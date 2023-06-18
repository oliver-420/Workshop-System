package at.htl.workshopsystem.view.controller;


import at.htl.workshopsystem.PropertiesReader;
import at.htl.workshopsystem.model.Car;
import at.htl.workshopsystem.model.factory.CarFactory;
import at.htl.workshopsystem.webCrawler.WebCrawler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public ListView<Car> lvCars;
    public Button saveBtn;
    public Image carImg;
    public ImageView carImgView;

    @FXML
    private TextField searchField;

    private ObservableList<Car> carList = FXCollections.observableArrayList();

    public void initialize() {
        this.lvCars.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.modelField.setText(newValue.getModel());
                this.manufacturerField.setText(newValue.getManufacturer());
                this.prodYearField.setText(String.valueOf(newValue.getProductionYear()));
                this.fuelTypeField.setText(newValue.getFuel());
                this.ownerField.setText(newValue.getOwner());
                this.numberPlateField.setText(newValue.getNumberPlate());
                this.registrationField.setText(String.valueOf(newValue.getRegistrationYear()));
                setImage(newValue.getProductionYear(), newValue.getManufacturer(), newValue.getModel());
            }
        });

        this.searchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                searchCars(searchField.getText());
            }
        });

        this.saveBtn.setOnAction(event -> {
            saveCar();
        });
    }

    private void setImage(int year, String make, String model) {
        String imgUrl = WebCrawler.getImageURL( year + "+" + make + "+" + model);
        if (imgUrl != null) {
            this.carImg = new Image(imgUrl);
            carImgView.setImage(carImg);

            System.out.println( carImg.getUrl());
        }
    }

    private void saveCar() {
        Car currentCar = lvCars.getSelectionModel().getSelectedItem();
        if (currentCar == null) {
            currentCar = new Car();
        }
        try {
            currentCar.setModel(modelField.getText());
            currentCar.setManufacturer(manufacturerField.getText());
            currentCar.setProductionYear(Integer.parseInt(prodYearField.getText()));
            currentCar.setFuel(fuelTypeField.getText());
            currentCar.setOwner(ownerField.getText());
            currentCar.setNumberPlate(numberPlateField.getText());
            currentCar.setRegistrationYear(Integer.parseInt(registrationField.getText()));
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while saving");
            alert.setContentText("Please check your input");
            alert.showAndWait();
        }
    }

    private void searchCars(String query) {
        try {
            URL url = new URL(API_ENDPOINT + "/cars?limit=50&model=" + query);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("X-Api-Key", PropertiesReader.getProperty("apininja_api_key"));
            InputStream responseStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseStream);
            createCarList(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createCarList(JsonNode root) {
        carList.clear();

        root.elements().forEachRemaining(c -> {
            carList.add(CarFactory.createCarFromJsonNode(c));
        });

        if (carList != null) {
            lvCars.setItems(carList);
        }
    }
}
