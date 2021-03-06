package com.weatherforecast.controller;

import com.weatherforecast.view.ViewManager;

/**
 * Created by Arek on 19.04.2020.
 */
public abstract class BaseController {

    protected final ViewManager viewManager;
    private final String fxmlName;

    public BaseController(ViewManager viewManager, String fxmlName) {
        this.viewManager = viewManager;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName() {
        return fxmlName;
    }
}
