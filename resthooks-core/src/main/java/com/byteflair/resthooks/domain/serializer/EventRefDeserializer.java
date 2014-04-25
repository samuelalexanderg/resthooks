package com.byteflair.resthooks.domain.serializer;

import com.byteflair.resthooks.api.EventSpi;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by dcerecedo on 1/24/14.
 */
public class EventRefDeserializer extends JsonDeserializer<String> {

    private static Logger LOGGER=LoggerFactory.getLogger(EventRefDeserializer.class);
    private static Pattern pattern;

    static {
        pattern=Pattern.compile(
              linkTo(ReflectionUtils.findMethod(EventSpi.class, "getResource", String.class), "").withSelfRel().getHref()+
                    "/([^\\s^/]+)"
        );
    }

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String hypermedia=jsonParser.readValueAs(new TypeReference<String>() {});
        Matcher matcher=pattern.matcher(hypermedia);
        String eventRef;
        if(matcher.matches()) {
            eventRef=matcher.group(matcher.groupCount());

        } else {
            LOGGER.error(String.format("The hypermedia supplied %s does not match %s", hypermedia, this.pattern.pattern().toString()));
            throw new IOException(String.format("The hypermedia supplied %s does not match %s", hypermedia, this.pattern.pattern().toString()));
        }
        return eventRef;
    }
/*
    private Pattern getHypermediaPattern() {
        if(this.pattern == null) {
            pattern=Pattern.compile(
                  linkTo(ReflectionUtils.findMethod(EventSpi.class, "getResource", String.class), "").withSelfRel().getHref()+
                        "/([^\\s^/]+)"
            );
        }
        return this.pattern;
    }*/
}
