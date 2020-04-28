package com.weatherforecast.controller;

import com.weatherforecast.WeatherManager;
import com.weatherforecast.model.CityListReader;
import com.weatherforecast.model.WeatherForecast;
import com.weatherforecast.view.ViewManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import net.aksingh.owmjapis.api.APIException;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Arek on 16.04.2020.
 */
public class MainWindowController extends BaseController implements Initializable {

    private CityListReader cityListReader;
    private Integer currentTimeIndex;
    private String dateWithoutTime;
    private String mainWeather;

    @FXML
    private VBox currentLocationBackground;

    @FXML
    private TextField currentLocationField;

    @FXML
    private Label currentErrorLabel;

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
    private Label currentLocationWindSpeed;



    @FXML
    private VBox targetLocationBackground;

    @FXML
    private TextField targetLocationField;

    @FXML
    private Label targetLocationName;

    @FXML
    private Label targetErrorLabel;

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

    @FXML
    private Label targetLocationWindSpeed;

    public MainWindowController(WeatherManager weatherManager, ViewManager viewManager, String fxmlName){
        super(weatherManager, viewManager, fxmlName);
        this.cityListReader = new CityListReader();
        this.currentTimeIndex = 0;
        this.dateWithoutTime = "2020-01-01";
        this.mainWeather = "Clear";
    }

    @FXML
    void currentLocationButtonAction() throws APIException {

        if(currentFieldIsValid()){
            showCurrentWeather(currentLocationField, currentLocationName, currentLocationDate, currentLocationImage,
                    currentLocationTemp, currentLocationWeather, currentLocationMoist, currentLocationWindSpeed, currentLocationBackground);

            /*
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
            */
        }
    }

    @FXML
    void targetLocationButtonAction() throws APIException {

        if(targetFieldIsValid()){
            showCurrentWeather(targetLocationField, targetLocationName, targetLocationDate, targetLocationImage,
                    targetLocationTemp, targetLocationWeather, targetLocationMoist, targetLocationWindSpeed,
                    targetLocationBackground);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setStartView();
        TextFields.bindAutoCompletion(currentLocationField, cityListReader.getCityNameWithCountryCodeMap().values());
        TextFields.bindAutoCompletion(targetLocationField, cityListReader.getCityNameWithCountryCodeMap().values());
    }
    
    private void setStartView(){
        try {
            currentLocationField.setText("Warszawa, PL");
            targetLocationField.setText("New York, US");
            showCurrentWeather(currentLocationField, currentLocationName, currentLocationDate, currentLocationImage,
                    currentLocationTemp, currentLocationWeather, currentLocationMoist, currentLocationWindSpeed, currentLocationBackground);
            showCurrentWeather(targetLocationField, targetLocationName, targetLocationDate, targetLocationImage,
                    targetLocationTemp, targetLocationWeather, targetLocationMoist, targetLocationWindSpeed, targetLocationBackground);
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    private void showCurrentWeather(TextField locationField, Label locationName, Label locationDate,
                                    ImageView locationImage, Label locationTemp, Label locationWeather,
                                    Label locationMoist, Label locationWindSpeed, VBox locationBackground) throws APIException {

        WeatherForecast weatherForecast = new WeatherForecast(locationField.getText());

        locationName.setText(weatherForecast.getCityName()+ ", " + weatherForecast .getCountryCode());

        dateWithoutTime = weatherForecast.getDateTimeText(currentTimeIndex).substring(0,10);
        locationDate.setText(dateWithoutTime );

        Image image = new Image(weatherForecast .getIconLink(currentTimeIndex));
        locationImage.setImage(image);

        locationTemp.setText(weatherForecast .getTemp(currentTimeIndex));
        locationWeather.setText(weatherForecast .getDescription(currentTimeIndex));
        locationMoist.setText(weatherForecast .getHumidity(currentTimeIndex));
        locationWindSpeed.setText(weatherForecast .getWindSpeed(currentTimeIndex));

        mainWeather = weatherForecast.getMainWeather(currentTimeIndex);
        setBackground(mainWeather, locationBackground);

    }

    private void setBackground(String mainWeather, VBox locationBackground){
        switch (mainWeather) {
            case "Thunderstorm":
            case "Rain":
            case "Drizzle":
                locationBackground.setStyle("-fx-background-color: #8EB0B9");
                break;
            case "Snow":
                locationBackground.setStyle("-fx-background-color: #CEECF5");
                break;
            case "Clear":
                locationBackground.setStyle("-fx-background-color: #00CCFF");
                break;
            case "Clouds":
                locationBackground.setStyle("-fx-background-color: #0099FF");
                break;
            default:
                locationBackground.setStyle("-fx-background-color: #BDBDBD");
                break;
        }
    }

    private boolean currentFieldIsValid(){
        if(currentLocationField.getText().isEmpty()){
            currentErrorLabel.setText("Please enter the current city");
            return false;
        }
        currentErrorLabel.setText("");
        return true;
    }

    private boolean targetFieldIsValid(){
        if(targetLocationField.getText().isEmpty()){
            targetErrorLabel.setText("Please enter the target city");
            return false;
        }
        currentErrorLabel.setText("");
        return true;
    }

}
