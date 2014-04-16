package com.byteflair.resthooks.domain;

import com.byteflair.resthooks.api.Subscription;
import org.joda.time.DateTime;

import java.net.URL;

/**
 * Created by dcerecedo on 1/19/14.
 */
public class SubscriptionImpl implements Subscription {

    private String id;
    private URL callback;
    private String topic;
    private int maximunRetries;
    private DateTime createdAt;
    private DateTime updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt=createdAt;
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

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(DateTime updatedAt) {
        this.updatedAt=updatedAt;
    }
}
