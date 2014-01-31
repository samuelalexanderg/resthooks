package com.byteflair.resthooks.test;

import com.byteflair.rest.exceptions.NotFoundException;
import com.byteflair.rest.exceptions.RestApiException;
import com.byteflair.resthooks.Log;
import com.byteflair.resthooks.LogLevel;
import com.byteflair.resthooks.model.LogImpl;
import com.byteflair.resthooks.services.LogRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dcerecedo on 1/26/14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:integration-test.xml")
public class LogSpiTest {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private LogRepository logRepository;
    private MockMvc mockMvc;
    private final SecureRandom secureRandom=new SecureRandom();
    private final List<Log> testLogs=new ArrayList<>();

    @Before
    public void setup() {
        this.mockMvc=MockMvcBuilders.webAppContextSetup(this.wac).build();
        for(int i=0; i<10; i++) {
            testLogs.add(createLog());
            logRepository.save(testLogs.get(i));
        }
    }

    @After
    public void clean() {
        for(Log log : testLogs) {
            logRepository.delete(log.getId());
        }
    }

    private Log createLog() {
        Log log=new LogImpl(new BigInteger(16, secureRandom).toString(), LogLevel.INFO, new BigInteger(130, secureRandom).toString(32), new BigInteger(16, secureRandom).toString());
        return log;
    }

    @Test
    public void thatCanGetAllLogEntries() throws Exception {
        ResultActions result=this.mockMvc.perform(get("/logs").accept(new MediaType("application", "json")));
        result.andExpect(status().isOk())
              .andExpect(content().contentType("application/json"));
        String json=result.andReturn().getResponse().getContentAsString();
        ObjectMapper mapper=new ObjectMapper();
        List<Log> logs=mapper.readValue(json, new TypeReference<List<LogImpl>>() {});
        assertTrue(logs.size() == 10);
        for(Log log : logs) {
            Log other=logRepository.findOne(log.getId());
            assertEquals(log.getId(), other.getId());
            assertEquals(log.getLevel(), other.getLevel());
            assertEquals(log.getMessage(), other.getMessage());
            assertTrue(log.getCreatedAt().isEqual(other.getCreatedAt()));
        }
    }

    @Test
    public void thatCanGetSingleLogEntry() throws Exception {
        ObjectMapper mapper=new ObjectMapper();
        for(Log log : testLogs) {
            ResultActions result=this.mockMvc.perform(get("/logs/"+log.getId()).accept(new MediaType("application", "json")));
            result.andExpect(status().isOk())
                  .andExpect(content().contentType("application/json"));
            String json=result.andReturn().getResponse().getContentAsString();
            Log other=mapper.readValue(json, LogImpl.class);
            assertEquals(log.getId(), other.getId());
            assertEquals(log.getLevel(), other.getLevel());
            assertEquals(log.getMessage(), other.getMessage());
            assertTrue(log.getCreatedAt().isEqual(other.getCreatedAt()));
        }
    }

    @Test
    public void thatThrowsNotFoundException() throws Exception {
        String id=(String) logRepository.findAll().get(0).getId();
        logRepository.delete(id);
        ResultActions result=this.mockMvc.perform(get("/logs/"+id).accept(new MediaType("application", "json")));
        result.andExpect(status().isNotFound())
              .andExpect(content().contentType("application/json"));
        String json=result.andReturn().getResponse().getContentAsString();
        ObjectMapper mapper=new ObjectMapper();
        RestApiException exception=mapper.readValue(json, NotFoundException.class);
        assertEquals(exception.getStatus(), HttpStatus.NOT_FOUND);
    }
}
