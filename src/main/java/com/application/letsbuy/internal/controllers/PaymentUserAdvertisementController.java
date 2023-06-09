package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.entities.PaymentUserAdvertisement;
import com.application.letsbuy.internal.services.PaymentUserAdversimentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/payment-user-advertisements")
public class PaymentUserAdvertisementController {

    private final PaymentUserAdversimentService paymentUserAdversimentService;

    @PostMapping("/{idUser}/{idAdvertisement}")
    ResponseEntity<PaymentUserAdvertisement> create(@PathVariable final Long idUser, @PathVariable final Long idAdvertisement) {
        return new ResponseEntity<>(this.paymentUserAdversimentService.create(idAdvertisement, idUser), HttpStatus.CREATED);
    }
}
