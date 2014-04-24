package com.byteflair.resthooks.domain.services;

import com.byteflair.resthooks.api.Log;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by dcerecedo on 1/26/14.
 */

@NoRepositoryBean
public interface LogRepository<R extends Log> extends BaseRepository<R, String> {

    void delete(String id);

    R findOne(String id);

    R save(R resource);
}
