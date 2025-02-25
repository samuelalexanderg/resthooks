package com.byteflair.resthooks.api;

import com.byteflair.resthooks.domain.EventImpl;
import com.byteflair.resthooks.domain.LogImpl;
import com.byteflair.resthooks.domain.services.EventRepository;
import com.byteflair.resthooks.domain.services.LogRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dcerecedo on 1/24/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
      @ContextConfiguration("classpath:context-root.xml"),
      @ContextConfiguration("classpath:context-servlet.xml")
})

public class EventSpiTest {

    private final static Logger LOGGER=LoggerFactory.getLogger(EventSpiTest.class);
    private final SecureRandom secureRandom=new SecureRandom();
    private final List<Event> testEvents=new ArrayList<>();
    private final List<Log> testLogs=new ArrayList<>();
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private EventRepository eventRepository;
    private MockMvc mockMvc;

    private Event createEvent() {
        EventImpl event=new EventImpl(new BigInteger(25, secureRandom).toString(32),
              new BigInteger(25, secureRandom).toString(32),
              EventStatus.values()[secureRandom.nextInt(EventStatus.values().length)]
        );
        return event;
    }

    private Log createLog() {
        Log log=new LogImpl(new BigInteger(16, secureRandom).toString(),
              LogLevel.values()[secureRandom.nextInt(LogLevel.values().length)],
              new BigInteger(130, secureRandom).toString(32),
              new BigInteger(16, secureRandom).toString()
        );
        return log;
    }

    private Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter=new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        return new Filter[] { characterEncodingFilter };
    }

    @Before
    public void setup() {
        this.mockMvc=MockMvcBuilders.webAppContextSetup(this.wac)
              .addFilters(getServletFilters())
              .build();

        for(int i=0; i<10; i++) {
            testEvents.add(createEvent());
            for(int j=0; j<5; j++) {
                testLogs.add(createLog());
                logRepository.save(testLogs.get(i));
                ((EventImpl) testEvents.get(i)).addHistory(testLogs.get(j).getId());
            }
            eventRepository.save(testEvents.get(i));
        }
    }

    @After
    public void clean() {
        for(Event event : testEvents) {
            eventRepository.delete(event.getId());
        }
        for(Log log : testLogs) {
            logRepository.delete(log.getId());
        }
    }

    @Test
    public void thatCanGetAllEvents() throws Exception {
        ResultActions result=this.mockMvc.perform(MockMvcRequestBuilders.get("/events").accept(new MediaType("application", "json")));
        result.andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        String json=result.andReturn().getResponse().getContentAsString();
        ObjectMapper mapper=new ObjectMapper();
        List<Event> events=mapper.readValue(json, new TypeReference<List<EventImpl>>() {});
        Assert.assertTrue(events.size() == 10);
        for(Event event : events) {
            Event other=eventRepository.findOne(event.getId());
            Assert.assertEquals(event.getId(), other.getId());
            Assert.assertEquals(event.getType(), other.getType());
            Assert.assertEquals(event.getStatus(), other.getStatus());
            Assert.assertTrue(event.getCreatedAt().isEqual(other.getCreatedAt()));

            Assert.assertEquals(event.getHistory().size(), other.getHistory().size());
            for(int i=0; i<event.getHistory().size(); i++) {
                Assert.assertEquals(event.getHistory().get(i), other.getHistory().get(i));
            }

        }
    }

    @Test
    public void thatCanGetSingleEvent() throws Exception {
        ObjectMapper mapper=new ObjectMapper();
        for(Event event : testEvents) {
            ResultActions result=this.mockMvc.perform(MockMvcRequestBuilders.get("/events/"+event.getId()).accept(new MediaType("application", "json")));
            result.andExpect(MockMvcResultMatchers.status().isOk())
                  .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
            String json=result.andReturn().getResponse().getContentAsString();
            Event other=mapper.readValue(json, EventImpl.class);
            Assert.assertEquals(event.getId(), other.getId());
            Assert.assertEquals(event.getType(), other.getType());
            Assert.assertEquals(event.getStatus(), other.getStatus());
            Assert.assertTrue(event.getCreatedAt().isEqual(other.getCreatedAt()));

            Assert.assertEquals(event.getHistory().size(), other.getHistory().size());
            for(int i=0; i<event.getHistory().size(); i++) {
                Assert.assertEquals(event.getHistory().get(i), other.getHistory().get(i));
            }

        }
    }

}
