package com.byteflair.rest.exceptions;

import org.springframework.http.HttpStatus;

public class RequestForbiddenException extends RestApiException {

    private static final long serialVersionUID=-1234406325595024615L;

    public RequestForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }

    public RequestForbiddenException(String message, Exception e) {
        super(HttpStatus.FORBIDDEN, message, e);
    }

}
