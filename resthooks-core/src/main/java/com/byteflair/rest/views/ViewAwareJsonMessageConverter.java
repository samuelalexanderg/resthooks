package com.byteflair.rest.views;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;

public class ViewAwareJsonMessageConverter extends MappingJackson2HttpMessageConverter {

    public ViewAwareJsonMessageConverter() {
        super();
        ObjectMapper defaultMapper=new ObjectMapper();
        setObjectMapper(defaultMapper);
    }

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException {
        if(object instanceof DataView) {
            writeView((DataView) object, outputMessage);
        } else {
            super.writeInternal(object, outputMessage);
        }
    }

    protected void writeView(DataView view, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        ObjectMapper mapper=new ObjectMapper();
        ObjectWriter writer=mapper.writerWithView(view.getView());
        writer.writeValue(outputMessage.getBody(), view.getData());
    }

}