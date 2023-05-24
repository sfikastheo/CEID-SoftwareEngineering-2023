module com.live_the_city {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens com.live_the_city to javafx.fxml;
    exports com.live_the_city;
}