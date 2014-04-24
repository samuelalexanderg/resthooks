package com.byteflair.resthooks.domain.services;

import com.byteflair.resthooks.api.Log;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by dcerecedo on 1/26/14.
 */

@NoRepositoryBean
public interface LogRepository<R extends Log> extends BaseRepository<R, String> {

    public void delete(String id);

    public R findOne(String id);

    public R save(R resource);
}
