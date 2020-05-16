module com.weatherforecast {
    requires javafx.controls;
    requires javafx.fxml;
    requires owm.japis;
    requires java.sql;
    requires gson;
    requires org.controlsfx.controls;


    opens com.weatherforecast to javafx.fxml;
    opens com.weatherforecast.controller to javafx.fxml;
    opens com.weatherforecast.view to javafx.fxml;
    opens com.weatherforecast.model to javafx.fxml, gson;

    exports com.weatherforecast;
}