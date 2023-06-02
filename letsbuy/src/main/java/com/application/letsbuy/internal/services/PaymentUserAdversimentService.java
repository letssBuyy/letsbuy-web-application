package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.dto.TransactionDto;
import com.application.letsbuy.internal.entities.*;
import com.application.letsbuy.internal.enums.AdversimentEnum;
import com.application.letsbuy.internal.enums.TrackingStatus;
import com.application.letsbuy.internal.repositories.PaymentUserAdversimentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentUserAdversimentService {

    private final AdversimentService advertisementService;
    private final UserService userService;
    private final CreatePaymentServiceBroker createPaymentServiceBroker;
    private final PaymentService paymentService;
    private final PaymentUserAdversimentRepository paymentUserAdversimentRepository;


    private PaymentUserAdverstisement create(Long adversimentId, Long buyerId) {

        Adversiment advertisement = this.advertisementService.findById(adversimentId);
        User user = this.userService.findById(buyerId);
        TransactionDto transactionDto = this.createPaymentServiceBroker.createTransaction(user.getId(), advertisement.getId());
        Optional<Payment> payment = this.paymentService.retrieveByExternalReference(transactionDto.getTransactionExternalReference());

        if (payment.isPresent()) {
            Tracking tracking = new Tracking();
            tracking.setAdversiment(advertisement);
            tracking.setStatus(TrackingStatus.AWAITING_PAYMENT);
            advertisement.setTrackings(List.of(tracking));
            advertisement.setIsActive(AdversimentEnum.SELLING);
            this.advertisementService.save(advertisement);
            return this.paymentUserAdversimentRepository.save(this.createPaymentUserAdversiment(advertisement, user, payment.get()));
        }
        throw new IllegalArgumentException();
    }

    private PaymentUserAdverstisement createPaymentUserAdversiment(Adversiment advertisement, User user, Payment payment) {
        PaymentUserAdverstisement paymentUserAdverstisement = new PaymentUserAdverstisement();
        paymentUserAdverstisement.setAdversiment(advertisement);
        paymentUserAdverstisement.setPayment(payment);
        paymentUserAdverstisement.setBuyer(user);
        return paymentUserAdverstisement;
    }
}
