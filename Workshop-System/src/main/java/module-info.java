module at.htl.workshopsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.oracle.database.jdbc;

    opens at.htl.workshopsystem to javafx.fxml;
    exports at.htl.workshopsystem;
}