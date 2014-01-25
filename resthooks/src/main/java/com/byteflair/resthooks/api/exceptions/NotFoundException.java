package com.byteflair.resthooks.api.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RestApiException {

    private static final long serialVersionUID=6647074164892871120L;

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public NotFoundException(String message, Exception e) {
        super(HttpStatus.NOT_FOUND, message, e);
    }

}
