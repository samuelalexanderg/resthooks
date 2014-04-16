package com.byteflair.rest.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RestApiException {

    private static final long serialVersionUID=6647074164892871120L;

    public ResourceNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public ResourceNotFoundException(String message, Exception e) {
        super(HttpStatus.NOT_FOUND, message, e);
    }

}
