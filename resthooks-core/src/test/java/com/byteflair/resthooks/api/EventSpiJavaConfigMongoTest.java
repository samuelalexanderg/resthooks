package com.byteflair.resthooks.api;

import com.byteflair.resthooks.conf.test.RootContextMongoConfig;
import com.byteflair.resthooks.conf.test.WebContextConfig;
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
      @ContextConfiguration(classes={ RootContextMongoConfig.class }),
      @ContextConfiguration(classes={ WebContextConfig.class })
})
public class EventSpiJavaConfigMongoTest extends EventSpiTest {
}
