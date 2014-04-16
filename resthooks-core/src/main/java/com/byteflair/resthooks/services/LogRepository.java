package com.byteflair.resthooks.services;

import com.byteflair.resthooks.api.Log;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by dcerecedo on 1/26/14.
 */

@NoRepositoryBean
public interface LogRepository extends Repository<Log, String> {

    void deleteAll();

    void delete(String id);

    List<Log> findAll();

    Log findOne(String id);

    Log save(Log log);
}
