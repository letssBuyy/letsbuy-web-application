package com.application.letsbuy.internal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends UnexpectedExceptionTemplate {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String CODE_STATUS = "400";

    private static final String ERROR_STATUS = "USER_NOT_FOUND_EXCEPTION";

    private static final String MESSAGE = "User not found";

    public UserNotFoundException() {
        super(ERROR_STATUS, CODE_STATUS, MESSAGE);
    }
}
