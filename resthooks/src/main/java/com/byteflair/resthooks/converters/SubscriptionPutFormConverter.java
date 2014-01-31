package com.byteflair.resthooks.converters;

import com.byteflair.resthooks.Subscription;
import com.byteflair.resthooks.boundary.SubscriptionPutForm;
import com.byteflair.resthooks.model.SubscriptionImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by dcerecedo on 1/24/14.
 */
public class SubscriptionPutFormConverter implements Converter<SubscriptionPutForm, Subscription> {

    @Override
    public Subscription convert(SubscriptionPutForm subscriptionPutForm) {
        Subscription subscription=new SubscriptionImpl();
        BeanUtils.copyProperties(subscriptionPutForm, subscription);
        return subscription;
    }
}
