package com.byteflair.resthooks.api.boundary;

import com.byteflair.resthooks.api.Subscription;
import com.byteflair.resthooks.api.SubscriptionImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by dcerecedo on 1/24/14.
 */
public class SubscriptionPostFormConverter implements Converter<SubscriptionPostForm, Subscription> {

    @Override
    public Subscription convert(SubscriptionPostForm subscriptionPostForm) {
        Subscription subscription=new SubscriptionImpl();
        BeanUtils.copyProperties(subscriptionPostForm, subscription);
        return subscription;
    }
}
