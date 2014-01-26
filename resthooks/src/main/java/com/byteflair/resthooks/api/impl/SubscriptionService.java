package com.byteflair.resthooks.api.impl;

import com.byteflair.resthooks.api.Subscription;
import com.byteflair.resthooks.api.boundary.SubscriptionPostForm;
import com.byteflair.resthooks.api.boundary.SubscriptionPutForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by dcerecedo on 1/25/14.
 */

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    public Collection<? extends Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    public Subscription get(String id) {
        return null;
    }

    public Subscription create(SubscriptionPostForm form) {
        return null;
    }

    public Subscription update(SubscriptionPutForm form) {
        return null;
    }
}
