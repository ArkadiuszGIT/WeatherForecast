package com.weatherforecast.model;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;

/**
 * Created by Arek on 24.04.2020.
 */
public class WeatherForecastFetcher {

    private final OWM owm;

    public WeatherForecastFetcher(String apiKey) {
         owm = new OWM(apiKey);
         owm.setUnit(OWM.Unit.METRIC);
    }

    public Weather getWeatherForecast(String cityNameWithCountryCode) throws APIException {
        return new Weather(owm.hourlyWeatherForecastByCityName(cityNameWithCountryCode));
    }
}
