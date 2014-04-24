package com.byteflair.resthooks.domain;

import com.byteflair.resthooks.api.Log;
import com.byteflair.resthooks.api.LogLevel;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.data.annotation.Transient;

/**
 * Created by dcerecedo on 1/24/14.
 */
public class LogImpl implements Log {

    @Transient
    private final DateTimeFormatter formatter=ISODateTimeFormat.dateTime().withZoneUTC();

    private String id;
    private LogLevel level;
    private String message;
    private String event;
    private DateTime createdAt;

    public LogImpl() {
    }

    public LogImpl(String id, LogLevel level, String message, String event) {
        this.id=id;
        this.level=level;
        this.message=message;
        this.createdAt=new DateTime();
        this.event=event;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getEvent() {
        return event;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt=createdAt;
    }

    public void setEvent(String event) {
        this.event=event;
    }

    @Override
    public int hashCode() {
        int result=id != null ? id.hashCode() : 0;
        result=31*result+(level != null ? level.hashCode() : 0);
        result=31*result+(message != null ? message.hashCode() : 0);
        result=31*result+(event != null ? event.hashCode() : 0);
        result=31*result+(createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(!(o instanceof LogImpl)) {
            return false;
        }

        LogImpl log=(LogImpl) o;

        if(createdAt != null ? !createdAt.equals(log.createdAt) : log.createdAt != null) {
            return false;
        }
        if(event != null ? !event.equals(log.event) : log.event != null) {
            return false;
        }
        if(id != null ? !id.equals(log.id) : log.id != null) {
            return false;
        }
        if(level != log.level) {
            return false;
        }
        if(message != null ? !message.equals(log.message) : log.message != null) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "LogImpl{"+
              "id="+id+
              ", level="+level+
              ", message='"+message+'\''+
              ", event="+event+
              ", createdAt="+formatter.print(createdAt)+
              '}';
    }
}
