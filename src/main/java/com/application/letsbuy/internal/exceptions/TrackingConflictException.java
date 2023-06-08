package com.application.letsbuy.internal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TrackingConflictException extends RuntimeException {

    public TrackingConflictException(String message) {
        super(message);
    }
}
