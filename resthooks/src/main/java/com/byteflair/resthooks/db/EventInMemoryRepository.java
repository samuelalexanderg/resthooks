package com.byteflair.resthooks.db;

import com.byteflair.resthooks.api.Event;
import com.byteflair.resthooks.api.impl.EventRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dcerecedo on 1/26/14.
 */
public class EventInMemoryRepository implements EventRepository {

    private Map<Object, Event> eventStore=new HashMap<>();

    @Override
    public void deleteAll() {
        eventStore.clear();
    }

    @Override
    public void delete(Object id) {
        eventStore.remove(id);
    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<Event>(eventStore.values());
    }

    @Override
    public Event findOne(Object id) {
        return eventStore.get(id);
    }

    @Override
    public Event save(Event event) {
        return eventStore.put(event.getId(), event);
    }
}
