module com.example.thirdbesthack {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.thirdbesthack to javafx.fxml;
    exports com.example.thirdbesthack;
}