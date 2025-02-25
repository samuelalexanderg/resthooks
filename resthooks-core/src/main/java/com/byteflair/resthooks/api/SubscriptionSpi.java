/*******************************************************************************
 * Copyright (c), 2014, Byteflair S.L. Code contributed by Daniel Cerecedo.
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License version 3 as published by the Free Software Foundation.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GPLv3 for more details.
 ******************************************************************************/

package com.byteflair.resthooks.api;

import com.byteflair.rest.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by dcerecedo on 1/19/14.
 */
@Controller
@RequestMapping(value="/subscriptions", headers="Accept=application/json", produces="application/json")
public interface SubscriptionSpi {

    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    ResponseEntity<List<Subscription>> getResourceCollection();

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    @ResponseBody
    ResponseEntity<Subscription> getResource(@PathVariable("id") String id);

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    ResponseEntity<Subscription> createResource(Subscription form);

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    @ResponseBody
    ResponseEntity<Subscription> updateResource(@PathVariable("id") String id, Subscription form) throws ResourceNotFoundException;

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    ResponseEntity<Subscription> deleteResource(@PathVariable("id") String id);

}
