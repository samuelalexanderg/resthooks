package com.byteflair.resthooks.store.mongo;

import com.byteflair.resthooks.domain.EventImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by dcerecedo on 16/04/14.
 */

@Document(collection="events")
public class EventMongoImpl extends EventImpl {

    @Id
    public String getId() {
        return super.getId();
    }
}
