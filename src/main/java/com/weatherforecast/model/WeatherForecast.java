package com.weatherforecast.model;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;

/**
 * Created by Arek on 24.04.2020.
 */
public class WeatherForecast {

    private OWM owm = new OWM("dc43b76f47ccb750e836361fb3143462");
    private HourlyWeatherForecast hourlyWeatherForecast;

    public WeatherForecast(String cityNameWithCountryCode) throws APIException {
        owm.setUnit(OWM.Unit.METRIC);

        hourlyWeatherForecast = getWeatherForecast(cityNameWithCountryCode);
    }

    private HourlyWeatherForecast getWeatherForecast(String cityNameWithCountryCode) throws APIException {
        HourlyWeatherForecast hourlyWeatherForecastCall = owm.hourlyWeatherForecastByCityName(cityNameWithCountryCode);

        return hourlyWeatherForecastCall;

    }
    /* nullpointerexception
    private boolean checkIfDataRetrievalSuccessful(){
        try {
            if(hourlyWeatherForecast.hasRespCode() && hourlyWeatherForecast.getRespCode().equals("200")) {
                return true;
            }
        } catch(NullPointerException e) {
            System.out.println("NullPointerException thrown!");
        }
        return false;
    }
    */
    public Integer getForecastSize(){
        if(hourlyWeatherForecast.hasDataCount()){
            return hourlyWeatherForecast.getDataCount();
        }
        return null;
    }

    public String getCityName(){
        if(hourlyWeatherForecast.getCityData().hasName()){
            return hourlyWeatherForecast.getCityData().getName();
        }
        return null;
    }

    public String getCountryCode(){
        if(hourlyWeatherForecast.getCityData().hasCountryCode()){
            return hourlyWeatherForecast.getCityData().getCountryCode();
        }
        return null;
    }
    public String getDayOfTheWeek(int forecastIndex){
        if(hourlyWeatherForecast.getDataList().get(forecastIndex).hasDateTime()){
            return String.valueOf(hourlyWeatherForecast.getDataList().get(forecastIndex).getDateTime()).substring(0,3);
        }
        return null;
    }
    public String getDateTimeText(int forecastIndex){
        if(hourlyWeatherForecast.getDataList().get(forecastIndex).hasDateTimeText()){
            return hourlyWeatherForecast.getDataList().get(forecastIndex).getDateTimeText();
        }
        return null;
    }

    public String getDateWithoutTime(int forecastIndex){
        return getDateTimeText(forecastIndex).substring(0,10);
    }


    public String getTemp(int forecastIndex){
        if(hourlyWeatherForecast.getDataList().get(forecastIndex).getMainData().hasTemp()){
            return hourlyWeatherForecast.getDataList().get(forecastIndex).getMainData().getTemp() + " °C";
        }
        return null;
    }

    public String getHumidity(int forecastIndex){
        if(hourlyWeatherForecast.getDataList().get(forecastIndex).getMainData().hasHumidity()){
            return hourlyWeatherForecast.getDataList().get(forecastIndex).getMainData().getHumidity() + " %";
        }
        return null;
    }

    public String getWindSpeed(int forecastIndex){
        if(hourlyWeatherForecast.getDataList().get(forecastIndex).getWindData().hasSpeed()){
            return Math.round(hourlyWeatherForecast.getDataList().get(forecastIndex).getWindData().getSpeed() * 3.6) +
                    " km/h";
        }
        return null;
    }

    public String getDescription(int forecastIndex){
        if(hourlyWeatherForecast.getDataList().get(forecastIndex).getWeatherList().get(0).hasDescription()){
            return hourlyWeatherForecast.getDataList().get(forecastIndex).getWeatherList().get(0).getDescription();
        }
        return null;
    }

    public String getMainWeather(int forecastIndex){
        if(hourlyWeatherForecast.getDataList().get(forecastIndex).getWeatherList().get(0).hasMainInfo()){
            return hourlyWeatherForecast.getDataList().get(forecastIndex).getWeatherList().get(0).getMainInfo();
        }
        return null;
    }

    public String getIconLink(int forecastIndex){
        if(hourlyWeatherForecast.getDataList().get(forecastIndex).getWeatherList().get(0).hasIconLink()){
            return hourlyWeatherForecast.getDataList().get(forecastIndex).getWeatherList().get(0).getIconLink();
        }
        return null;
    }
}
