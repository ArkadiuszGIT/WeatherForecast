package com.weatherforecast;

import com.weatherforecast.model.WeatherForecast;
import net.aksingh.owmjapis.api.APIException;

/**
 * Created by Arek on 19.04.2020.
 */
public class WeatherManager {

    public WeatherManager() throws APIException {
    }

    public void showForecast() throws APIException {
        String cityName = "Lublin, PL";
        WeatherForecast weatherForecast = new WeatherForecast(cityName);

        System.out.println(weatherForecast .getCityName() + " ," + weatherForecast .getCountryCode());
        String today = weatherForecast .getDateWithoutTime(0);
        String noon = "12:00:00";
        int days = 0;
        for (int i = 0; i < weatherForecast .getForecastSize(); i++ ){
            if(!weatherForecast.getDateWithoutTime(i).equals(today) && weatherForecast .getTimeWithoutDate(i).equals(noon) && days < 4){
                System.out.println("");
                System.out.println(i);
                System.out.println(weatherForecast .getTimeWithoutDate(i));
                System.out.println(weatherForecast .getDateWithoutTime(i));
                System.out.println(weatherForecast .getDateTimeText(i));
                System.out.println(weatherForecast .getTemp(i));
                System.out.println(weatherForecast .getHumidity(i));
                System.out.println(weatherForecast .getDescription(i));
                System.out.println(weatherForecast .getIconLink(i));
                System.out.println(weatherForecast .getWindSpeed(i));
                days++;
            }
        }
    }
}
