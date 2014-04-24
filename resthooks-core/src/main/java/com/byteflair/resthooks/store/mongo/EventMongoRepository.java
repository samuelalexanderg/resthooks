package com.byteflair.resthooks.store.mongo;

import com.byteflair.resthooks.domain.EventImpl;
import com.byteflair.resthooks.domain.services.EventRepository;

/**
 * Created by dcerecedo on 16/04/14.
 */
public interface EventMongoRepository extends EventRepository<EventMongoImpl> {
}
