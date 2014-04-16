package com.byteflair.rest.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

class ResponseViewResolver {

    private static final Logger LOGGER=LoggerFactory.getLogger(ResponseViewResolver.class);

    public static Class<? extends View> resolveView(ModelAndViewContainer model, MethodParameter returnType) {
        ResponseView annotation=returnType.getMethodAnnotation(ResponseView.class);
        Class<? extends View> view=null;
        if(annotation != null) {
            if(annotation.value().equals(View.class)) {
                String viewName=((ModelAndView) model.getModel().get("modelAndView")).getViewName();
                int index=0;
                if(viewName != null) {
                    if(annotation.names().length != annotation.views().length) {
                        LOGGER.warn("Annotation ResponseView on method {} has different number of names and views", returnType.getMethod().getName());
                    }
                    index=Arrays.binarySearch(annotation.names(), viewName);
                } else {
                    LOGGER.debug("No view name on model, using default view {}", annotation.views()[0]);
                }
                if(0<=index && index<annotation.views().length) {
                    view=annotation.views()[index];
                    viewName=annotation.names()[index];
                } else {
                    LOGGER.error("The view name: {}; has no corresponding view.", viewName);
                }
                LOGGER.debug("Using view: {}; with name: {};", annotation.views()[index], viewName);

            } else {
                view=annotation.value();
                LOGGER.debug("Using default view {}. Ignoring model.", annotation.value());
            }

        }
        return view;
    }
}
