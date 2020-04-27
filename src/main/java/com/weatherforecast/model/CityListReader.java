package com.weatherforecast.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.weatherforecast.model.City;


/**
 * Created by Arek on 16.04.2020.
 */

public class CityListReader {
    private List<City> cityList;
    private HashMap<String, String> cityNameWithCountryCodeMap = new HashMap<>();

    public CityListReader(){
        loadJsonFile();
        cityListConversionToHashMap();
    }

    private void loadJsonFile(){

        try {

            FileReader inputStream = new FileReader("D:/java/IdeaProjects/WeatherForecast/src/main/resources" +
                    "/com/weatherforecast/CityList.json");

            JsonArray jsonArray = new JsonParser().parse(inputStream).getAsJsonArray();

            Gson gson = new Gson();
             Type listType = new TypeToken<List<City>>() {
            }.getType();
            cityList = gson.fromJson(jsonArray, listType);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void cityListConversionToHashMap(){
        for (City value : cityList) {
            String city = value.getCityName();
            String countryCode = value.getCountryCode();
            cityNameWithCountryCodeMap.put(city, city + ", " + countryCode);
        }
    }

    public HashMap<String, String> getCityNameWithCountryCodeMap(){
        return cityNameWithCountryCodeMap;
    }

}
