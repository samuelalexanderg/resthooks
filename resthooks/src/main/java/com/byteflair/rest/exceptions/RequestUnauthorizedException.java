package com.byteflair.rest.exceptions;

import org.springframework.http.HttpStatus;

public class RequestUnauthorizedException extends RestApiException {

    private static final long serialVersionUID=6379061529188630235L;

    public RequestUnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }

    public RequestUnauthorizedException(String message, Exception e) {
        super(HttpStatus.UNAUTHORIZED, message, e);
    }
}
