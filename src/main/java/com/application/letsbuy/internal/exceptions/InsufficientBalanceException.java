package com.application.letsbuy.internal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientBalanceException extends UnexpectedExceptionTemplate {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String CODE_STATUS = "400";

    private static final String ERROR_STATUS = "INSUFFICIENT_BALANCE_EXCEPTION";

    private static final String MESSAGE = "Insufficient balance";

    public InsufficientBalanceException() {
        super(ERROR_STATUS, CODE_STATUS, MESSAGE);
    }
}
