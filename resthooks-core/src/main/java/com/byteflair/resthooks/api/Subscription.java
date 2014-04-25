package com.byteflair.resthooks.api;

import com.byteflair.resthooks.domain.serializer.DateTimeDeserializer;
import com.byteflair.resthooks.domain.serializer.DateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;

import java.net.URL;

/**
 * Created by dcerecedo on 1/19/14.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public interface Subscription {

    String getId();

    String getTopic();

    URL getCallback();

    Integer getMaximunRetries();

    @JsonSerialize(using=DateTimeSerializer.class)
    @JsonDeserialize(using=DateTimeDeserializer.class)
    DateTime getCreatedAt();

    @JsonSerialize(using=DateTimeSerializer.class)
    @JsonDeserialize(using=DateTimeDeserializer.class)
    DateTime getUpdatedAt();
}

