package com.byteflair.resthooks.store.mongo;

import com.byteflair.resthooks.api.EventStatus;
import com.byteflair.resthooks.domain.EventImpl;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dcerecedo on 16/04/14.
 */

@Document(collection = "events")
public class EventMongoImpl extends EventImpl{

    @Id
    public String getId(){
        return super.getId();
    }
}
