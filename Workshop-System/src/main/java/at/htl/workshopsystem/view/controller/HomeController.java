package at.htl.workshopsystem.view.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class HomeController {
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
                changeScene(event, "customers.fxml", "Customers");
            }
        });

        btn_tasks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeScene(event, "tasks.fxml", "Tasks");
            }
        });
    }

    //change scene to customers or tasks
    public void changeScene(ActionEvent event, String fxmlFile, String title){
        Parent root = null;

        try{
            FXMLLoader loader = new FXMLLoader(HomeController.class.getResource(fxmlFile));
            root = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
}