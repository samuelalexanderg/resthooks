package com.byteflair.resthooks.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<RestApiException> handleException(RestApiException exception) {
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
        throwable.printStackTrace();
        return handleException(exception);
    }

}
