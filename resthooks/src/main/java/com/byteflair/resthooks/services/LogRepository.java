package com.byteflair.resthooks.services;

import com.byteflair.resthooks.api.Log;

import java.util.List;

/**
 * Created by dcerecedo on 1/26/14.
 */
public interface LogRepository {

    public void delete(Object id);

    public List<Log> findAll();

    public Log findOne(Object id);

    public Log save(Log log);
}
