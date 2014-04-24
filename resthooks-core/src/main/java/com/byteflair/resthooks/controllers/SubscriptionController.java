package com.byteflair.resthooks.controllers;

import com.byteflair.resthooks.api.Subscription;
import com.byteflair.resthooks.api.SubscriptionSpi;
import com.byteflair.resthooks.boundary.SubscriptionValidator;
import com.byteflair.resthooks.domain.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by dcerecedo on 1/24/14.
 */
public class SubscriptionController implements SubscriptionSpi {

    @Autowired
    SubscriptionService subscriptionService;

    @InitBinder
    public void initValidators(WebDataBinder binder) {
        binder.addValidators(new SubscriptionValidator());
    }

    @Override
    public ResponseEntity<List<Subscription>> getResourceCollection() {
        //TODO service error handling and mapping to RestApiException
        List<Subscription> subscriptions=new ArrayList<>();
        subscriptions.addAll(subscriptionService.getAll());
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Subscription> getResource(@PathVariable("id") String id) {
        //TODO service error handling and mapping to RestApiException
        Subscription subscription=subscriptionService.get(id);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Subscription> createResource(@RequestBody @Valid Subscription subscription) {
        //TODO service error handling and mapping to RestApiException
        HttpHeaders headers=new HttpHeaders();
        Method method=ReflectionUtils.findMethod(SubscriptionController.class, "getResource", String.class);
        headers.setLocation(linkTo(method, subscription.getId()).toUri());
        return new ResponseEntity<>(subscription, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Subscription> updateResource(@PathVariable("id") String id, @RequestBody @Valid Subscription subscription) {
        //TODO service error handling and mapping to RestApiException
        Subscription updatedSubscription=subscriptionService.update(subscription);
        return new ResponseEntity<>(updatedSubscription, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Subscription> deleteResource(@PathVariable("id") String id) {
        //TODO service error handling and mapping to RestApiException
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
