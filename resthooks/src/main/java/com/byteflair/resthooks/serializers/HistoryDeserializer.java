package com.byteflair.resthooks.serializers;

import com.byteflair.resthooks.LogSpi;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by dcerecedo on 1/24/14.
 */
public class HistoryDeserializer extends JsonDeserializer<List<Object>> {

    private static Logger LOG=LoggerFactory.getLogger(HistoryDeserializer.class);
    private Pattern pattern=null;

    @Override
    public List<Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        List<String> hypermedia=jsonParser.readValueAs(new TypeReference<List<String>>() {});
        List<Object> history=new ArrayList<>();
        for(String hypermedium : hypermedia) {
            Matcher matcher=getHypermediaPattern().matcher(hypermedium);
            if(matcher.matches()) {
                String id=matcher.group(matcher.groupCount());
                history.add(id);
            } else {
                LOG.error(String.format("The hypermedia supplied %s does not match %s", hypermedium, this.pattern.pattern().toString()));
                throw new JsonParseException(String.format("The hypermedia supplied %s does not match %s", hypermedium, this.pattern.pattern().toString()), jsonParser.getCurrentLocation());
            }
        }
        return new ArrayList<>();
    }

    private Pattern getHypermediaPattern() {
        if(this.pattern == null) {
            pattern=Pattern.compile(
                  linkTo(ReflectionUtils.findMethod(LogSpi.class, "getResource", String.class), "").withSelfRel().getHref()+
                        "/([^\\s^/]+)"
            );
        }
        return this.pattern;
    }
}
