package com.byteflair.resthooks.conf.test;

import com.byteflair.resthooks.domain.services.EventRepository;
import com.byteflair.resthooks.domain.services.LogRepository;
import com.byteflair.resthooks.domain.services.SubscriptionRepository;
import com.byteflair.resthooks.store.EventInMemoryRepository;
import com.byteflair.resthooks.store.LogInMemoryRepository;
import com.byteflair.resthooks.store.SubscriptionInMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dcerecedo on 25/04/14.
 */

@Configuration
public class InMemoryRepositoryConfig {

    @Bean
    public LogRepository getLogRepository() {
        return new LogInMemoryRepository();
    }

    @Bean
    public EventRepository getEventRepository() {
        return new EventInMemoryRepository();
    }

    @Bean
    public SubscriptionRepository getSubscriptionRepository() {
        return new SubscriptionInMemoryRepository();
    }
}
