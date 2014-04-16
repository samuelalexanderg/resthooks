package com.byteflair.rest.views;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseView {

    public Class<? extends View> value() default View.class;

    public String[] names() default {};

    public Class<? extends View>[] views() default {};
}
