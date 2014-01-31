package com.byteflair.resthooks.model;

import com.byteflair.resthooks.Event;
import com.byteflair.resthooks.EventStatus;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dcerecedo on 1/19/14.
 */
public class EventImpl implements Event {

    private final DateTimeFormatter formatter=ISODateTimeFormat.dateTime().withZoneUTC();

    private Object id;
    private String type;
    private EventStatus status;
    private List<Object> history=new ArrayList<>();
    private DateTime createdAt;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
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

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt=createdAt;
    }

    public List<Object> getHistory() {
        return Collections.unmodifiableList(this.history);
    }

    public void addHistory(Object historyItem) {
        this.history.add(historyItem);
    }

    @Override
    public String toString() {
        return "EventImpl{"+
              "formatter="+formatter+
              ", id="+id+
              ", type='"+type+'\''+
              ", status="+status+
              ", history="+history+
              ", createdAt="+formatter.print(createdAt)+
              '}';
    }
}
