package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.entities.Payment;
import com.application.letsbuy.internal.repositories.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;


    public Payment retrieve(Long idPayment) {
       return paymentRepository.findById(idPayment).orElseThrow(IllegalArgumentException::new);
    }

    public Optional<Payment> retrieveByExternalReference(String externalReference) {
        return paymentRepository.findByExternalReference(externalReference);
    }

    public Payment create(Payment payment) {
        if (Objects.isNull(payment)) {
            throw new IllegalArgumentException();
        }
        return this.paymentRepository.save(payment);
    }

    public Payment update(Payment payment) {
        if (Objects.isNull(payment)) {
            throw new IllegalArgumentException();
        }
        return this.paymentRepository.save(payment);
    }

    public void delete(Long idPayment) {
        Optional<Payment> paymentOptional = this.paymentRepository.findById(idPayment);
        paymentOptional.ifPresentOrElse(this.paymentRepository::delete, ()-> {
            throw new IllegalArgumentException();
        });
    }

}
