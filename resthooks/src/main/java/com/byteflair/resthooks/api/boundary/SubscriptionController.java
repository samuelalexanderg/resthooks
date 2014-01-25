package com.byteflair.resthooks.api.boundary;

import com.byteflair.resthooks.api.Subscription;
import com.byteflair.resthooks.api.SubscriptionSpi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by dcerecedo on 1/24/14.
 */
public class SubscriptionController implements SubscriptionSpi {

    @InitBinder
    private void initValidators(WebDataBinder binder) {
        binder.addValidators(new SubscriptionPostFormValidator(), new SubscriptionPutFormValidator());
    }

    @Override
    public ResponseEntity<List<Subscription>> getResourceCollection() {
        return null;
    }

    @Override
    public ResponseEntity<Subscription> getResource(@PathVariable("id") String id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Subscription>> createResource(@RequestBody @Valid SubscriptionPostForm form) {
        return null;
    }

    @Override
    public ResponseEntity<List<Subscription>> updateResource(@PathVariable("id") String id, @RequestBody @Valid SubscriptionPutForm form) {
        return null;
    }

    @Override
    public ResponseEntity<List<Subscription>> deleteResource(@PathVariable("id") String id) {
        return null;
    }
}
