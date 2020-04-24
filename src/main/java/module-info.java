module com.weatherforecast {
    requires javafx.controls;
    requires javafx.fxml;
    requires owm.japis;
    requires java.sql;

    opens com.weatherforecast to javafx.fxml;
    opens com.weatherforecast.controller to javafx.fxml;
    opens com.weatherforecast.view to javafx.fxml;
    opens com.weatherforecast.model to javafx.fxml;

    exports com.weatherforecast;
}