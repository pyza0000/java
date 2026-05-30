module com.example.zpo4 {

    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.zpo4 to javafx.fxml;

    exports com.example.zpo4;
}