package com.byteflair.rest.exceptions;

import org.springframework.http.HttpStatus;

public class GoneException extends RestApiException {

    private static final long serialVersionUID=-3398316730303185072L;

    public GoneException(String message) {
        super(HttpStatus.GONE, message);
    }

    public GoneException(String message, Exception e) {
        super(HttpStatus.GONE, message, e);
    }

}
