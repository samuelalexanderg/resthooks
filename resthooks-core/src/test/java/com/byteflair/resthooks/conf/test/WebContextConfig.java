package com.byteflair.resthooks.conf.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by dcerecedo on 25/04/14.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={ "com.byteflair.resthooks.api", "com.byteflair.resthooks.controllers", "com.byteflair.rest.exceptions" })
public class WebContextConfig {
}
