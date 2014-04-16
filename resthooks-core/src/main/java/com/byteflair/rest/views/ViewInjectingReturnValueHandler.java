package com.byteflair.rest.views;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.MethodParameter;
import org.springframework.expression.BeanResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

class ViewInjectingReturnValueHandler implements HandlerMethodReturnValueHandler, ApplicationContextAware {

    private final HandlerMethodReturnValueHandler delegate;

    public ViewInjectingReturnValueHandler(HandlerMethodReturnValueHandler delegate) {
        this.delegate=delegate;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return delegate.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        Class<? extends View> viewClass=ResponseViewResolver.resolveView(mavContainer, returnType);
        if(viewClass != null) {
            returnValue=wrapResult(returnValue, viewClass);
        }

        delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }

    /**
     * Returns the view class declared on the method, if it exists.
     * Otherwise, returns null.
     *
     * @param returnType
     * @return
     */

    private Object wrapResult(Object result, Class<? extends View> viewClass) {
        @SuppressWarnings("unchecked")
        ResponseEntityView response=new ResponseEntityView((ResponseEntity<Object>) result, viewClass);
        return response;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanResolver resolver=new BeanFactoryResolver(applicationContext);
    }
}
