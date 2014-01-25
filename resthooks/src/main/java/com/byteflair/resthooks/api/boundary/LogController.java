package com.byteflair.resthooks.api.boundary;

import com.byteflair.resthooks.api.Log;
import com.byteflair.resthooks.api.LogSpi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by dcerecedo on 1/24/14.
 */
public class LogController implements LogSpi {

    @Override
    public ResponseEntity<List<Log>> getResourceCollection() {
        return null;
    }

    @Override
    public ResponseEntity<Log> getResource(@PathVariable("id") String id) {
        return null;
    }
}
