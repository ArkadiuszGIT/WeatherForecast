package com.weatherforecast.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Arek on 17.05.2020.
 */
class CitiesReaderTest {

    @Test
    void cityNameWithCountryCodeMapShouldNotBeNullAfterLoadingJsonFile() {

        //given
        CitiesReader citiesReader = new CitiesReader();

        //when
        Map<String, String> cityNameWithCountryCodeMap = citiesReader.getCityNameWithCountryCodeMap();

        //then
        assertThat(cityNameWithCountryCodeMap.isEmpty(), is(false));
    }

    @Test
    void cityNameWithCountryCodeMapShouldContainsProperStrings() {

        //given
        CitiesReader citiesReader = new CitiesReader();

        //when
        Map<String, String> cityNameWithCountryCodeMap = citiesReader.getCityNameWithCountryCodeMap();

        //then
        assertThat(cityNameWithCountryCodeMap, hasKey("London"));
        assertThat(cityNameWithCountryCodeMap, hasValue("London, GB"));
        assertThat(cityNameWithCountryCodeMap, hasKey("Hurzuf"));
        assertThat(cityNameWithCountryCodeMap, hasValue("Hurzuf, UA"));
    }
}