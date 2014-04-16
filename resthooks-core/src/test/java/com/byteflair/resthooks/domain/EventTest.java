package com.byteflair.resthooks.domain;

import com.byteflair.resthooks.api.Event;
import com.byteflair.resthooks.api.EventStatus;
import com.byteflair.resthooks.api.LogSpi;
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
      @ContextConfiguration("classpath:context-root.xml"),
      @ContextConfiguration("classpath:context-servlet.xml")
})
public class EventTest {

    private final static SecureRandom secureRandom=new SecureRandom();

    private final static Logger LOG=LoggerFactory.getLogger(EventTest.class);

    private Event createEvent() {
        EventImpl event=new EventImpl();
        event.setId(new BigInteger(25, secureRandom).toString(32));
        event.setStatus(EventStatus.values()[secureRandom.nextInt(EventStatus.values().length)]);
        event.setType(new BigInteger(25, secureRandom).toString(32));
        for(int i=0; i<5; i++) {
            event.addHistory(new BigInteger(25, secureRandom).toString(32));
        }
        event.setCreatedAt(new DateTime());
        return event;
    }

    @Test
    public void thatProperlySerializesToJson() throws Exception {
        Event event=createEvent();

        ObjectMapper mapper=new ObjectMapper();
        DateTimeFormatter formatter=ISODateTimeFormat.dateTime().withZoneUTC();


        String json=mapper.writeValueAsString(event);
        /*
         * Must include all fields...
         */
        Assert.assertEquals(JsonPath.read(json, "id"), event.getId());
        Assert.assertEquals(JsonPath.read(json, "status"), event.getStatus().name());
        Assert.assertEquals(JsonPath.read(json, "type"), event.getType());
        Assert.assertEquals(JsonPath.read(json, "createdAt"), formatter.print(event.getCreatedAt()));
        int i=0;
        /*
        for(Object id : event.getHistory()) {
            Assert.assertEquals(JsonPath.read(json, "history["+i+"]"),
                  linkTo(ReflectionUtils.findMethod(LogSpi.class, "getResource", String.class), event.getHistory().get(i++)).withSelfRel().getHref()
            );
        }*/
        LOG.info("thatProperlySerializesToJson produced:\n{}", json);
    }

    @Test
    public void thatProperlyDeserializesFromJson() throws Exception {
        Event event=createEvent();

        ObjectMapper mapper=new ObjectMapper();
        DateTimeFormatter formatter=ISODateTimeFormat.dateTime().withZoneUTC();


        String json=mapper.writeValueAsString(event);

        Event otherEvent=mapper.readValue(json, EventImpl.class);
        /*
         * Must include all fields...
         */
        Assert.assertEquals(otherEvent.getId(), event.getId());
        Assert.assertEquals(otherEvent.getStatus(), event.getStatus());
        Assert.assertEquals(otherEvent.getType(), event.getType());
        Assert.assertTrue(otherEvent.getCreatedAt().isEqual(event.getCreatedAt()));
        int i=0;

        for(Object id : event.getHistory()) {
            Assert.assertEquals(JsonPath.read(json, "history["+i+"]"),
                  linkTo(ReflectionUtils.findMethod(LogSpi.class, "getResource", String.class), event.getHistory().get(i++)).withSelfRel().getHref()
            );
        }

        LOG.info("thatProperlyDeserializesFromJson deserialized from\n{}\ninto...\n{}", json, otherEvent);
    }

}
