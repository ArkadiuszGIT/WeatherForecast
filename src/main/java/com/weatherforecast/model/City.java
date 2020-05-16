package com.weatherforecast.model;

public class City {
    private String name;
    private String country;

    public String getCityName() {
        return name;
    }

    public void setCityName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return country;
    }

    public void setCountryCode(String country) {
        this.country = country;
    }
}
