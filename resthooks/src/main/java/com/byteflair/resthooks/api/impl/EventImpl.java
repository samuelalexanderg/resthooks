package com.byteflair.resthooks.api.impl;

import com.byteflair.resthooks.api.Event;
import com.byteflair.resthooks.api.EventStatus;
import org.joda.time.DateTime;

/**
 * Created by dcerecedo on 1/19/14.
 */
public class EventImpl implements Event {

    private Object id;
    private EventStatus status;
    private DateTime createdAt;
}
