module com.example.project_3_software_methodology {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project_3_software_methodology to javafx.fxml;
    exports com.example.project_3_software_methodology;
}