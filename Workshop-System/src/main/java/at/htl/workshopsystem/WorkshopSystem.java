package at.htl.workshopsystem;

import at.htl.workshopsystem.controller.database.CarRepository;
import at.htl.workshopsystem.controller.database.MechanicRepository;
import at.htl.workshopsystem.model.Car;
import at.htl.workshopsystem.model.Mechanic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WorkshopSystem extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WorkshopSystem.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}