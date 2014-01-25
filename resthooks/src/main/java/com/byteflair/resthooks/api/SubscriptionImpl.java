package com.byteflair.resthooks.api;

import org.joda.time.DateTime;

import java.net.URL;

/**
 * Created by dcerecedo on 1/19/14.
 */
public class SubscriptionImpl implements Subscription {

    private Object id;
    private URL callback;
    private String topic;
    private int maximunRetries;


    private DateTime createdAt;
    private DateTime updatedAt;

}
