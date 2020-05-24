package com.weatherforecast.model;

import net.aksingh.owmjapis.api.APIException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


/**
 * Created by Arek on 19.05.2020.
 */
class WeatherForecastFetcherTest {

    @Test
    void getWeatherForecastShouldNotBeNull() throws APIException {

        //given
        WeatherForecastFetcher weatherForecastFetcher = new WeatherForecastFetcher("dc43b76f47ccb750e836361fb3143462");

        //when
        Weather weather = weatherForecastFetcher.getWeatherForecast("London, GB");

        //then
        assertThat(weather, is(notNullValue()));
    }
}