package at.htl.workshopsystem;

import at.htl.workshopsystem.model.Mechanic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        Mechanic mechanic = new Mechanic("Test", 10.0);
        MechanicRepository mechanicRepository = new MechanicRepository();
        System.out.println(mechanicRepository.insert(mechanic).getId());

        mechanic.setName("Test2");
        mechanicRepository.update(mechanic);
        System.out.println(mechanic.getName());

        mechanicRepository.delete(1);

        mechanicRepository.getAll().forEach(m -> System.out.println(m.getName() + m.getId()));

        Mechanic mechanic1 = mechanicRepository.getById(19);

        System.out.println(mechanic1.getId());
    }

    public static void main(String[] args) {
        launch();
    }
}