module at.htl.workshopsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.htl.workshopsystem to javafx.fxml;
    exports at.htl.workshopsystem;
}