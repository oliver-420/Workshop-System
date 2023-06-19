package at.htl.workshopsystem.view.controller;

import at.htl.workshopsystem.WorkshopSystem;
import at.htl.workshopsystem.model.Car;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;


public class AddTaskController {
    public Button homeBtn;
    public Button customersBtn;
    public Button tasksBtn;
    public Button partRepoBtn;
    public Button searchCarBtn;
    public Pane pnCar;

    @FXML
    private TextField searchField;

    public void initialize() {
        WorkshopSystem.onPageChange(this.homeBtn, this.customersBtn, this.tasksBtn, this.partRepoBtn);

        this.searchCarBtn.setOnAction(event -> {
            setCarNode();
        });


    }


    private void setCarNode(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        try {
           /* Node node =  FXMLLoader.load(getClass().getResource("/at/htl/workshopsystem/" + "carSearch.fxml"));
            AnchorPane main = (AnchorPane) node.lookup("#pane");
            DialogPane pane = new DialogPane(500, 500);
            pane.getChildren().add(node);
            alert.setHeaderText("Auto suchen");
            alert.setDialogPane(pane);
            alert.showAndWait();*/

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            Label label = new Label("text");

            VBox layout = new VBox(label);
            layout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(layout, 200, 100);

            stage.setTitle("Dialog");
            stage.setScene(scene);
            stage.show();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
