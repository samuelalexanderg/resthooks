package com.byteflair.resthooks.api;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by dcerecedo on 16/04/14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
      @ContextConfiguration("classpath:/inMemory/context-root.xml"),
      @ContextConfiguration("classpath:context-servlet.xml")
})
public class LogSpiInMemoryTest extends LogSpiTest {
}
