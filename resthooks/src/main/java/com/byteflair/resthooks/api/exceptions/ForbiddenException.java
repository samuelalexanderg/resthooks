package com.byteflair.resthooks.api.exceptions;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends RestApiException {

    private static final long serialVersionUID=-1234406325595024615L;

    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }

    public ForbiddenException(String message, Exception e) {
        super(HttpStatus.FORBIDDEN, message, e);
    }

}
