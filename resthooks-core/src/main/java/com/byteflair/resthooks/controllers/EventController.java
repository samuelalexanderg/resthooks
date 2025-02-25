package com.byteflair.resthooks.controllers;

import com.byteflair.rest.exceptions.ResourceNotFoundException;
import com.byteflair.resthooks.api.Event;
import com.byteflair.resthooks.api.EventSpi;
import com.byteflair.resthooks.domain.services.EventService;
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
public class EventController implements EventSpi {

    @Autowired
    EventService eventService;

    @Override
    public ResponseEntity<List<Event>> getResourceCollection() {
        List<Event> events=new ArrayList<>();
        events.addAll(eventService.getAll());
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Event> getResource(@PathVariable("id") String id) throws ResourceNotFoundException {
        Event event=eventService.get(id);
        if(event == null) {
            throw new ResourceNotFoundException("Entity not found");
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }
}
