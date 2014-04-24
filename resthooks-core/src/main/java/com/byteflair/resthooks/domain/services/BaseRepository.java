package com.byteflair.resthooks.domain.services;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dcerecedo on 14/04/14.
 */

@NoRepositoryBean
public interface BaseRepository<R, ID extends Serializable> extends Repository<R, ID> {

    void deleteAll();

    void delete(ID id);

    List<R> findAll();

    R findOne(ID id);

    R save(R resource);
}
