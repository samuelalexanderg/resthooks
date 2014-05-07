package com.byteflair.resthooks.conf.test;

import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoOptionsFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by dcerecedo on 7/05/14.
 */
@Configuration
@PropertySource("classpath:/mongo/mongo.properties")
@EnableMongoRepositories(basePackages={ "com.byteflair.resthooks.store.mongo" })
public class MongoConfiguration {

    @Autowired
    Environment environment;

    public MongoOptions getMongoOptions() {
        MongoOptionsFactoryBean factoryBean=new MongoOptionsFactoryBean();
        factoryBean.setConnectionsPerHost(8);
        factoryBean.setThreadsAllowedToBlockForConnectionMultiplier(4);
        factoryBean.setConnectTimeout(15000);
        factoryBean.setMaxWaitTime(1500);
        factoryBean.setAutoConnectRetry(true);
        factoryBean.setSocketKeepAlive(true);
        factoryBean.setSocketTimeout(60000);
        factoryBean.setSlaveOk(true);
        factoryBean.setWriteNumber(1);
        factoryBean.setWriteTimeout(0);
        factoryBean.setWriteFsync(false);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    public Mongo getMongoFactoryBean(String host, int port) throws Exception {
        MongoFactoryBean factoryBean=new MongoFactoryBean();
        factoryBean.setHost(host);
        factoryBean.setPort(port);
        factoryBean.setMongoOptions(getMongoOptions());
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    public
    @Bean
    MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate=new MongoTemplate(
              getMongoFactoryBean(
                    environment.getProperty("mongo.hostname"),
                    Integer.valueOf(environment.getProperty("mongo.port"))),
              environment.getProperty("mongo.dbname")
        );
        return mongoTemplate;

    }
}
