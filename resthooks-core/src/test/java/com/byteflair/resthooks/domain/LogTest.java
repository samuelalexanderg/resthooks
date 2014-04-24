package com.byteflair.resthooks.domain;

import com.byteflair.resthooks.api.EventSpi;
import com.byteflair.resthooks.api.Log;
import com.byteflair.resthooks.api.LogLevel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.ReflectionUtils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


/**
 * Created by dcerecedo on 1/24/14.
 * <p/>
 * This test requires the context-servlet.xml to build references to controllers,
 * becuase the serialization/deserialization process requires building links to
 * resources.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
      @ContextConfiguration("classpath:/inMemory/context-root.xml"),
      @ContextConfiguration("classpath:context-servlet.xml")
})
public class LogTest {

    private final static Logger LOGGER=LoggerFactory.getLogger(LogTest.class);

    private final static SecureRandom secureRandom=new SecureRandom();

    private Log createLog() {
        LogImpl log=new LogImpl(new BigInteger(25, secureRandom).toString(32),
              LogLevel.values()[secureRandom.nextInt(LogLevel.values().length)],
              new BigInteger(25, secureRandom).toString(32),
              new BigInteger(25, secureRandom).toString(32));
        log.setCreatedAt(new DateTime());
        return log;
    }

    @Test
    public void thatProperlySerializesToJson() throws Exception {

        ObjectMapper mapper=new ObjectMapper();
        DateTimeFormatter formatter=ISODateTimeFormat.dateTime().withZoneUTC();

        Log log=createLog();
        String json=mapper.writeValueAsString(log);
        /*
         * Must include all fields...
         */
        Assert.assertEquals(JsonPath.read(json, "id"), log.getId());
        Assert.assertEquals(JsonPath.read(json, "level"), log.getLevel().toString());
        Assert.assertEquals(JsonPath.read(json, "message"), log.getMessage());
        Assert.assertEquals(JsonPath.read(json, "createdAt"), formatter.print(log.getCreatedAt()));
        Assert.assertEquals(JsonPath.read(json, "event"), linkTo(ReflectionUtils.findMethod(EventSpi.class, "getResource", String.class), log.getEvent()).withSelfRel().getHref());

        LOGGER.info("thatProperlySerializesToJson produced:\n{}", json);
    }

    @Test
    public void thatProperlyDeserializesFromJson() throws Exception {
        Log log=createLog();

        DateTimeFormatter formatter=ISODateTimeFormat.dateTime().withZoneUTC();

        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(log);

        Log otherLog=mapper.readValue(json, LogImpl.class);
        /*
         * Must include all fields...
         */
        Assert.assertEquals(otherLog.getId(), log.getId());
        Assert.assertEquals(otherLog.getLevel(), log.getLevel());
        Assert.assertEquals(otherLog.getMessage(), log.getMessage());
        Assert.assertTrue(otherLog.getCreatedAt().isEqual(log.getCreatedAt()));
        Assert.assertEquals(otherLog.getEvent(), log.getEvent());

        LOGGER.info("thatProperlyDeserializesFromJson deserialized:\n{}\ninto...\n{}", json, otherLog);
    }

    @Test
    public void thatDeserializationDoesNotFailOnUnknownProperties() throws Exception {
        Log log=createLog();
        ObjectMapper mapper=new ObjectMapper();
        Map<String, Object> data=mapper.readValue(mapper.writeValueAsString(log), Map.class);

        DateTimeFormatter formatter=ISODateTimeFormat.dateTime().withZoneUTC();

        data.put("unknownProperty", "anyVaue");

        String json=mapper.writeValueAsString(data);

        Log otherLog=mapper.readValue(json, LogImpl.class);
        /*
         * Must include all fields...
         */
        Assert.assertEquals(otherLog.getId(), log.getId());
        Assert.assertEquals(otherLog.getLevel(), log.getLevel());
        Assert.assertEquals(otherLog.getMessage(), log.getMessage());
        Assert.assertTrue(otherLog.getCreatedAt().isEqual(log.getCreatedAt()));
        Assert.assertEquals(otherLog.getEvent(), log.getEvent());

        LOGGER.info("thatDeserializationDoesNotFailOnUnknownProperties deserialized:\n{}\ninto...\n{}", json, otherLog);

    }
}
