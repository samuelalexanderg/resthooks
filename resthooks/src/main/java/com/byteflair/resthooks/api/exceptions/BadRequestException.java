package com.byteflair.resthooks.api.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RestApiException {

    private static final long serialVersionUID=-2072519535724461405L;

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public BadRequestException(String message, Exception cause) {
        super(HttpStatus.BAD_REQUEST, message, cause);
    }

}
