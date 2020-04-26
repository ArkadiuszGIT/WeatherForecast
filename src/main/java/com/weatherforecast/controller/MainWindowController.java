package com.weatherforecast.controller;

import com.weatherforecast.WeatherManager;
import com.weatherforecast.controller.services.CityListReader;
import com.weatherforecast.model.City;
import com.weatherforecast.view.ViewManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Arek on 16.04.2020.
 */
public class MainWindowController extends BaseController implements Initializable {

    @FXML
    private VBox currentLocationBackground;

    @FXML
    private TextField currentLocationField;

    @FXML
    private Label currentLocationName;

    @FXML
    private Label currentLocationDate;

    @FXML
    private ImageView currentLocationImage;

    @FXML
    private Label currentLocationTemp;

    @FXML
    private Label currentLocationWeather;

    @FXML
    private Label currentLocationMoist;


    @FXML
    private VBox targetLocationBackground;

    @FXML
    private TextField targetLocationField;

    @FXML
    private Label targetLocationName;

    @FXML
    private Label targetLocationDate;

    @FXML
    private ImageView targetLocationImage;

    @FXML
    private Label targetLocationWeather;

    @FXML
    private Label targetLocationTemp;

    @FXML
    private Label targetLocationMoist;

    private CityListReader cityListReader;

    public MainWindowController(WeatherManager weatherManager, ViewManager viewManager, String fxmlName){
        super(weatherManager, viewManager, fxmlName);
        this.cityListReader = new CityListReader();
    }

    @FXML
    void currentLocationButtonAction() {
        System.out.println("current click!");
    }

    @FXML
    void targetLocationButtonAction() {
        System.out.println("target click!");
    }

    private List<City> allCitiesList;
    private HashMap<String, String> cityNameWithCountryCode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allCitiesList = cityListReader.getCityList();
        cityNameWithCountryCode = new HashMap<>();
        int iterator = 0;
        for (City i : allCitiesList) {
            String city = allCitiesList.get(iterator).getCityName();
            String countryCode = allCitiesList.get(iterator).getCountryCode();
            cityNameWithCountryCode.put(city, city + ", " + countryCode);
            iterator++;
        }
        TextFields.bindAutoCompletion(currentLocationField, cityNameWithCountryCode.values());
    }
}
