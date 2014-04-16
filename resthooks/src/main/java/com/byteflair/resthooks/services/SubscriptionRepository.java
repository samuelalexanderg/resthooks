package com.byteflair.resthooks.services;

import com.byteflair.resthooks.api.Subscription;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by dcerecedo on 1/25/14.
 */

@NoRepositoryBean
public interface SubscriptionRepository extends BaseRepository<Subscription, String> {

    public void delete(String id);

    public List<Subscription> findAll();

    public Subscription findOne(String id);

    public Subscription save(Subscription subscription);
}
