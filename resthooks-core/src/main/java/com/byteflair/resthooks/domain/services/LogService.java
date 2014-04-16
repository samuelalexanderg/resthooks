package com.byteflair.resthooks.domain.services;

import com.byteflair.resthooks.api.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by dcerecedo on 1/25/14.
 */

@Service
public class LogService {

    @Autowired
    LogRepository logRepository;

    public Collection<? extends Log> getAll() {
        return logRepository.findAll();
    }

    public Log get(String id) {
        return logRepository.findOne(id);
    }
}
