module at.htl.workshopsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.oracle.database.jdbc;
    //requires itextpdf;
    //requires barcode4j;
    requires java.desktop;

    opens at.htl.workshopsystem to javafx.fxml;
    exports at.htl.workshopsystem;
    exports at.htl.workshopsystem.view.controller;
    opens at.htl.workshopsystem.view.controller to javafx.fxml;
}