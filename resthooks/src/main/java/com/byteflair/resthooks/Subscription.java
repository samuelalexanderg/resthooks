package com.byteflair.resthooks;

import com.byteflair.resthooks.domain.serializer.DateTimeDeserializer;
import com.byteflair.resthooks.domain.serializer.DateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;

/**
 * Created by dcerecedo on 1/19/14.
 */
public interface Subscription {

    String getId();

    @JsonSerialize(using=DateTimeSerializer.class)
    @JsonDeserialize(using=DateTimeDeserializer.class)
    DateTime getCreatedAt();
}
