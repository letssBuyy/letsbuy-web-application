package com.application.letsbuy.internal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserConflictException extends UnexpectedExceptionTemplate {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String CODE_STATUS = "409";

    private static final String ERROR_STATUS = "USER_CONFLICT_EXCEPTION";

    private static final String MESSAGE = "User conflict";

    public UserConflictException() {
        super(ERROR_STATUS, CODE_STATUS, MESSAGE);
    }
}
