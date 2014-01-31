package com.byteflair.resthooks.model;

import com.byteflair.resthooks.Log;
import com.byteflair.resthooks.LogLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Created by dcerecedo on 1/24/14.
 */
public class LogImpl implements Log {

    private final DateTimeFormatter formatter=ISODateTimeFormat.dateTime().withZoneUTC();

    private Object id;
    private LogLevel level;
    private String message;
    private Object eventId;
    private DateTime createdAt;

    public LogImpl() {
    }

    public LogImpl(Object id, LogLevel level, String message, Object eventId) {
        this.id=id;
        this.level=level;
        this.message=message;
        this.createdAt=new DateTime();
        this.eventId=eventId;
    }

    public LogImpl(Object id, LogLevel level, String message, Object eventId, DateTime createdAt) {
        this.id=id;
        this.level=level;
        this.message=message;
        this.createdAt=createdAt;
        this.eventId=eventId;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id=id;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level=level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message=message;
    }

    @JsonIgnore
    public Object getEventId() {
        return eventId;
    }

    public void setEventId(Object eventId) {
        this.eventId=eventId;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }


    @Override
    public String toString() {
        return "LogImpl{"+
              "id="+id+
              ", level="+level+
              ", message='"+message+'\''+
              ", eventId="+eventId+
              ", createdAt="+formatter.print(createdAt)+
              '}';
    }
}
