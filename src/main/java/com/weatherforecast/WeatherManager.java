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
        String cityName = "London, UK";
        WeatherForecast weatherForecast = new WeatherForecast(cityName);

        System.out.println(weatherForecast .getCityName() + " ," + weatherForecast .getCountryCode());

        for (int i = 0; i < weatherForecast .getForecastSize(); i++ ){
            System.out.println("");
            System.out.println(i);
            System.out.println(weatherForecast .getDateTimeText(i));
            System.out.println(weatherForecast .getTemp(i));
            System.out.println(weatherForecast .getHumidity(i));
            System.out.println(weatherForecast .getDescription(i));
            System.out.println(weatherForecast .getIconLink(i));
            System.out.println(weatherForecast .getWindSpeed(i));
        }
    }

}
