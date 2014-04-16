package com.byteflair.resthooks.domain.services;

import com.byteflair.resthooks.api.Subscription;
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

    public Subscription create(Subscription form) {
        return null;
    }

    public Subscription update(Subscription form) {
        return null;
    }
}
