package at.htl.workshopsystem;

import at.htl.workshopsystem.controller.database.MechanicRepository;
import at.htl.workshopsystem.model.Mechanic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oracle.jdbc.replay.driver.ReplayStatisticsMBeanImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        MechanicRepository mechanicRepository = new MechanicRepository();
        System.out.println(mechanicRepository.insert(new Mechanic("Test", 10.0)).get_hourlyWage());
    }

    public static void main(String[] args) {
        launch();
    }
}