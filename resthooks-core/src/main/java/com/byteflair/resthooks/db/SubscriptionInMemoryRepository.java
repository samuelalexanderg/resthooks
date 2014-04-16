package com.byteflair.resthooks.db;

import com.byteflair.resthooks.api.Subscription;
import com.byteflair.resthooks.services.SubscriptionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dcerecedo on 1/26/14.
 */
public class SubscriptionInMemoryRepository implements SubscriptionRepository {

    private Map<String, Subscription> store=new HashMap<>();

    @Override
    public void delete(String id) {
        store.remove(id);
    }

    @Override
    public List<Subscription> findAll() {
        return new ArrayList<Subscription>(store.values());
    }

    @Override
    public Subscription findOne(String id) {
        return store.get(id);
    }

    @Override
    public Subscription save(Subscription subscription) {
        return store.put(subscription.getId(), subscription);
    }
}
