package com.weatherforecast.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;;
import java.lang.reflect.Type;
import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Arek on 16.04.2020.
 */

public class CitiesReader {
    private Map<String, String> cityNameWithCountryCodeMap;

    public CitiesReader(){
        loadJsonFile();
    }

    private void loadJsonFile(){

        try (var stream = new InputStreamReader(this.getClass().getResourceAsStream("/com/weatherforecast/CityList.json"))) {

            JsonArray jsonArray = new JsonParser().parse(stream).getAsJsonArray();

            Gson gson = new Gson();
            Type listType = new TypeToken<List<City>>() {
            }.getType();
            List<City> cities = gson.fromJson(jsonArray, listType);
            cityNameWithCountryCodeMap = cities
                    .stream()
                    .collect(Collectors.toMap(City::getCityName, city -> (city.getCityName() + "," + city.getCountryCode()), (c1, c2) -> c1));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Map<String, String> getCityNameWithCountryCodeMap() {
        return cityNameWithCountryCodeMap;
    }

}
