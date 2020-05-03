package com.weatherforecast.model;

public class City {
    private String name;
    private String country;

    public City(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getCityName() {
        return name;
    }

    public String getCountryCode() {
        return country;
    }
}
