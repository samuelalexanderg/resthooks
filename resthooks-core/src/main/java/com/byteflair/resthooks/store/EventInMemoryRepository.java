package com.byteflair.resthooks.store;

import com.byteflair.resthooks.domain.EventImpl;
import com.byteflair.resthooks.domain.services.EventRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dcerecedo on 1/26/14.
 */
public class EventInMemoryRepository implements EventRepository<EventImpl> {

    private Map<String, EventImpl> eventStore=new HashMap<>();

    @Override
    public void deleteAll() {
        eventStore.clear();
    }

    @Override
    public List<EventImpl> findAll() {
        return new ArrayList<>(eventStore.values());
    }

    @Override
    public void delete(String id) {
        eventStore.remove(id);
    }

    @Override
    public EventImpl findOne(String id) {
        return eventStore.get(id);
    }

    @Override
    public EventImpl save(EventImpl event) {
        return eventStore.put(event.getId(), event);
    }
}
