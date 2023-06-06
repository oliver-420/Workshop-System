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

         Mechanic mechanic = new Mechanic("Test", 10.0);
        MechanicRepository mechanicRepository = new MechanicRepository();
        System.out.println(mechanicRepository.insert(mechanic).getId());

        mechanic.setName("Test2");
        mechanicRepository.update(mechanic);


        //mechanicRepository.delete(1);

        mechanicRepository.getAll().forEach(m -> System.out.println(m.getName() + m.getId()));

        Mechanic mechanic1 = mechanicRepository.getById(3);

        System.out.println(mechanic1.getId());

        Car car = new Car("Test", "test", 1, 1, "e", "e", "e");

        CarRepository carRepository = new CarRepository();
        System.out.println(carRepository.insert(car).getId());

        car.setModel("Test2");
        carRepository.update(car);
        System.out.println(carRepository.getById(car.getId()).getModel());

        carRepository.delete(2);

        carRepository.getAll().forEach(c -> System.out.println(c.getModel() + c.getId()));
    }

    public static void main(String[] args) {
        launch();
    }
}