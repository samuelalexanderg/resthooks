package com.byteflair.resthooks.converters;

import com.byteflair.resthooks.Subscription;
import com.byteflair.resthooks.boundary.SubscriptionPostForm;
import com.byteflair.resthooks.model.SubscriptionImpl;
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
