module at.htl.workshopsys.workshopsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.htl.workshopsys.workshopsystem to javafx.fxml;
    exports at.htl.workshopsys.workshopsystem;
}