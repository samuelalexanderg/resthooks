package com.byteflair.resthooks.store.mongo;

import com.byteflair.resthooks.domain.LogImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by dcerecedo on 16/04/14.
 */

@Document(collection="logs")
public class LogMongoImpl extends LogImpl {

    @Id
    public String getId() {
        return super.getId();
    }
}
