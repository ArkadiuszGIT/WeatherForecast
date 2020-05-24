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

    @Test
    void dateWithoutTimeLengthShouldBeEqualTo10() {

        //given
        //when
        String dateWithoutTime = weather.getDateWithoutTime(0);
        int length = dateWithoutTime.length();

        //then
        assertThat(length, equalTo(10));
    }

    @Test
    void timeWithoutDateShouldNotBeNull() {
        //when
        //then
        assertThat(weather.getTimeWithoutDate(0), is(notNullValue()));
    }

    @Test
    void timeWithoutDateLengthShouldBeEqualTo8() {

        //given
        //when
        String timeWithoutDate = weather.getTimeWithoutDate(0);
        int length = timeWithoutDate .length();

        //then
        assertThat(length, equalTo(8));
    }

    @Test
    void temperatureShouldNotBeNull() {
        //when
        //then
        assertThat(weather.getTemp(0), is(notNullValue()));
    }

    @Test
    void humidityShouldNotBeNull() {
        //when
        //then
        assertThat(weather.getHumidity(0), is(notNullValue()));
    }

    @Test
    void descriptionShouldNotBeNull() {
        //when
        //then
        assertThat(weather.getDescription(0), is(notNullValue()));
    }

    @Test
    void mainWeatherShouldNotBeNull() {
        //when
        //then
        assertThat(weather.getMainWeather(0), is(notNullValue()));
    }

    @Test
    void iconLinkShouldNotBeNull() {
        //when
        //then
        assertThat(weather.getIconLink(0), is(notNullValue()));
    }


    @Test
    void equalsMethodShouldReturnTrueWhenTwoWeathersAreTheSame() throws APIException {
        //given
        Weather weather2 = weatherForecastFetcher.getWeatherForecast("London, GB");

        //when
        //then
        assertThat(weather.equals(weather2), is(true));
    }

    @Test
    void equalsMethodShouldReturnFalseWhenTwoWeathersAreNotTheSame() throws APIException {
        //given
        Weather weather2 = weatherForecastFetcher.getWeatherForecast("Warsaw, PL");

        //when
        //then
        assertThat(weather.equals(weather2), is(false));
    }

    @Test
    void equalsMethodShouldReturnFalseWhenTheClassTypesAreDifferent() {
        //when
        //then
        assertThat(weather.equals(new Object()), is(false));
    }

    @Test
    void comparingHashCodeMethodShouldReturnTrueWhenTwoWeathersAreTheSame() throws APIException {
        //given
        Weather weather2 = weatherForecastFetcher.getWeatherForecast("London, GB");

        //when
        boolean isHashcodeEquals = weather.hashCode() == weather2.hashCode();

        //then
        assertThat(isHashcodeEquals, is(true));
    }

    @Test
    void comparingHashCodeMethodShouldReturnFalseWhenTwoWeathersAreNotTheSame() throws APIException {
        //given
        Weather weather2 = weatherForecastFetcher.getWeatherForecast("Warsaw, PL");

        //when
        boolean isHashcodeEquals = weather.hashCode() == weather2.hashCode();

        //then
        assertThat(isHashcodeEquals, is(false));
    }
}
