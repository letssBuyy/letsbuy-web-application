package com.application.letsbuy.internal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.CONFLICT)
public class AdversimentIsAlreadyLikedException extends UnexpectedExceptionTemplate {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String CODE_STATUS = "409";

    private static final String ERROR_STATUS = "ADVERSIMENT_IS_ALREADY_LIKED";

    private static final String MESSAGE = "Adversiment is already liked";

    public AdversimentIsAlreadyLikedException() {
        super(ERROR_STATUS, CODE_STATUS, MESSAGE);
    }
}
