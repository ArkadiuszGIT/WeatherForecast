package com.weatherforecast.model;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;

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
        HourlyWeatherForecast hourlyWeatherForecast = owm.hourlyWeatherForecastByCityName(cityNameWithCountryCode);
        if (hourlyWeatherForecast.hasRespCode() && hourlyWeatherForecast.getRespCode().equals("200")) {
            return new Weather(hourlyWeatherForecast);
        }
        return null;
    }
}
