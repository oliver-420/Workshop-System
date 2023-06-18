package at.htl.workshopsystem.view.controller;


import at.htl.workshopsystem.model.Car;
import at.htl.workshopsystem.model.factory.CarFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;

import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarSearchController {

    private static final String API_ENDPOINT = "https://api.api-ninjas.com/v1/";
    public TextField modelField;
    public TextField manufacturerField;
    public TextField prodYearField;
    public TextField fuelTypeField;
    public TextField ownerField;
    public TextField numberPlateField;
    public TextField registrationField;
    public ListView lvCars;

    @FXML
    private TextField searchField;

    private ObservableList<Car> carList = FXCollections.observableArrayList();

    public void initialize() {

        searchField.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                searchCars(searchField.getText());
            }
        } );
    }

    private void searchCars(String query) {
            try {
                URL url = new URL(API_ENDPOINT + "/cars?limit=50&model=" + query);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("accept", "application/json");
                connection.setRequestProperty("X-Api-Key", "EBJ7x/Fimlf9lw5qUkZ6BQ==e2jdO7wFxjCanXzO");
                InputStream responseStream = connection.getInputStream();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(responseStream);
                createCarList(root);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void createCarList(JsonNode root){
        carList.clear();

        root.elements().forEachRemaining(c -> {
            carList.add(CarFactory.createCarFromJsonNode(c));
        });

        if(carList != null){
            lvCars.setItems(carList);
        }
    }

    /*private void editCar(Car car) {
        TextInputDialog dialog = new TextInputDialog(car.getModel());
        dialog.setTitle("Edit Car");
        dialog.setHeaderText("Edit Model");
        dialog.setContentText("Enter new model:");

        dialog.showAndWait().ifPresent(newModel -> car.setModel(newModel));
    }

    private ListCell<Car> carCellFactory(ListView<Car> param) {
        return new ListCell<>() {
            @Override
            protected void updateItem(Car item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getModel() + " (" + item.getManufacturer() + ")");
                }
            }
        };
    }

    private Callback<ListView<Car>, ListCell<Car>> carCellFactory = new Callback<>() {
        @Override
        public ListCell<Car> call(ListView<Car> param) {
            return new ListCell<>() {
                @Override
                protected void updateItem(Car item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getModel() + " (" + item.getManufacturer() + ")");
                    }
                }
            };
        }
    };*/
}
