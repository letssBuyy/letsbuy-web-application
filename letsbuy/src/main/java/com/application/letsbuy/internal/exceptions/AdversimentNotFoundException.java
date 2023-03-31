package com.application.letsbuy.internal.exceptions;

import java.io.Serial;

public class AdversimentNotFoundException extends UnexpectedExceptionTemplate{

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String CODE_STATUS = "400";

    private static final String ERROR_STATUS = "ADVERSIMENT_NOT_FOUND_EXCEPTION";

    private static final String MESSAGE = "Adversiment not found";

    public AdversimentNotFoundException() {
        super(ERROR_STATUS, CODE_STATUS, MESSAGE);
    }
}

