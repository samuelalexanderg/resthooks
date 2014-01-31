package com.byteflair.rest.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends RestApiException {

    private static final long serialVersionUID=6379061529188630235L;

    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }

    public UnauthorizedException(String message, Exception e) {
        super(HttpStatus.UNAUTHORIZED, message, e);
    }
}
