package com.byteflair.resthooks.api.boundary;

import com.byteflair.resthooks.api.Subscription;
import com.byteflair.resthooks.api.SubscriptionSpi;
import com.byteflair.resthooks.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
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
    @Autowired
    ConversionService conversionService;

    @InitBinder
    private void initValidators(WebDataBinder binder) {
        binder.addValidators(new SubscriptionPostFormValidator(), new SubscriptionPutFormValidator());
    }

    @Override
    public ResponseEntity<List<Subscription>> getResourceCollection() {
        //TODO service error handling and mapping to RestApiException
        List<Subscription> subscriptions=new ArrayList<>();
        subscriptions.addAll(subscriptionService.getAll());
        return new ResponseEntity<List<Subscription>>(subscriptions, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Subscription> getResource(@PathVariable("id") String id) {
        //TODO service error handling and mapping to RestApiException
        Subscription subscription=subscriptionService.get(id);
        return new ResponseEntity<Subscription>(subscription, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Subscription> createResource(@RequestBody @Valid SubscriptionPostForm form) {
        //TODO service error handling and mapping to RestApiException
        Subscription subscription=subscriptionService.create(form);

        HttpHeaders headers=new HttpHeaders();
        Method method=ReflectionUtils.findMethod(SubscriptionController.class, "createResource", SubscriptionPostForm.class);
        headers.setLocation(linkTo(method, form).toUri());
        return new ResponseEntity<Subscription>(subscription, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Subscription> updateResource(@PathVariable("id") String id, @RequestBody @Valid SubscriptionPutForm form) {
        //TODO service error handling and mapping to RestApiException
        Subscription subscription=subscriptionService.update(form);
        return new ResponseEntity<Subscription>(subscription, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Subscription> deleteResource(@PathVariable("id") String id) {
        //TODO service error handling and mapping to RestApiException
        return new ResponseEntity<Subscription>(HttpStatus.NO_CONTENT);
    }
}
