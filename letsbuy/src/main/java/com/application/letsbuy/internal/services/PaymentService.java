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
        return this.paymentRepository.findById(idPayment).orElseThrow(IllegalArgumentException::new);
    }

    public Optional<Payment> retrieveByExternalReference(String externalReference) {
        return this.paymentRepository.findByExternalReference(externalReference);
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
        this.paymentRepository.findById(idPayment).ifPresentOrElse(this.paymentRepository::delete, () -> {
            throw new IllegalArgumentException();
        });
    }

}
