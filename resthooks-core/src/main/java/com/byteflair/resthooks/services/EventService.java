package com.byteflair.resthooks.services;

import com.byteflair.resthooks.api.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by dcerecedo on 1/25/14.
 */

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public Collection<? extends Event> getAll() {
        return eventRepository.findAll();
    }

    public Event get(String id) {
        return eventRepository.findOne(id);
    }
}
