package com.byteflair.resthooks.api;

import com.byteflair.resthooks.api.boundary.DateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;

/**
 * Created by dcerecedo on 1/24/14.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public interface Log {

    public Object getId();

    public LogLevel getLevel();

    public String getMessage();

    @JsonSerialize(using=DateTimeSerializer.class)
    public DateTime getCreatedAt();

}
