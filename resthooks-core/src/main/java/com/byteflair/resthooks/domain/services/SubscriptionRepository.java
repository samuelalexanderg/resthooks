package com.byteflair.resthooks.domain.services;

import com.byteflair.resthooks.api.Subscription;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by dcerecedo on 1/25/14.
 */

@NoRepositoryBean
public interface SubscriptionRepository<R extends Subscription> extends BaseRepository<R, String> {

    void delete(String id);

    R findOne(String id);

    R save(R resource);
}
