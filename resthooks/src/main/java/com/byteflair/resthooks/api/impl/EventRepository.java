package com.byteflair.resthooks.api.impl;

import com.byteflair.resthooks.api.Event;

import java.util.List;

/**
 * Created by dcerecedo on 1/30/14.
 */
public interface EventRepository {

    public void deleteAll();

    public void delete(Object id);

    public List<Event> findAll();

    public Event findOne(Object id);

    public Event save(Event event);
}
