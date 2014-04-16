package com.byteflair.resthooks.domain.serializer;

import com.byteflair.resthooks.api.LogSpi;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by dcerecedo on 1/30/14.
 */
public class HistorySerializer extends JsonSerializer<List<String>> {

    @Override
    public void serialize(List<String> value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeStartArray();
        for(Object id : value) {
            jsonGenerator.writeString(this.getHypermediaForId(id.toString()));
        }
        jsonGenerator.writeEndArray();
    }

    private String getHypermediaForId(String id) {
        return linkTo(ReflectionUtils.findMethod(LogSpi.class, "getResource", String.class), id).withSelfRel().getHref();
    }
}
