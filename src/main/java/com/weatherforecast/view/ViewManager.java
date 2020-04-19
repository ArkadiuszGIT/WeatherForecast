package com.weatherforecast.view;

import com.weatherforecast.WeatherManager;
import com.weatherforecast.controller.BaseController;
import com.weatherforecast.controller.MainWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Arek on 19.04.2020.
 */
public class ViewManager {

    private WeatherManager weatherManager;

    public ViewManager(WeatherManager weatherManager) {
        this.weatherManager = weatherManager;
    }

    public void showMainWindow(){
        System.out.println("Show main window called");

        BaseController controller = new MainWindowController(weatherManager,this,"/com/weatherforecast/MainWindow.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));
        fxmlLoader.setController(controller);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        }catch(IOException e){
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }
}
