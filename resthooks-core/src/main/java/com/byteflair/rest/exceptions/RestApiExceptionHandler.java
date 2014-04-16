package com.byteflair.rest.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestApiExceptionHandler {

    private final static Logger LOGGER=LoggerFactory.getLogger(RestApiExceptionHandler.class);

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<RestApiException> handleException(RestApiException exception) {
        LOGGER.error("Sending error to client {}", exception);
        return new ResponseEntity<RestApiException>(exception, exception.getStatus());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<RestApiException> handleException(HttpRequestMethodNotSupportedException requestNotSupported) {
        RestApiException exception=new RestApiException(HttpStatus.METHOD_NOT_ALLOWED, requestNotSupported.getMessage(), requestNotSupported.getCause());
        return handleException(exception);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<RestApiException> handleException(Throwable throwable) {
        RestApiException exception=new RestApiException(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage(), throwable.getCause());
        return handleException(exception);
    }

}
