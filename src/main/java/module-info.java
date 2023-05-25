module com.example.thirdbesthack {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires opencsv;
    requires commons.csv;

    opens com.example.thirdbesthack to javafx.fxml;
    exports com.example.thirdbesthack;
}