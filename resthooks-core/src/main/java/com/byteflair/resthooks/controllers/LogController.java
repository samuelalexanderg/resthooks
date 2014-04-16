package com.byteflair.resthooks.controllers;

import com.byteflair.rest.exceptions.ResourceNotFoundException;
import com.byteflair.resthooks.api.Log;
import com.byteflair.resthooks.api.LogSpi;
import com.byteflair.resthooks.domain.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dcerecedo on 1/24/14.
 */

@Component
public class LogController implements LogSpi {

    @Autowired
    LogService logService;

    @Override
    public ResponseEntity<List<Log>> getResourceCollection() {
        List<Log> logs=new ArrayList<>();
        logs.addAll(logService.getAll());
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Log> getResource(@PathVariable("id") String id) throws ResourceNotFoundException {
        Log log=logService.get(id);
        if(log == null) {
            throw new ResourceNotFoundException("Entity not found");
        }
        return new ResponseEntity<>(log, HttpStatus.OK);
    }
}
