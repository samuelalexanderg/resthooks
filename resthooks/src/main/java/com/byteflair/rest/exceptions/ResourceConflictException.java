package com.byteflair.rest.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceConflictException extends RestApiException {

    private static final long serialVersionUID=8641674827960644641L;

    public ResourceConflictException(String message) {
        super(HttpStatus.CONFLICT, message);
    }

    public ResourceConflictException(String message, Exception e) {
        super(HttpStatus.CONFLICT, message, e);
    }

}
