package com.byteflair.resthooks.domain;

import com.byteflair.resthooks.api.Event;
import com.byteflair.resthooks.api.EventStatus;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dcerecedo on 1/19/14.
 */
public class EventImpl implements Event {

    @Transient
    private final DateTimeFormatter formatter=ISODateTimeFormat.dateTime().withZoneUTC();

    private String id;
    private String type;
    private EventStatus status;
    private List<String> history=new ArrayList<>();
    private DateTime createdAt;

    public EventImpl() {
    }

    public EventImpl(String id, String type, EventStatus status) {
        this.id=id;
        this.type=type;
        this.status=status;
        this.createdAt=new DateTime();
    }

    public EventImpl(String id, String type, EventStatus status, List<String> history) {
        this(id, type, status);
        this.history=history;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type=type;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status=status;
    }

    public List<String> getHistory() {
        return new ArrayList<>(this.history);
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt=createdAt;
    }

    public void addHistory(String historyItem) {
        this.history.add(historyItem);
    }

    @Override
    public int hashCode() {
        int result=id != null ? id.hashCode() : 0;
        result=31*result+(type != null ? type.hashCode() : 0);
        result=31*result+(status != null ? status.hashCode() : 0);
        result=31*result+(history != null ? history.hashCode() : 0);
        result=31*result+(createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(!(o instanceof EventImpl)) {
            return false;
        }

        EventImpl event=(EventImpl) o;

        if(createdAt != null ? !createdAt.equals(event.createdAt) : event.createdAt != null) {
            return false;
        }
        if(history != null ? !history.equals(event.history) : event.history != null) {
            return false;
        }
        if(id != null ? !id.equals(event.id) : event.id != null) {
            return false;
        }
        if(status != event.status) {
            return false;
        }
        if(type != null ? !type.equals(event.type) : event.type != null) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "EventImpl{"+
              ", id="+id+
              ", type='"+type+'\''+
              ", status="+status+
              ", history="+history+
              ", createdAt="+formatter.print(createdAt)+
              '}';
    }
}
