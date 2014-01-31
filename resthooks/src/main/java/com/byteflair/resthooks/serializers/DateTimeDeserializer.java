package com.byteflair.resthooks.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;

/**
 * Created by dcerecedo on 1/24/14.
 */
public class DateTimeDeserializer extends JsonDeserializer<DateTime> {

    private final DateTimeFormatter formatter=ISODateTimeFormat.dateTime().withZoneUTC();

    @Override
    public DateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return formatter.parseDateTime(jsonParser.getValueAsString());
    }
}
