package com.byteflair.resthooks.db.mongo;

import com.byteflair.resthooks.services.LogRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dcerecedo on 1/26/14.
 */
public class LogMongoRepository extends CrudRepository<LogMongoImpl, Object> implements LogRepository {
}
