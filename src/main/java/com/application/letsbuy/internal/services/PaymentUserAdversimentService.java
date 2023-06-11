package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.dto.*;
import com.application.letsbuy.internal.entities.*;
import com.application.letsbuy.internal.enums.AdversimentEnum;
import com.application.letsbuy.internal.enums.PaymentControllSellerEnum;
import com.application.letsbuy.internal.enums.PaymentStatusEnum;
import com.application.letsbuy.internal.exceptions.AdversimentNotFoundException;
import com.application.letsbuy.internal.repositories.PaymentControlSellerRepository;
import com.application.letsbuy.internal.repositories.PaymentUserAdversimentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.application.letsbuy.internal.enums.TrackingStatus.AWAITING_PAYMENT;
import static com.application.letsbuy.internal.enums.TrackingStatus.WAITING_FOR_SHIPMENT;

@Service
@AllArgsConstructor
@Transactional
public class PaymentUserAdversimentService {

    private final AdversimentService advertisementService;
    private final UserService userService;
    private final CreatePaymentServiceBroker createPaymentServiceBroker;
    private final PaymentService paymentService;
    private final PaymentUserAdversimentRepository paymentUserAdversimentRepository;
    private final PaymentControlSellerRepository paymentControlSellerRepository;


    public PaymentUserAdvertisementResponseDto create(PaymentUserAdvertisementRequestDto paymentUserAdvertisementRequestDto) {

        Adversiment advertisement = this.advertisementService.findById(paymentUserAdvertisementRequestDto.getIdAdvertisement());
        User user = this.userService.findById(paymentUserAdvertisementRequestDto.getIdUser());
        PagSeguroDto pagSeguroDto = this.createPaymentServiceBroker.createTransaction(paymentUserAdvertisementRequestDto);

        ChargesPagSeguroDto chargesPagSeguroDto = pagSeguroDto.getCharges().stream().findFirst().orElseThrow();

        if (chargesPagSeguroDto.getStatus().equals("PAID")) {
            List<Tracking> tracking = getTracking(advertisement);
            advertisement.setTrackings(tracking);
            advertisement.setIsActive(AdversimentEnum.SALLED);
            this.advertisementService.save(advertisement);
            PaymentUserAdvertisement paymentUserAdvertisement = this.createPaymentUserAdversiment(advertisement, user, this.createPayment(pagSeguroDto));
            this.paymentUserAdversimentRepository.save(paymentUserAdvertisement);
            this.createPaymentControllSeller(paymentUserAdvertisement);

            return PaymentUserAdvertisementResponseDto.parseEntityToDto(paymentUserAdvertisement);
        }

        throw new IllegalArgumentException();
    }

    private static List<Tracking> getTracking(Adversiment advertisement) {

        List<Tracking> trackings = new ArrayList<>();

        Tracking trackingAwaitingPayment = new Tracking();
        trackingAwaitingPayment.setAdversiment(advertisement);
        trackingAwaitingPayment.setStatus(AWAITING_PAYMENT);
        trackings.add(trackingAwaitingPayment);

        Tracking trackingWaitingForShipment = new Tracking();
        trackingWaitingForShipment.setAdversiment(advertisement);
        trackingWaitingForShipment.setStatus(WAITING_FOR_SHIPMENT);
        trackings.add(trackingWaitingForShipment);

        return trackings;
    }

    public PaymentUserAdvertisement findByAdversimentId(Long idAdversiment) {

        Adversiment adversiment = this.advertisementService.findById(idAdversiment);

        Optional<PaymentUserAdvertisement> paymentOptional = paymentUserAdversimentRepository.findByAdversiment(adversiment);
        if (paymentOptional.isEmpty()){
            throw new AdversimentNotFoundException();
        }
        return paymentOptional.get();
    }

    private Payment createPayment(PagSeguroDto pagSeguroDto) {

        ItemsPagSeguroDto itemsPagSeguroDto = pagSeguroDto.getItems().stream().findFirst().orElseThrow();
        ChargesPagSeguroDto chargesPagSeguroDto = pagSeguroDto.getCharges().stream().findFirst().orElseThrow();
        AmountPagSeguroDto amountPagSeguroDto = chargesPagSeguroDto.getAmount();
        PaymentMethodPagSeguroDto paymentMethodPagSeguroDto = chargesPagSeguroDto.getPaymentMethod();

        Payment payment = new Payment();
        payment.setAmount(BigDecimal.valueOf(itemsPagSeguroDto.getUnitAmount()));
        payment.setDescription(itemsPagSeguroDto.getName());
        payment.setStatus(PaymentStatusEnum.valueOf(chargesPagSeguroDto.getStatus()));
        payment.setCurrencyId(amountPagSeguroDto.getCurrency());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setExternalReference(pagSeguroDto.getId());
        payment.setPaymentMethod(paymentMethodPagSeguroDto.getType());

        return paymentService.create(payment);
    }


    private PaymentUserAdvertisement createPaymentUserAdversiment(Adversiment advertisement, User user, Payment payment) {
        PaymentUserAdvertisement paymentUserAdvertisement = new PaymentUserAdvertisement();
        paymentUserAdvertisement.setAdversiment(advertisement);
        paymentUserAdvertisement.setPayment(payment);
        paymentUserAdvertisement.setBuyer(user);
        return paymentUserAdvertisement;
    }

    private void createPaymentControllSeller(PaymentUserAdvertisement paymentUserAdvertisement) {
        PaymentControllSeller paymentControllSeller = new PaymentControllSeller();
        paymentControllSeller.setStatus(PaymentControllSellerEnum.PENDING);
        paymentControllSeller.setAmountTax(10L);
        paymentControllSeller.setPaymentUserAdvertisement(paymentUserAdvertisement);
        this.paymentControlSellerRepository.save(paymentControllSeller);
    }
}
