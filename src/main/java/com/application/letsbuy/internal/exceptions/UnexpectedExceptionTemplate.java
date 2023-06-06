package com.application.letsbuy.internal.exceptions;

import lombok.Getter;

public class UnexpectedExceptionTemplate extends RuntimeException {
    private static final String DEFAULT_STATUS = "500";
    private static final String DEFAULT_CODE = "UNKNOW";
    private final String status;
    private final String code;
    private final String message;

    public UnexpectedExceptionTemplate(String status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
