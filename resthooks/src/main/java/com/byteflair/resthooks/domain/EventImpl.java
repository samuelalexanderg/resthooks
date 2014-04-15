package com.byteflair.resthooks.domain;

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

    private String id;
    private String type;
    private EventStatus status;
    private List<Object> history=new ArrayList<>();
    private DateTime createdAt;

    public EventImpl() {
    }

    public EventImpl(String id, String type, EventStatus status) {
        this.id=id;
        this.type=type;
        this.status=status;
        this.createdAt=new DateTime();
    }

    public EventImpl(String id, String type, EventStatus status, DateTime createdAt) {
        this.id=id;
        this.type=type;
        this.status=status;
        this.createdAt=createdAt;
    }

    public EventImpl(String id, String type, EventStatus status, List<Object> history) {
        this(id, type, status);
        this.history=history;
    }

    public EventImpl(String id, String type, EventStatus status, List<Object> history, DateTime createdAt) {
        this(id, type, status, createdAt);
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

    public List<Object> getHistory() {
        return Collections.unmodifiableList(this.history);
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt=createdAt;
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
