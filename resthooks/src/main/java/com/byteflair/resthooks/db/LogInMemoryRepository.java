package com.byteflair.resthooks.db;

import com.byteflair.resthooks.api.Log;
import com.byteflair.resthooks.api.impl.LogRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dcerecedo on 1/26/14.
 */
public class LogInMemoryRepository implements LogRepository {

    private Map<Object, Log> logStore=new HashMap<>();

    @Override
    public void delete(Object id) {
        logStore.remove(id);
    }

    @Override
    public List<Log> findAll() {
        return new ArrayList<Log>(logStore.values());
    }

    @Override
    public Log findOne(Object id) {
        return logStore.get(id);
    }

    @Override
    public Log save(Log log) {
        return logStore.put(log.getId(), log);
    }
}
