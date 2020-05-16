package com.weatherforecast.controller;

import com.weatherforecast.model.CitiesReader;
import com.weatherforecast.model.Weather;
import com.weatherforecast.model.WeatherForecastFetcher;
import com.weatherforecast.view.ViewManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import net.aksingh.owmjapis.api.APIException;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Arek on 16.04.2020.
 */
public class MainWindowController extends BaseController implements Initializable {

    private CitiesReader citiesReader;

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
    private TilePane currentLocationNextDays;



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

    @FXML
    private TilePane targetLocationNextDays;

    private final WeatherForecastFetcher weatherForecastFetcher;

     public MainWindowController(ViewManager viewManager, String fxmlName) {
         super(viewManager, fxmlName);
         this.citiesReader = new CitiesReader();
         weatherForecastFetcher = new WeatherForecastFetcher("YOUR_API_KEY");
     }

    @FXML
    void currentLocationButtonAction() throws APIException {

        if (currentFieldIsValid()) {
            showCurrentWeather(currentLocationField, currentLocationName, currentLocationDate, currentLocationImage,
                    currentLocationTemp, currentLocationWeather, currentLocationMoist, currentLocationWindSpeed, currentLocationBackground);
            showNextDays(currentLocationField, currentLocationNextDays);
        }
    }

