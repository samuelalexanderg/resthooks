package com.byteflair.resthooks.domain;

import com.byteflair.resthooks.api.Subscription;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Map;


/**
 * Created by dcerecedo on 1/24/14.
 * <p/>
 * This test requires the context-servlet.xml to build references to controllers,
 * becuase the serialization/deserialization process requires building links to
 * resources.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
      @ContextConfiguration("classpath:/inMemory/context-root.xml"),
      @ContextConfiguration("classpath:context-servlet.xml")
})
public class SubscriptionTest {

    private final static SecureRandom secureRandom=new SecureRandom();

    private final static Logger LOGGER=LoggerFactory.getLogger(SubscriptionTest.class);

    private Subscription createSubscription() throws URISyntaxException, MalformedURLException {
        URL url=new URI("http", null, "localhost", 8080, "/test-client", null, null).toURL();
        SubscriptionImpl subscription=new SubscriptionImpl(new BigInteger(25, secureRandom).toString(32), url, new BigInteger(25, secureRandom).toString(32));
        DateTime now=new DateTime();
        subscription.setUpdatedAt(now);
        subscription.setCreatedAt(now);
        return subscription;
    }

    @Test
    public void thatProperlySerializesToJson() throws Exception {
        Subscription subscription=createSubscription();

        ObjectMapper mapper=new ObjectMapper();
        DateTimeFormatter formatter=ISODateTimeFormat.dateTime().withZoneUTC();


        String json=mapper.writeValueAsString(subscription);
        /*
         * Must include all fields...
         */
        Assert.assertEquals(JsonPath.read(json, "id"), subscription.getId());
        Assert.assertEquals(JsonPath.read(json, "topic"), subscription.getTopic());
        Assert.assertEquals(JsonPath.read(json, "maximunRetries"), subscription.getMaximunRetries());
        Assert.assertEquals(JsonPath.read(json, "callback"), subscription.getCallback().toString());
        Assert.assertEquals(JsonPath.read(json, "updatedAt"), formatter.print(subscription.getUpdatedAt()));
        Assert.assertEquals(JsonPath.read(json, "createdAt"), formatter.print(subscription.getCreatedAt()));

        LOGGER.info("thatProperlySerializesToJson produced:\n{}", json);
    }

    @Test
    public void thatProperlyDeserializesFromJson() throws Exception {
        Subscription subscription=createSubscription();

        ObjectMapper mapper=new ObjectMapper();
        DateTimeFormatter formatter=ISODateTimeFormat.dateTime().withZoneUTC();


        String json=mapper.writeValueAsString(subscription);

        Subscription otherSubscription=mapper.readValue(json, SubscriptionImpl.class);

        areEqual(subscription, otherSubscription);

        LOGGER.info("thatProperlyDeserializesFromJson deserialized from\n{}\ninto...\n{}", json, otherSubscription);
    }

    @Test
    public void thatDeserializationDoesNotFailOnUnknownProperties() throws Exception {

        Subscription subscription=createSubscription();
        ObjectMapper mapper=new ObjectMapper();
        Map<String, Object> data=mapper.readValue(mapper.writeValueAsString(subscription), Map.class);
        data.put("unknownProperty", "some value");

        String json=mapper.writeValueAsString(data);

        Subscription oterSubscription=mapper.readValue(json, SubscriptionImpl.class);

        areEqual(subscription, oterSubscription);

        LOGGER.info("thatDeserializationDoesNotFailOnUnknownProperties deserialized:\n{}\ninto...\n{}", json, oterSubscription);

    }

    @Test
    public void thatHashCodeIsTheSameWithEqualPropertyValues() throws Exception {
        ObjectMapper mapper=new ObjectMapper();
        Subscription subscription=createSubscription();

        Map<String, Object> data=mapper.readValue(mapper.writeValueAsString(subscription), Map.class);
        Subscription otherSubscription;
        BeanWrapper wrapper=PropertyAccessorFactory.forBeanPropertyAccess(subscription);
        for(String key : data.keySet()) {
            otherSubscription=mapper.readValue(mapper.writeValueAsString(data), SubscriptionImpl.class);
            Assert.assertEquals(subscription.hashCode(), otherSubscription.hashCode());
            /**
             * Sequentially set each property to null, directly in the bean and to the
             * data map that will be deserialized back into a bean. The we compare both beans
             * to check that hashcode still works.
             */
            Object value=null;
            if(wrapper.isReadableProperty(key)) {
                Class clazz=wrapper.getPropertyDescriptor(key).getPropertyType();
                if(Number.class.isAssignableFrom(clazz)) {
                    value=new Integer(0);
                }
                wrapper.setPropertyValue(key, value);
                data.put(key, value);
            }
        }
        otherSubscription=mapper.readValue(mapper.writeValueAsString(data), SubscriptionImpl.class);
        Assert.assertEquals(subscription.hashCode(), otherSubscription.hashCode());
    }

    @Test
    public void thatEqualsReturnsTrueWithEqualPropertyValues() throws Exception {
        ObjectMapper mapper=new ObjectMapper();
        Subscription subscription=createSubscription();

        Map<String, Object> data=mapper.readValue(mapper.writeValueAsString(subscription), Map.class);
        Subscription otherSubscription;
        BeanWrapper wrapper=PropertyAccessorFactory.forBeanPropertyAccess(subscription);
        for(String key : data.keySet()) {
            otherSubscription=mapper.readValue(mapper.writeValueAsString(data), SubscriptionImpl.class);
            Assert.assertEquals(subscription, otherSubscription);
            /**
             * Sequentially set each property to null, directly in the bean and to the
             * data map that will be deserialized back into a bean. The we compare both beans
             * to check that hashcode still works.
             */
            Object value=null;
            if(wrapper.isReadableProperty(key)) {
                Class clazz=wrapper.getPropertyDescriptor(key).getPropertyType();
                if(Number.class.isAssignableFrom(clazz)) {
                    value=new Integer(0);
                }
                wrapper.setPropertyValue(key, value);
                data.put(key, value);
            }
        }
        otherSubscription=mapper.readValue(mapper.writeValueAsString(data), SubscriptionImpl.class);
        Assert.assertEquals(subscription, otherSubscription);
    }

    @Test
    public void thatHashCodeIsDifferentWithDifferentPropertyValues() {

    }

    @Test
    public void thatEqualsReturnsFalseWithDifferentPropertyValues() {

    }

    private void areEqual(Subscription subscription, Subscription otherSubscription) {
        Assert.assertEquals(otherSubscription.getId(), subscription.getId());
        Assert.assertEquals(otherSubscription.getTopic(), subscription.getTopic());
        Assert.assertEquals(otherSubscription.getMaximunRetries(), subscription.getMaximunRetries());
        Assert.assertEquals(otherSubscription.getCallback(), subscription.getCallback());
        Assert.assertTrue(otherSubscription.getCreatedAt().isEqual(subscription.getCreatedAt()));
        Assert.assertTrue(otherSubscription.getUpdatedAt().isEqual(subscription.getUpdatedAt()));

    }

}
