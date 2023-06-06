package com.application.letsbuy.internal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class AdversimentsLikeNoContentException extends UnexpectedExceptionTemplate {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String CODE_STATUS = "204";

    private static final String ERROR_STATUS = "ADVERSIMENTSLIKE_NO_CONTENT_EXCEPTION";

    private static final String MESSAGE = "AdversimentS like no content";

    public AdversimentsLikeNoContentException() {
        super(ERROR_STATUS, CODE_STATUS, MESSAGE);
    }
}