    @FXML
    void targetLocationButtonAction() throws APIException {

        if (targetFieldIsValid()) {
            showCurrentWeather(targetLocationField, targetLocationName, targetLocationDate, targetLocationImage,
                    targetLocationTemp, targetLocationWeather, targetLocationMoist, targetLocationWindSpeed,
                    targetLocationBackground);
            showNextDays(targetLocationField, targetLocationNextDays);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setStartView();
        TextFields.bindAutoCompletion(currentLocationField, citiesReader.getCityNameWithCountryCodeMap().values());
        TextFields.bindAutoCompletion(targetLocationField, citiesReader.getCityNameWithCountryCodeMap().values());
    }

    private void setStartView() {
        try {
            currentLocationField.setText("Warszawa, PL");
            targetLocationField.setText("New York, US");
            showCurrentWeather(currentLocationField, currentLocationName, currentLocationDate, currentLocationImage,
                    currentLocationTemp, currentLocationWeather, currentLocationMoist, currentLocationWindSpeed, currentLocationBackground);
            showNextDays(currentLocationField, currentLocationNextDays);
            showCurrentWeather(targetLocationField, targetLocationName, targetLocationDate, targetLocationImage,
                    targetLocationTemp, targetLocationWeather, targetLocationMoist, targetLocationWindSpeed, targetLocationBackground);
            showNextDays(targetLocationField, targetLocationNextDays);
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    private void showCurrentWeather(TextField locationField, Label locationName, Label locationDate,
                                    ImageView locationImage, Label locationTemp, Label locationWeather,
                                    Label locationMoist, Label locationWindSpeed, VBox locationBackground) throws APIException {

        var weather = weatherForecastFetcher.getWeatherForecast(locationField.getText());
        int currentTimeIndex = 0;

        String cityNameWithCountryCode = weather.getCityName() + ", " + weather .getCountryCode();
        String dateWithDay =
                weather.getDayOfTheWeek(currentTimeIndex) + ", " + weather.getDateWithoutTime(currentTimeIndex);
        Image image = new Image(weather .getIconLink(currentTimeIndex));
        String temp = "Temperature: " + weather .getTemp(currentTimeIndex);
        String description = "Weather: " + weather .getDescription(currentTimeIndex);
        String humidity = "Humidity: " + weather .getHumidity(currentTimeIndex);
        String windSpeed = "Wind Speed: " + weather .getWindSpeed(currentTimeIndex);
        String mainWeather = weather.getMainWeather(currentTimeIndex);


        locationName.setText(cityNameWithCountryCode);
        locationDate.setText(dateWithDay);
        locationImage.setImage(image);
        locationTemp.setText(temp);
        locationWeather.setText(description);
        locationMoist.setText(humidity);
        locationWindSpeed.setText(windSpeed);
        setBackground(mainWeather, locationBackground);

    }

    private void showNextDays(TextField locationField, TilePane locationNextDays) throws APIException {

        locationNextDays.getChildren().clear();
        var weather = weatherForecastFetcher.getWeatherForecast(locationField.getText());
        int currentTimeIndex = 0;
        String today = weather.getDateWithoutTime(currentTimeIndex);
        String noon = "12:00:00";
        int days = 1;

        for (int timeIndex = 0; timeIndex < weather.getForecastSize(); timeIndex++ ){

            String date = weather.getDateWithoutTime(timeIndex);
            String time = weather.getTimeWithoutDate(timeIndex);

            if (!date.equals(today) && time.equals(noon) && days <= 4) {
                setLayoutForNextDays(weather, timeIndex, locationNextDays);
                days++;
            }
        }
    }

    private void setLayoutForNextDays(Weather weather, int timeIndex, TilePane locationNextDays) {

        String dateWithDay =
                weather.getDayOfTheWeek(timeIndex) + ", " + weather .getDateWithoutTime(timeIndex);
        Image image = new Image(weather .getIconLink(timeIndex));
        String temp = weather .getTemp(timeIndex);
        String description = weather .getDescription(timeIndex);
        String humidity = weather .getHumidity(timeIndex);
        String windSpeed = weather .getWindSpeed(timeIndex);

        Label dailyDateWithDay = new Label();
        dailyDateWithDay.setPadding(new Insets(0, 0, 0, 40));
        dailyDateWithDay.setFont(Font.font("Calibri", FontWeight.BOLD, 15));
        dailyDateWithDay.setText(dateWithDay);

        HBox nextDay = new HBox();
        nextDay.setAlignment(Pos.CENTER);

        ImageView dailyImage = new ImageView();
        dailyImage.setImage(image);
        dailyImage.setFitHeight(100);
        dailyImage.setFitWidth(100);

        Separator separator = new Separator();
        separator.setMaxHeight(80);
        separator.setOrientation(Orientation.VERTICAL);

        VBox nextDayInfo = new VBox();
        nextDayInfo.setAlignment(Pos.TOP_LEFT);

        VBox nextDayWithName = new VBox();
        nextDayWithName.setAlignment(Pos.CENTER);

        Label dailyWeather = new Label();
        dailyWeather.setPadding(new Insets(7, 0, 5, 0));
        dailyWeather.setText(description);

        Label dailyTemperature = new Label();
        dailyTemperature.setPadding(new Insets(0, 0, 5, 0));
        dailyTemperature.setText(temp);

        Label dailyHumidity = new Label();
        dailyHumidity.setPadding(new Insets(0, 0, 5, 0));
        dailyHumidity.setText(humidity);

        Label dailyWindSpeed = new Label();
        dailyWindSpeed.setText(windSpeed);

        nextDayInfo.getChildren().addAll(dailyWeather, dailyTemperature, dailyHumidity, dailyWindSpeed);

        nextDay.getChildren().addAll(dailyImage, separator, nextDayInfo);

        nextDayWithName.getChildren().addAll(dailyDateWithDay, nextDay);

        locationNextDays.getChildren().add(nextDayWithName);
        locationNextDays.setAlignment(Pos.CENTER);
        locationNextDays.setPrefColumns(2);
        locationNextDays.setPrefRows(2);
        locationNextDays.setMaxWidth(Region.USE_PREF_SIZE);
    }

    private void setBackground(String mainWeather, VBox locationBackground) {
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

    private boolean currentFieldIsValid() {
        if(currentLocationField.getText().isEmpty()) {
            currentErrorLabel.setText("Please enter the current city");
            return false;
        }
        currentErrorLabel.setText("");
        return true;
    }

    private boolean targetFieldIsValid() {
        if(targetLocationField.getText().isEmpty()) {
            targetErrorLabel.setText("Please enter the target city");
            return false;
        }
        currentErrorLabel.setText("");
        return true;
    }

}
