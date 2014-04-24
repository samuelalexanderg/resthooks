package com.byteflair.resthooks.store.mongo;

import com.byteflair.resthooks.api.Subscription;
import com.byteflair.resthooks.domain.LogImpl;
import com.byteflair.resthooks.domain.SubscriptionImpl;
import com.byteflair.resthooks.domain.services.LogRepository;
import com.byteflair.resthooks.domain.services.SubscriptionRepository;

/**
 * Created by dcerecedo on 16/04/14.
 */
public interface SubscriptionMongoRepository extends SubscriptionRepository<SubscriptionMongoImpl> {
}
