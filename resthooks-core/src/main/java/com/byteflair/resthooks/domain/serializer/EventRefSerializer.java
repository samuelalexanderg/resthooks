package com.byteflair.resthooks.domain.serializer;

import com.byteflair.resthooks.api.EventSpi;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by dcerecedo on 1/30/14.
 */
public class EventRefSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeString(this.getHypermediaForId(value));
    }

    private String getHypermediaForId(String id) {
        return linkTo(ReflectionUtils.findMethod(EventSpi.class, "getResource", String.class), id).withSelfRel().getHref();
    }
}
