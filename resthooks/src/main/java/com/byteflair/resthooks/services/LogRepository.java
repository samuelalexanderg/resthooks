package com.byteflair.resthooks.services;

import com.byteflair.resthooks.Log;

import java.util.List;

/**
 * Created by dcerecedo on 1/26/14.
 */
public interface LogRepository {

    void deleteAll();

    void delete(Object id);

    List<Log> findAll();

    Log findOne(Object id);

    Log save(Log log);
}
