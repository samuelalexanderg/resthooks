package com.byteflair.resthooks.db.mongo;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by dcerecedo on 1/26/14.
 */
public interface LogMongoRepository extends CrudRepository<LogMongoImpl, String> {
}
