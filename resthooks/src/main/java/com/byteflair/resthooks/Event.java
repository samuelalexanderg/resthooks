package com.byteflair.resthooks;

import com.byteflair.resthooks.domain.serializer.DateTimeDeserializer;
import com.byteflair.resthooks.domain.serializer.DateTimeSerializer;
import com.byteflair.resthooks.domain.serializer.HistoryDeserializer;
import com.byteflair.resthooks.domain.serializer.HistorySerializer;
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

    String getId();

    String getType();

    EventStatus getStatus();

    @JsonSerialize(using=HistorySerializer.class)
    @JsonDeserialize(using=HistoryDeserializer.class)
    List<String> getHistory();

    @JsonSerialize(using=DateTimeSerializer.class)
    @JsonDeserialize(using=DateTimeDeserializer.class)
    DateTime getCreatedAt();
}
