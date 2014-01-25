package com.byteflair.resthooks.api.exceptions;

import org.springframework.http.HttpStatus;

public class ServiceUnavailableException extends RestApiException {


    private static final long serialVersionUID=745105939406211125L;

    public ServiceUnavailableException(String message) {
        super(HttpStatus.SERVICE_UNAVAILABLE, message);
    }

    public ServiceUnavailableException(String message, Exception e) {
        super(HttpStatus.SERVICE_UNAVAILABLE, message, e);
    }

}
