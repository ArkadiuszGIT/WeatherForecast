package com.weatherforecast.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * Created by Arek on 16.04.2020.
 */
public class MainWindowController {

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

    @FXML
    void currentLocationButtonAction() {
        System.out.println("current click!");
    }

    @FXML
    void targetLocationButtonAction() {
        System.out.println("target click!");
    }
}
