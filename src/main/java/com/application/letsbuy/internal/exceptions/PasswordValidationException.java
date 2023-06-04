package com.application.letsbuy.internal.exceptions;

import java.io.Serial;

public class PasswordValidationException extends UnexpectedExceptionTemplate {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String CODE_STATUS = "400";

    private static final String ERROR_STATUS = "PASSWORD_VALIDATION_EXCEPTION";

    private static final String MESSAGE = "The password must have at least 8 characters, " +
            "one number, one uppercase letter, one lowercase letter, and one special character.";

    public PasswordValidationException() {
        super(ERROR_STATUS, CODE_STATUS, MESSAGE);
    }
}
