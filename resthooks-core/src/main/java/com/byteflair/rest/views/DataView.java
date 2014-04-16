package com.byteflair.rest.views;

public interface DataView {

    Class<? extends View> getView();

    Object getData();
}
