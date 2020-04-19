package com.weatherforecast.controller;

import com.weatherforecast.WeatherManager;
import com.weatherforecast.view.ViewManager;

/**
 * Created by Arek on 19.04.2020.
 */
public abstract class BaseController {

    protected WeatherManager weatherManager;
    protected ViewManager viewManager;
    private String fxmlName;

    public BaseController(WeatherManager weatherManager, ViewManager viewManager, String fxmlName) {
        this.weatherManager = weatherManager;
        this.viewManager = viewManager;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName() {
        return fxmlName;
    }
}
