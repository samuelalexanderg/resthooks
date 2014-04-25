package com.byteflair.resthooks.domain;

import com.byteflair.resthooks.api.Subscription;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.data.annotation.Transient;

import java.net.URL;

/**
 * Created by dcerecedo on 1/19/14.
 */
public class SubscriptionImpl implements Subscription {

    @Transient
    private final DateTimeFormatter formatter=ISODateTimeFormat.dateTime().withZoneUTC();

    private String id;
    private URL callback;
    private String topic;
    private Integer maximunRetries=10;
    private DateTime createdAt;
    private DateTime updatedAt;

    public SubscriptionImpl() {
    }

    public SubscriptionImpl(String id, URL callback, String topic) {
        this.id=id;
        this.callback=callback;
        this.topic=topic;
    }

    public SubscriptionImpl(String id, URL callback, String topic, Integer retries) {
        this.id=id;
        this.callback=callback;
        this.topic=topic;
        this.maximunRetries=retries;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public String getTopic() {
        return topic;
    }

    public URL getCallback() {
        return callback;
    }

    public void setCallback(URL callback) {
        this.callback=callback;
    }

    public Integer getMaximunRetries() {
        return maximunRetries;
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

    public void setMaximunRetries(Integer maximunRetries) {
        this.maximunRetries=maximunRetries;
    }

    public void setTopic(String topic) {
        this.topic=topic;
    }

    @Override
    public int hashCode() {
        int result=id != null ? id.hashCode() : 0;
        result=31*result+(callback != null ? callback.hashCode() : 0);
        result=31*result+(topic != null ? topic.hashCode() : 0);
        result=31*result+(maximunRetries != null ? maximunRetries.hashCode() : 0);
        result=31*result+(createdAt != null ? createdAt.hashCode() : 0);
        result=31*result+(updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(!(o instanceof SubscriptionImpl)) {
            return false;
        }

        SubscriptionImpl that=(SubscriptionImpl) o;

        if(callback != null ? !callback.equals(that.callback) : that.callback != null) {
            return false;
        }
        if(createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) {
            return false;
        }
        if(id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if(maximunRetries != null ? !maximunRetries.equals(that.maximunRetries) : that.maximunRetries != null) {
            return false;
        }
        if(topic != null ? !topic.equals(that.topic) : that.topic != null) {
            return false;
        }
        if(updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "SubscriptionImpl{"+
              "id='"+id+'\''+
              ", callback="+callback+
              ", topic='"+topic+'\''+
              ", maximunRetries="+maximunRetries+
              ", createdAt="+formatter.print(createdAt)+
              ", updatedAt="+formatter.print(updatedAt)+
              '}';
    }
}
