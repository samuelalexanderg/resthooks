package com.byteflair.resthooks.domain;

import com.byteflair.resthooks.api.Subscription;

import java.net.URL;

/**
 * Created by dcerecedo on 1/19/14.
 */
public class SubscriptionImpl implements Subscription {

    private URL callback;
    private String eventType;
}
