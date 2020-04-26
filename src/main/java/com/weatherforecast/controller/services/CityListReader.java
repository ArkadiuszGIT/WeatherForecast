package com.weatherforecast.controller.services;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.weatherforecast.model.City;


/**
 * Created by Arek on 16.04.2020.
 */

public class CityListReader {
    private List<City> cityList;

    public CityListReader(){
        loadJsonFile();
    }

    private void loadJsonFile(){

        try {
            /*
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("D:/java/IdeaProjects" +
                        "/WeatherForecast/src/main/resources/com/weatherforecast/listamiast.json");

                JsonArray jsonArray = new JsonParser().parse(new InputStreamReader(inputStream)).getAsJsonArray();

                Gson gson = new Gson();
                Type listType = new TypeToken<List<City>>() {
                }.getType();
                List<City> cityList = gson.fromJson(jsonArray, listType);

                this.cityList = cityList;
            */

            FileReader inputStream = new FileReader("D:/java/IdeaProjects/WeatherForecast/src/main/resources" +
                    "/com/weatherforecast/listamiast.json");
           // InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/com/weatherforecast" +
             //       "/listamiast.json");

            JsonArray jsonArray = new JsonParser().parse(inputStream).getAsJsonArray();

            Gson gson = new Gson();
            Type listType = new TypeToken<List<City>>() {
            }.getType();
            List<City> cityList = gson.fromJson(jsonArray, listType);

            this.cityList = cityList;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<City> getCityList() {
        return cityList;
    }
}
