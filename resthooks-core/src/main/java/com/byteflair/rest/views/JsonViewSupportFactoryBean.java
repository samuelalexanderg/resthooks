package com.byteflair.rest.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

public class JsonViewSupportFactoryBean implements InitializingBean {

    private static final Logger LOGGER=LoggerFactory.getLogger(JsonViewSupportFactoryBean.class);

    @Autowired
    private RequestMappingHandlerAdapter adapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> handlers=adapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> customHandlers=new ArrayList<>();
        for(HandlerMethodReturnValueHandler handler : handlers) {
            if(handler instanceof HttpEntityMethodProcessor) {
                ViewInjectingReturnValueHandler decorator=new ViewInjectingReturnValueHandler(handler);
                customHandlers.add(decorator);
                LOGGER.debug("JsonView decorator support wired up");
                break;
            }
        }
        adapter.setCustomReturnValueHandlers(customHandlers);
    }

    private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        List<HandlerMethodReturnValueHandler> customHandlers=new ArrayList<>();
        for(HandlerMethodReturnValueHandler handler : handlers) {
            if(handler instanceof HttpEntityMethodProcessor) {
                ViewInjectingReturnValueHandler decorator=new ViewInjectingReturnValueHandler(handler);
                int index=handlers.indexOf(handler);
                handlers.set(index, decorator);

                LOGGER.debug("JsonView decorator support wired up");
                break;
            }
        }
    }

}
