package com.byteflair.resthooks.controllers;

import com.byteflair.rest.exceptions.NotFoundException;
import com.byteflair.resthooks.Event;
import com.byteflair.resthooks.EventSpi;
import com.byteflair.resthooks.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dcerecedo on 1/24/14.
 */
public class EventController implements EventSpi {

    @Autowired
    EventService eventService;

    @Override
    public ResponseEntity<List<Event>> getResourceCollection() {
        List<Event> events=new ArrayList<>();
        events.addAll(eventService.getAll());
        return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Event> getResource(@PathVariable("id") String id) throws NotFoundException {
        Event event=eventService.get(id);
        if(event == null) {
            throw new NotFoundException("Entity not found");
        }
        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }
}
