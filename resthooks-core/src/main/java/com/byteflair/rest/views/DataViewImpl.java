package com.byteflair.rest.views;

public class DataViewImpl implements DataView {

    private final Object data;
    private final Class<? extends View> view;

    public DataViewImpl(Object data, Class<? extends View> view) {
        this.data=data;
        this.view=view;
    }

    @Override
    public Class<? extends View> getView() {
        return this.view;
    }

    @Override
    public Object getData() {
        return this.data;
    }

}
