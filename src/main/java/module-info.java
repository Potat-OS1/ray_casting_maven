module com.example.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires aparapi;


    opens com.example.ray_casting;
    exports com.example.ray_casting;
}