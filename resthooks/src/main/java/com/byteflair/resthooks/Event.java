package com.byteflair.resthooks;

import com.byteflair.resthooks.serializers.DateTimeDeserializer;
import com.byteflair.resthooks.serializers.DateTimeSerializer;
import com.byteflair.resthooks.serializers.HistoryDeserializer;
import com.byteflair.resthooks.serializers.HistorySerializer;
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

    Object getId();

    String getType();

    EventStatus getStatus();

    @JsonSerialize(using=HistorySerializer.class)
    @JsonDeserialize(using=HistoryDeserializer.class)
    List<Object> getHistory();

    @JsonSerialize(using=DateTimeSerializer.class)
    @JsonDeserialize(using=DateTimeDeserializer.class)
    DateTime getCreatedAt();
}
