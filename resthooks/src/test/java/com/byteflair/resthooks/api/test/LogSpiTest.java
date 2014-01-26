package com.byteflair.resthooks.api.test;

import com.byteflair.resthooks.api.Log;
import com.byteflair.resthooks.api.LogImpl;
import com.byteflair.resthooks.api.LogLevel;
import com.byteflair.resthooks.services.LogRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
    private SecureRandom secureRandom=new SecureRandom();

    @Before
    public void setup() {
        this.mockMvc=MockMvcBuilders.webAppContextSetup(this.wac).build();
        for(int i=0; i<10; i++) {
            logRepository.save(createLogInstance());
        }
    }

    private Log createLogInstance() {
        Log log=new LogImpl(new BigInteger(16, secureRandom).toString(), LogLevel.INFO, new BigInteger(130, secureRandom).toString(32), new BigInteger(16, secureRandom).toString(), new DateTime());
        return log;
    }

    @Test
    public void getLogEntries() throws Exception {
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

}
