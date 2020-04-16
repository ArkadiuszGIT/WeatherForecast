package com.weatherforecast;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("view/MainWindow.fxml"));

        Scene scene = new Scene(parent);
        stage.setScene(scene);

        stage.show();

    }

}