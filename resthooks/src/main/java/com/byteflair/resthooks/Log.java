package com.byteflair.resthooks;

import com.byteflair.resthooks.serializers.DateTimeDeserializer;
import com.byteflair.resthooks.serializers.DateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;

/**
 * Created by dcerecedo on 1/24/14.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public interface Log {

    Object getId();

    LogLevel getLevel();

    String getMessage();

    @JsonSerialize(using=DateTimeSerializer.class)
    @JsonDeserialize(using=DateTimeDeserializer.class)
    DateTime getCreatedAt();

}
