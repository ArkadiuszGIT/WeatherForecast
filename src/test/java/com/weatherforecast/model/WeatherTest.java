package com.weatherforecast.model;

import net.aksingh.owmjapis.api.APIException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Arek on 19.05.2020.
 */
class WeatherTest{

    private static WeatherForecastFetcher weatherForecastFetcher;
    private static Weather weather;

    @BeforeAll
    static void setup() throws APIException {
        weatherForecastFetcher = new WeatherForecastFetcher("dc43b76f47ccb750e836361fb3143462");
        weather = weatherForecastFetcher.getWeatherForecast("London, GB");
    }

    @Test
    void forecastSizeShouldBeEqualTo40() {
        //when
        //then
        assertThat(weather.getForecastSize(), equalTo(40));
    }

    @Test
    void cityNameShouldNotBeNull() {
        //when
        //then
        assertThat(weather.getCityName(), is(notNullValue()));
    }

    @Test
    void countryCodeShouldNotBeNull() {
        //when
        //then
        assertThat(weather.getCountryCode(), is(notNullValue()));
    }

    @Test
    void dayOfTheWeekShouldNotBeNull() {
        //when
        //then
        assertThat(weather.getDayOfTheWeek(0), is(notNullValue()));
    }

    @Test
    void dayOfTheWeekLengthShouldBeEqualTo3() {

        //given
        //when
        String dayOfTheWeek = weather.getDayOfTheWeek(0);
        int length = dayOfTheWeek.length();

        //then
        assertThat(length, equalTo(3));
    }

    @Test
    void dateTimeTextShouldNotBeNull() {
        //when
        //then
        assertThat(weather.getDateTimeText(0), is(notNullValue()));
    }

    @Test
    void dateWithoutTimeShouldNotBeNull() {
        //when
        //then
        assertThat(weather.getDateWithoutTime(0), is(notNullValue()));
    }


}
