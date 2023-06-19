package at.htl.workshopsystem.view.controller;

import at.htl.workshopsystem.WorkshopSystem;
import at.htl.workshopsystem.model.Car;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AddTaskController {
    public Button homeBtn;
    public Button customersBtn;
    public Button tasksBtn;
    public Button partRepoBtn;
    public Pane pnCar;

    @FXML
    private TextField searchField;

    public void initialize() {
        WorkshopSystem.onPageChange(this.homeBtn, this.customersBtn, this.tasksBtn, this.partRepoBtn);

        setCarNode();


    }


    private void setCarNode(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Select a Car");
        alert.setHeaderText("Please select a car");
        try {
            Node node =  FXMLLoader.load(getClass().getResource("/at/htl/workshopsystem/" + "carSearch.fxml"));
            HBox main = (HBox) node.lookup("#mainPage");
            alert.getDialogPane().setContent(main);
            alert.showAndWait();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
