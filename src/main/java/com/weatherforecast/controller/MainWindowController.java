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
    }

    @FXML
    void currentLocationButtonAction() throws APIException {

        if(currentFieldIsValid()){
            WeatherForecast weatherForecast = new WeatherForecast(currentLocationField.getText());
            currentLocationName.setText(weatherForecast.getCityName()+ ", " + weatherForecast .getCountryCode());
            dateWithoutTime = weatherForecast.getDateTimeText(currentTimeIndex).substring(0,10);
            currentLocationDate.setText(dateWithoutTime );
            Image image = new Image(weatherForecast .getIconLink(currentTimeIndex));
            currentLocationImage.setImage(image);
            currentLocationTemp.setText(weatherForecast .getTemp(currentTimeIndex));
            currentLocationWeather.setText(weatherForecast .getDescription(currentTimeIndex));
            currentLocationMoist.setText(weatherForecast .getHumidity(currentTimeIndex));
            currentLocationWindSpeed.setText(weatherForecast .getWindSpeed(currentTimeIndex));

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
            WeatherForecast weatherForecast = new WeatherForecast(targetLocationField.getText());
            System.out.println(targetLocationField.getText());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentLocationField.setText("Warszawa, PL");
        targetLocationField.setText("New York, US");
        TextFields.bindAutoCompletion(currentLocationField, cityListReader.getCityNameWithCountryCodeMap().values());
        TextFields.bindAutoCompletion(targetLocationField, cityListReader.getCityNameWithCountryCodeMap().values());
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
