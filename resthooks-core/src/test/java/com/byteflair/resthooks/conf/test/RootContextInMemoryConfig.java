package com.byteflair.resthooks.conf.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by dcerecedo on 25/04/14.
 */

@Configuration
@ComponentScan(basePackages={ "com.byteflair.resthooks.domain.services" })
@Import(InMemoryRepositoryConfig.class)
public class RootContextInMemoryConfig {
}
