package com.byteflair.resthooks.boundary;

import com.byteflair.resthooks.Subscription;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by dcerecedo on 1/24/14.
 */

public class SubscriptionValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Subscription.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object form, Errors errors) {
        Subscription subscription=(Subscription) form;
        if(false) {
            //TODO
            errors.reject("aKey", "aMessage");
        }
    }
}
