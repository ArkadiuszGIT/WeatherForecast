package com.weatherforecast;

import com.weatherforecast.view.ViewManager;
import javafx.application.Application;

import javafx.stage.Stage;



/**
 * JavaFX App
 */
public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        ViewManager viewManager = new ViewManager(new WeatherManager());
        viewManager.showMainWindow();
    }

}