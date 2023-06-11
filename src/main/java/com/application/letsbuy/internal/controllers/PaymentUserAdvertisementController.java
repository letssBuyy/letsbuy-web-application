package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.dto.PaymentUserAdvertisementRequestDto;
import com.application.letsbuy.internal.dto.PaymentUserAdvertisementResponseDto;
import com.application.letsbuy.internal.services.PaymentUserAdversimentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/payment-user-advertisements")
public class PaymentUserAdvertisementController {

    private final PaymentUserAdversimentService paymentUserAdversimentService;

    @PostMapping
    ResponseEntity<PaymentUserAdvertisementResponseDto> create(@RequestBody PaymentUserAdvertisementRequestDto paymentUserAdvertisementRequestDto) {
        return new ResponseEntity<>(this.paymentUserAdversimentService.create(paymentUserAdvertisementRequestDto), HttpStatus.CREATED);
    }
}
