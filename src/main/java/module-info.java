module com.weatherforecast {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.weatherforecast to javafx.fxml;
    opens com.weatherforecast.controller to javafx.fxml;

    exports com.weatherforecast;
}