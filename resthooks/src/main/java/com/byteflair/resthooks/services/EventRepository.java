package com.byteflair.resthooks.services;

import com.byteflair.resthooks.api.Event;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by dcerecedo on 1/30/14.
 */
@NoRepositoryBean
public interface EventRepository extends BaseRepository<Event, String> {

    void deleteAll();

    void delete(String id);

    List<Event> findAll();

    Event findOne(String id);

    Event save(Event event);
}