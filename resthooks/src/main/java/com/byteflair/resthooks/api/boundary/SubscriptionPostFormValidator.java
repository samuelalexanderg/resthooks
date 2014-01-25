package com.byteflair.resthooks.api.boundary;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by dcerecedo on 1/24/14.
 */

public class SubscriptionPostFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return SubscriptionPostForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object form, Errors errors) {
        SubscriptionPostForm eventPostForm=(SubscriptionPostForm) form;
        if(false) {
            //TODO
            errors.reject("aKey", "aMessage");
        }
    }
}
