package at.htl.workshopsystem;

import at.htl.workshopsystem.controller.database.CarRepository;
import at.htl.workshopsystem.controller.database.MechanicRepository;
import at.htl.workshopsystem.model.Car;
import at.htl.workshopsystem.model.Mechanic;
import at.htl.workshopsystem.view.controller.HomeController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class WorkshopSystem extends Application {
    public static Stage stage = null;
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Home");
        stage.setScene(scene);
        this.stage = stage;
        //MetaData.parent = root;
        stage.show();
    }

    public static void changeScene(ActionEvent event, String fxmlFile, String title){
        Parent root = null;

        try{
            FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("/at/htl/workshopsystem/" + fxmlFile));
            root = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}