package com.weatherforecast.model;

import net.aksingh.owmjapis.model.HourlyWeatherForecast;

import java.util.Objects;

/**
 * Created by Arek on 15.05.2020.
 */
public class Weather {
     private final HourlyWeatherForecast hourlyWeatherForecast;

     public Weather(HourlyWeatherForecast hourlyWeatherForecast) {
         this.hourlyWeatherForecast = hourlyWeatherForecast;
     }

     public Integer getForecastSize() {
         if (hourlyWeatherForecast.hasDataCount()) {
             return hourlyWeatherForecast.getDataCount();
         }
         return null;
     }

     public String getCityName() {
         if (hourlyWeatherForecast.getCityData().hasName()) {
             return hourlyWeatherForecast.getCityData().getName();
         }
         return null;
     }

     public String getCountryCode() {
         if (hourlyWeatherForecast.getCityData().hasCountryCode()) {
             return hourlyWeatherForecast.getCityData().getCountryCode();
         }
         return null;
     }

     public String getDayOfTheWeek(int forecastIndex) {
         if (hourlyWeatherForecast.getDataList().get(forecastIndex).hasDateTime()) {
             return String.valueOf(hourlyWeatherForecast.getDataList().get(forecastIndex).getDateTime()).substring(0, 3);
         }
         return null;
     }

     public String getDateTimeText(int forecastIndex) {
         if (hourlyWeatherForecast.getDataList().get(forecastIndex).hasDateTimeText()) {
             return hourlyWeatherForecast.getDataList().get(forecastIndex).getDateTimeText();
         }
         return null;
     }

     public String getDateWithoutTime(int forecastIndex) {
         return getDateTimeText(forecastIndex).substring(0, 10);
     }

     public String getTimeWithoutDate(int forecastIndex) {
         return getDateTimeText(forecastIndex).substring(11);
     }


     public String getTemp(int forecastIndex) {
         if (hourlyWeatherForecast.getDataList().get(forecastIndex).getMainData().hasTemp()) {
             return hourlyWeatherForecast.getDataList().get(forecastIndex).getMainData().getTemp() + " °C";
         }
         return null;
     }

     public String getHumidity(int forecastIndex) {
         if (hourlyWeatherForecast.getDataList().get(forecastIndex).getMainData().hasHumidity()) {
             return hourlyWeatherForecast.getDataList().get(forecastIndex).getMainData().getHumidity() + " %";
         }
         return null;
     }

     public String getWindSpeed(int forecastIndex) {
         if (hourlyWeatherForecast.getDataList().get(forecastIndex).getWindData().hasSpeed()) {
             return Math.round(hourlyWeatherForecast.getDataList().get(forecastIndex).getWindData().getSpeed() * 3.6) +
                     " km/h";
         }
         return null;
     }

     public String getDescription(int forecastIndex) {
         if (hourlyWeatherForecast.getDataList().get(forecastIndex).getWeatherList().get(0).hasDescription()) {
             return hourlyWeatherForecast.getDataList().get(forecastIndex).getWeatherList().get(0).getDescription();
         }
         return null;
     }

     public String getMainWeather(int forecastIndex) {
         if (hourlyWeatherForecast.getDataList().get(forecastIndex).getWeatherList().get(0).hasMainInfo()) {
             return hourlyWeatherForecast.getDataList().get(forecastIndex).getWeatherList().get(0).getMainInfo();
         }
         return null;
     }

     public String getIconLink(int forecastIndex) {
         if (hourlyWeatherForecast.getDataList().get(forecastIndex).getWeatherList().get(0).hasIconLink()) {
             return hourlyWeatherForecast.getDataList().get(forecastIndex).getWeatherList().get(0).getIconLink();
         }
         return null;
     }

     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         Weather weather = (Weather) o;
         return Objects.equals(hourlyWeatherForecast, weather.hourlyWeatherForecast);
     }

     @Override
     public int hashCode() {
         return Objects.hash(hourlyWeatherForecast);
     }
}
