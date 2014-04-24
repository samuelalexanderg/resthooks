package com.byteflair.rest.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Default exception handler for RESTful services. I tries to convert exceptions to the
 * corresponding RestApiException that represents an adequate HTTP response.
 * You should not rely on this handler to perform all converstions from any exception to
 * its corresponding HTTP response. In some cases, depending on the granularity of your
 * exceptions, you will have to perform a custom conversion on per method basis. This is,
 * in most cases there will be specific logic depending on the invocation that is failing
 * or the context of the invocation.
 * This handler is intended to be used as a fallback.
 */
@ControllerAdvice
public class RestApiExceptionHandler {

    private final static Logger LOGGER=LoggerFactory.getLogger(RestApiExceptionHandler.class);

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<RestApiException> handleException(RestApiException exception) {
        LOGGER.error("Sending error to client {}", exception);
        return new ResponseEntity<RestApiException>(exception, exception.getStatus());
    }

    /**
     * Used to handle IllegalArgumentException thrown by Spring's Assert utilities
     * that are used for further validation made at the controller level.
     * You can also use this mechanism to test preconditions and postconditions at
     * most methods and ensure that a comprenhensive error is sent to the client
     *
     * @param requestNotSupported
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RestApiException> handleException(IllegalArgumentException requestNotSupported) {
        RestApiException exception=new RestApiException(HttpStatus.BAD_REQUEST, requestNotSupported.getMessage(), requestNotSupported.getCause());
        return handleException(exception);
    }

    /**
     * Handles the invocation of an endpoint that is not mapped by any controller
     *
     * @param requestNotSupported
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<RestApiException> handleException(HttpRequestMethodNotSupportedException requestNotSupported) {
        RestApiException exception=new RestApiException(HttpStatus.METHOD_NOT_ALLOWED, requestNotSupported.getMessage(), requestNotSupported.getCause());
        return handleException(exception);
    }

    /**
     * Tries to catch any other exception. In many cases it won't work.
     * @param throwable
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<RestApiException> handleException(Throwable throwable) {
        RestApiException exception=new RestApiException(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage(), throwable.getCause());
        return handleException(exception);
    }

}
