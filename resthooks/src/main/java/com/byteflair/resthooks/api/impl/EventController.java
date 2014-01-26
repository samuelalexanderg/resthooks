package com.byteflair.resthooks.api.impl;

import com.byteflair.resthooks.api.Event;
import com.byteflair.resthooks.api.EventSpi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by dcerecedo on 1/24/14.
 */
public class EventController implements EventSpi {

    @Override
    public ResponseEntity<List<Event>> getResourceCollection() {
        return null;
    }

    @Override
    public ResponseEntity<Event> getResource(@PathVariable("id") String id) {
        return null;
    }
}
