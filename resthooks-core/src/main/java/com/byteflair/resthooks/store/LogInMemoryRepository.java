package com.byteflair.resthooks.store;

import com.byteflair.resthooks.domain.LogImpl;
import com.byteflair.resthooks.domain.services.LogRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dcerecedo on 1/26/14.
 */
public class LogInMemoryRepository implements LogRepository<LogImpl> {

    private Map<String, LogImpl> logStore=new HashMap<>();

    @Override
    public void deleteAll() {
        logStore.clear();
    }

    @Override
    public List<LogImpl> findAll() {
        return new ArrayList<>(logStore.values());
    }

    @Override
    public void delete(String id) {
        logStore.remove(id);
    }

    @Override
    public LogImpl findOne(String id) {
        return logStore.get(id);
    }

    @Override
    public LogImpl save(LogImpl resource) {
        return logStore.put(resource.getId(), resource);
    }
}
