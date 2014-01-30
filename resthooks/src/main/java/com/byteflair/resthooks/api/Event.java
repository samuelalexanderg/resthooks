package com.byteflair.resthooks.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by dcerecedo on 1/19/14.
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public interface Event {

    public Object getId();

    public String getType();

    public EventStatus getStatus();

    @JsonSerialize(using=HistorySerializer.class)
    @JsonDeserialize(using=HistoryDeserializer.class)
    public List<Object> getHistory();

    @JsonSerialize(using=DateTimeSerializer.class)
    @JsonDeserialize(using=DateTimeDeserializer.class)
    public DateTime getCreatedAt();
}
