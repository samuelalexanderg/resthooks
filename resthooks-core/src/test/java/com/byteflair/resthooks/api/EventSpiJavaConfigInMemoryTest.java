package com.byteflair.resthooks.api;

import com.byteflair.resthooks.conf.test.RootContextInMemoryConfig;
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
      @ContextConfiguration(classes={ RootContextInMemoryConfig.class }),
      @ContextConfiguration(classes={ WebContextConfig.class })
})
public class EventSpiJavaConfigInMemoryTest extends EventSpiTest {
}
