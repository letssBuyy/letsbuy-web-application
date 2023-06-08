package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.dto.TransactionDto;
import com.application.letsbuy.internal.entities.*;
import com.application.letsbuy.internal.enums.AdversimentEnum;
import com.application.letsbuy.internal.enums.PaymentControllSellerEnum;
import com.application.letsbuy.internal.enums.TrackingStatus;
import com.application.letsbuy.internal.repositories.PaymentControlSellerRepository;
import com.application.letsbuy.internal.repositories.PaymentUserAdversimentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.application.letsbuy.internal.enums.TrackingStatus.AWAITING_PAYMENT;

@Service
@AllArgsConstructor
public class PaymentUserAdversimentService {

    private final AdversimentService advertisementService;
    private final UserService userService;
    private final CreatePaymentServiceBroker createPaymentServiceBroker;
    private final PaymentService paymentService;
    private final PaymentUserAdversimentRepository paymentUserAdversimentRepository;
    private final PaymentControlSellerRepository paymentControlSellerRepository;


    public PaymentUserAdvertisement create(Long adversimentId, Long buyerId) {

        Adversiment advertisement = this.advertisementService.findById(adversimentId);
        User user = this.userService.findById(buyerId);
        TransactionDto transactionDto = this.createPaymentServiceBroker.createTransaction(user, advertisement);
        Optional<Payment> payment = this.paymentService.retrieveByExternalReference(transactionDto.getExternalReference());

        if (payment.isPresent()) {
            Tracking tracking = new Tracking();
            tracking.setAdversiment(advertisement);
            tracking.setStatus(AWAITING_PAYMENT);
            advertisement.setTrackings(List.of(tracking));
            advertisement.setIsActive(AdversimentEnum.SELLING);
            this.advertisementService.save(advertisement);
            PaymentUserAdvertisement paymentUserAdvertisement = this.createPaymentUserAdversiment(advertisement, user, payment.get());
            this.createPaymentControllSeller(paymentUserAdvertisement);
            return paymentUserAdvertisement;
        }
        throw new IllegalArgumentException();
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
