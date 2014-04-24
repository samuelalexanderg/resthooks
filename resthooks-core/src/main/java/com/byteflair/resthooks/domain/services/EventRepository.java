package com.byteflair.resthooks.domain.services;

import com.byteflair.resthooks.api.Event;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by dcerecedo on 1/30/14.
 */

@NoRepositoryBean
public interface EventRepository<R extends Event> extends BaseRepository<R, String> {

    public void delete(String id);

    public R findOne(String id);

    public R save(R resource);
}