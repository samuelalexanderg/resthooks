package com.byteflair.resthooks.store.mongo;

import com.byteflair.resthooks.domain.SubscriptionImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by dcerecedo on 16/04/14.
 */

@Document(collection="events")
public class SubscriptionMongoImpl extends SubscriptionImpl {

    @Id
    public String getId() {
        return super.getId();
    }
}
