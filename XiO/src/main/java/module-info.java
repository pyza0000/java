module com.example.xio {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.xio to javafx.fxml;
    exports com.example.xio;
}