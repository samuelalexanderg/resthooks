package com.byteflair.resthooks.api.impl;

import com.byteflair.resthooks.api.Subscription;
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

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id=id;
    }

    public URL getCallback() {
        return callback;
    }

    public void setCallback(URL callback) {
        this.callback=callback;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic=topic;
    }

    public int getMaximunRetries() {
        return maximunRetries;
    }

    public void setMaximunRetries(int maximunRetries) {
        this.maximunRetries=maximunRetries;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt=createdAt;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(DateTime updatedAt) {
        this.updatedAt=updatedAt;
    }
}
