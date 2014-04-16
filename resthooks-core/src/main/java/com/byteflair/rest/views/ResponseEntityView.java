package com.byteflair.rest.views;

import org.springframework.http.ResponseEntity;

public class ResponseEntityView extends ResponseEntity<Object> {

    public ResponseEntityView(ResponseEntity<Object> data, Class<? extends View> view) {
        super(ResponseEntityView.wrapBody(data.getBody(), view), data.getHeaders(), data.getStatusCode());
    }

    private static Object wrapBody(Object body, Class<? extends View> view) {
        return new DataViewImpl(body, view);
    }
}
