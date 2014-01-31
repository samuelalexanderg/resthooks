package com.byteflair.rest.exceptions;

import org.springframework.http.HttpStatus;

public class ConflictException extends RestApiException {

    private static final long serialVersionUID=8641674827960644641L;

    public ConflictException(String message) {
        super(HttpStatus.CONFLICT, message);
    }

    public ConflictException(String message, Exception e) {
        super(HttpStatus.CONFLICT, message, e);
    }

}
