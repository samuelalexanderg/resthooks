package com.byteflair.resthooks.services;

import com.byteflair.resthooks.Event;

import java.util.List;

/**
 * Created by dcerecedo on 1/30/14.
 */
public interface EventRepository {

    void deleteAll();

    void delete(Object id);

    List<Event> findAll();

    Event findOne(Object id);

    Event save(Event event);
}