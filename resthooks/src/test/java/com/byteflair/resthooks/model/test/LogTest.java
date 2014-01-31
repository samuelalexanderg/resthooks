package com.byteflair.resthooks.model.test;

import com.byteflair.resthooks.Log;
import com.byteflair.resthooks.LogLevel;
import com.byteflair.resthooks.model.LogImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by dcerecedo on 1/24/14.
 */
public class LogTest {

    private final static Logger LOG=LoggerFactory.getLogger(LogTest.class);

    @Test
    public void thatProperlySerializesToJson() throws Exception {
        String logId="1234";
        LogLevel level=LogLevel.INFO;
        String message="a simple message";
        String eventId="6789";
        DateTime createdAt=new DateTime();

        ObjectMapper mapper=new ObjectMapper();
        DateTimeFormatter formatter=ISODateTimeFormat.dateTime().withZoneUTC();

        Log logEntry=new LogImpl(logId, level, message, eventId, createdAt);
        String json=mapper.writeValueAsString((Log) logEntry);
        /*
         * Must include all fields...
         */
        Assert.assertEquals(JsonPath.read(json, "id"), logId);
        Assert.assertEquals(JsonPath.read(json, "level"), level.toString());
        Assert.assertEquals(JsonPath.read(json, "message"), message);
        Assert.assertEquals(JsonPath.read(json, "createdAt"), formatter.print(createdAt));
        /**
         * ...except eventId
         */
        Assert.assertNull(JsonPath.read(json, "eventId"));

        LOG.info("thatProperlySerializesToJson produced:\n{}", json);
    }

    @Test
    public void thatProperlyDeserializesFromJson() throws Exception {
        String logId="1234";
        LogLevel level=LogLevel.INFO;
        String message="a simple message";
        String eventId="6789";
        DateTime createdAt=new DateTime();

        DateTimeFormatter formatter=ISODateTimeFormat.dateTime().withZoneUTC();

        Map<String, String> data=new HashMap<>();
        data.put("id", logId);
        data.put("level", level.toString());
        data.put("message", message);
        data.put("createdAt", formatter.print(createdAt));

        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(data);

        Log logEntry=mapper.readValue(json, LogImpl.class);
        /*
         * Must include all fields...
         */
        Assert.assertEquals(logEntry.getId(), logId);
        Assert.assertEquals(logEntry.getLevel(), level);
        Assert.assertEquals(logEntry.getMessage(), message);
        Assert.assertTrue(logEntry.getCreatedAt().isEqual(createdAt));
        /**
         * ...except eventId
         */
        Assert.assertNull(JsonPath.read(json, "eventId"));

        LOG.info("thatProperlyDeserializesFromJson deserialized:\n{}\ninto...\n{}", json, logEntry);
    }

    @Test
    public void thatDeserializationDoesNotFailOnUnknownProperties() throws Exception {
        String logId="1234";
        LogLevel level=LogLevel.INFO;
        String message="a simple message";
        String eventId="6789";
        DateTime createdAt=new DateTime();

        DateTimeFormatter formatter=ISODateTimeFormat.dateTime().withZoneUTC();

        Map<String, String> data=new HashMap<>();
        data.put("id", logId);
        data.put("level", level.toString());
        data.put("message", message);
        data.put("createdAt", formatter.print(createdAt));
        data.put("unknownProperty", "anyVaue");

        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(data);

        Log logEntry=mapper.readValue(json, LogImpl.class);
        /*
         * Must include all fields...
         */
        Assert.assertEquals(logEntry.getId(), logId);
        Assert.assertEquals(logEntry.getLevel(), level);
        Assert.assertEquals(logEntry.getMessage(), message);
        Assert.assertTrue(logEntry.getCreatedAt().isEqual(createdAt));
        /**
         * ...except eventId
         */
        Assert.assertNull(JsonPath.read(json, "eventId"));

        LOG.info("thatDeserializationDoesNotFailOnUnknownProperties deserialized:\n{}\ninto...\n{}", json, logEntry);

    }
}
