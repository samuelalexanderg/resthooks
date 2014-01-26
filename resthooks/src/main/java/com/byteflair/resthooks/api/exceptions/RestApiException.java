package com.byteflair.resthooks.api.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@JsonIgnoreProperties(value={ "cause", "stackTrace", "localizedMessage", "suppressed" }, ignoreUnknown=true)
public class RestApiException extends Exception {

    private static final long serialVersionUID=5726439321553988580L;

    private HttpStatus status=null;

    private String message=null;

    private Throwable reason=null;

    RestApiException(HttpStatus status, String message) {
        this.status=status;
        this.message=message;
    }

    protected RestApiException(HttpStatus status, BindingResult result) {
        this.status=status;
        StringBuilder buffer=new StringBuilder();
        boolean isFirst=true;
        for(ObjectError error : result.getAllErrors()) {
            if(isFirst) {
                isFirst=false;
            } else {
                buffer.append("; and,\n");
            }
            buffer.append(error.getDefaultMessage());
        }
        this.message=buffer.toString();
    }

    RestApiException(HttpStatus status, String message, Throwable reason) {
        this.status=status;
        this.message=message;
        this.reason=reason;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getCode() {
        return status.value();
    }

    public String getMessage() {
        return message;
    }

    public Throwable getReason() {
        return this.reason;
    }

}
