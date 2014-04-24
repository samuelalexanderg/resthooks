package com.byteflair.resthooks.api;

import com.byteflair.resthooks.domain.serializer.DateTimeDeserializer;
import com.byteflair.resthooks.domain.serializer.DateTimeSerializer;
import com.byteflair.resthooks.domain.serializer.EventRefDeserializer;
import com.byteflair.resthooks.domain.serializer.EventRefSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;

/**
 * Created by dcerecedo on 1/24/14.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public interface Log {

    String getId();

    LogLevel getLevel();

    String getMessage();

    @JsonSerialize(using=EventRefSerializer.class)
    @JsonDeserialize(using=EventRefDeserializer.class)
    String getEvent();

    @JsonSerialize(using=DateTimeSerializer.class)
    @JsonDeserialize(using=DateTimeDeserializer.class)
    DateTime getCreatedAt();

}
