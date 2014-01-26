package com.byteflair.resthooks.services;

import com.byteflair.resthooks.api.Subscription;

import java.util.List;

/**
 * Created by dcerecedo on 1/25/14.
 */
public interface SubscriptionRepository {

    public void delete(Object id);

    public List<Subscription> findAll();

    public Subscription findOne(Object id);

    public Subscription save(Subscription subscription);
}
