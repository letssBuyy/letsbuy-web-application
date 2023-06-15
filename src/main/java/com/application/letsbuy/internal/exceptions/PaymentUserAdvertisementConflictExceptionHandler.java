package com.application.letsbuy.internal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PaymentUserAdvertisementConflictExceptionHandler extends RuntimeException {

    public PaymentUserAdvertisementConflictExceptionHandler(String message) {
        super(message);
    }

    public PaymentUserAdvertisementConflictExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }
}
