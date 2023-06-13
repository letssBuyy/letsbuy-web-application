package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.dto.BankAccountDtoRequest;
import com.application.letsbuy.internal.dto.BankAccountDtoResponse;
import com.application.letsbuy.internal.dto.TaxResponseDto;
import com.application.letsbuy.internal.entities.BankAccount;
import com.application.letsbuy.internal.services.BankAccountService;
import com.application.letsbuy.internal.services.PaymentControllSellerService;
import com.application.letsbuy.internal.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/payment-controll")
public class PaymentControllSellerController {

    private final PaymentControllSellerService paymentControllSellerService;
    @GetMapping("/taxs")
    public ResponseEntity<TaxResponseDto> calulateTaxs() {
        return new ResponseEntity<>(new TaxResponseDto(paymentControllSellerService.calculateTax()), HttpStatus.OK);
    }
}
