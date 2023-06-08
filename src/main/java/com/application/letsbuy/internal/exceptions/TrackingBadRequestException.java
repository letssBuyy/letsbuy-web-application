package com.application.letsbuy.internal.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TrackingBadRequestException extends RuntimeException {

    public TrackingBadRequestException(String message) {
        super(message);
    }
}
