package com.byteflair.resthooks.domain.test;

import com.byteflair.resthooks.api.Log;
import com.byteflair.resthooks.api.LogImpl;
import com.byteflair.resthooks.api.LogLevel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
}
