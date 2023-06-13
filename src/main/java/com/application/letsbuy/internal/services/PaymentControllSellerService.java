package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.entities.PaymentControllSeller;
import com.application.letsbuy.internal.repositories.PaymentControlSellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentControllSellerService {

    private final PaymentControlSellerRepository paymentControlSellerRepository;


    public Double calculateTax() {
        double totalTaxs = 0.0;

        List<PaymentControllSeller> payments = paymentControlSellerRepository.findAll();

        for (PaymentControllSeller payment : payments){
            Double price = payment.getPaymentUserAdvertisement().getAdversiment().getPrice();
            totalTaxs += price * (payment.getAmountTax()/100.0);
        }

        return totalTaxs;
    }
}
