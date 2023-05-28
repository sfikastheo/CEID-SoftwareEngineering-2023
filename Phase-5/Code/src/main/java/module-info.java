module com.live_the_city {
    requires transitive java.sql;
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.live_the_city to javafx.fxml;
    exports com.live_the_city;
}
