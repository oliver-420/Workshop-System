package at.htl.workshopsystem.view.controller;

import at.htl.workshopsystem.WorkshopSystem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public Button btn_carSearchTest;
    @FXML
    private Label welcomeText;

    @FXML
    private Button btn_customers;
    @FXML
    private Button btn_tasks;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to our Workshop-System");
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_customers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WorkshopSystem.changeScene(event, "customers.fxml", "Customers");
            }
        });

        btn_carSearchTest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WorkshopSystem.changeScene(event, "carSearch.fxml", "Car Search Test");
            }
        });

        btn_tasks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WorkshopSystem.changeScene(event, "tasks.fxml", "Tasks");
            }
        });
    }
}