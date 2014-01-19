/*******************************************************************************
 * Copyright (c), 2014, Byteflair S.L. Code contributed by Daniel Cerecedo.
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License version 3 as published by the Free Software Foundation.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GPLv3 for more details.
 ******************************************************************************/

package com.byteflair.resthooks.api;

import com.byteflair.resthooks.api.boundary.SubscriptionPostForm;
import com.byteflair.resthooks.api.boundary.SubscriptionPutForm;
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
@RequestMapping("/subscriptions")
public interface SubscriptionSpi {

    @RequestMapping(method=RequestMethod.GET)
    public
    @ResponseBody
    List<Subscription> getResourceCollection();

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public
    @ResponseBody
    Subscription getResource(@PathVariable("id") String id);

    @RequestMapping(method=RequestMethod.POST)
    public
    @ResponseBody
    List<Subscription> createResource(SubscriptionPostForm form);

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public
    @ResponseBody
    List<Subscription> updateResource(@PathVariable("id") String id, SubscriptionPutForm form);

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public
    @ResponseBody
    List<Subscription> deleteResource(@PathVariable("id") String id);

}
