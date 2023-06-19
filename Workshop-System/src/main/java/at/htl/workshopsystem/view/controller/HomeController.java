package at.htl.workshopsystem.view.controller;

import at.htl.workshopsystem.WorkshopSystem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public Button btn_carSearchTest;
    @FXML
    private Button btn_customers;
    @FXML
    private Button btn_tasks;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_customers.setOnAction(event -> WorkshopSystem.changeScene(event, "customers.fxml", "Customers"));

        btn_carSearchTest.setOnAction(event -> WorkshopSystem.changeScene(event, "addTask.fxml", "Car Search Test"));

        btn_tasks.setOnAction(event -> WorkshopSystem.changeScene( "tasks.fxml", "Tasks"));
    }
}