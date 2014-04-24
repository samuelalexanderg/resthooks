package com.byteflair.resthooks.store;

import com.byteflair.resthooks.domain.SubscriptionImpl;
import com.byteflair.resthooks.domain.services.SubscriptionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dcerecedo on 1/26/14.
 */
public class SubscriptionInMemoryRepository implements SubscriptionRepository<SubscriptionImpl> {

    private Map<String, SubscriptionImpl> store=new HashMap<>();

    @Override
    public void deleteAll() {
        store.clear();
    }

    @Override
    public List<SubscriptionImpl> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(String id) {
        store.remove(id);
    }

    @Override
    public SubscriptionImpl findOne(String id) {
        return store.get(id);
    }

    @Override
    public SubscriptionImpl save(SubscriptionImpl subscription) {
        return store.put(subscription.getId(), subscription);
    }
}
