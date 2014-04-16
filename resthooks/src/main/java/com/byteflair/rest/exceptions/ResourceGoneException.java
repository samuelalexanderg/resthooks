package com.byteflair.rest.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceGoneException extends RestApiException {

    private static final long serialVersionUID=-3398316730303185072L;

    public ResourceGoneException(String message) {
        super(HttpStatus.GONE, message);
    }

    public ResourceGoneException(String message, Exception e) {
        super(HttpStatus.GONE, message, e);
    }

}
