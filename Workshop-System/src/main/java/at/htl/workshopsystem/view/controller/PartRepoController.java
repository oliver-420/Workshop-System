package at.htl.workshopsystem.view.controller;

import at.htl.workshopsystem.WorkshopSystem;
import at.htl.workshopsystem.controller.database.PartRepository;
import at.htl.workshopsystem.controller.database.TaskPartMappingRepository;
import at.htl.workshopsystem.model.Car;
import at.htl.workshopsystem.model.Part;
import at.htl.workshopsystem.model.TaskPartMapping;
import atlantafx.base.theme.Styles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.Serial;
import java.util.UUID;

public class PartRepoController {
    public Button homeBtn;
    public Button customersBtn;
    public Button tasksBtn;
    public Button partRepoBtn;
    public VBox pnItems;
    public TextField nameField;
    public TextField manufacturerField;
    public TextField priceField;
    public TextField upchargeField;
    public TextField quantityField;

    @FXML
    private Button newPartBtn;
    @FXML
    private Button deletePartBtn;
    @FXML
    private TextField searchField;

    private FilteredList<Part> parts;

    private PartRepository partRepository = new PartRepository();
    private TaskPartMappingRepository taskPartMappingRepository = new TaskPartMappingRepository();

    public void initialize() {
        setParts();
        WorkshopSystem.onPageChange(this.homeBtn, this.customersBtn, this.tasksBtn, this.partRepoBtn);
        displayParts();


        this.newPartBtn.setOnAction(event -> {
            try {
                String name = this.nameField.getText();
                String manufacturer = this.manufacturerField.getText();
                Double price = Double.parseDouble(this.priceField.getText());
                Double upcharge = Double.parseDouble(this.upchargeField.getText());
                int quantity = Integer.parseInt(this.quantityField.getText());
                String serial = UUID.randomUUID().toString();


                Part part = new Part(serial, name, manufacturer, price, upcharge, quantity);
                partRepository.insert(part);

                setParts();
                displayParts();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error while creating new Part");
                alert.setContentText("Please check your input");
                alert.showAndWait();
                e.printStackTrace();
            }
        });

        this.newPartBtn.getStyleClass().addAll(
                Styles.ACCENT, Styles.SMALL
        );

        this.searchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                parts.setPredicate(s -> s.getName().toLowerCase().contains(this.searchField.getText().toLowerCase()));
                displayParts();
            }
        });
    }

    private void setParts() {
        PartRepository partRepository = new PartRepository();
        ObservableList<Part> partList = FXCollections.observableArrayList();
        partList.addAll(partRepository.getAll());
        parts = new FilteredList<>(partList, p -> true);
    }

    private void displayParts() {
        pnItems.getChildren().clear();
        Node[] nodes = new Node[parts.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("/at/htl/workshopsystem/" + "Item.fxml"));

                Label name = (Label) nodes[i].lookup("#name");
                name.setText(parts.get(i).getName());

                Label manufacturer = (Label) nodes[i].lookup("#manufacturer");
                manufacturer.setText(parts.get(i).getManufacturer());

                Label price = (Label) nodes[i].lookup("#price");
                price.setText(String.valueOf(parts.get(i).getPrice()) + " €");

                Label additionalCharge = (Label) nodes[i].lookup("#upcharge");
                additionalCharge.setText(String.valueOf(parts.get(i).getAdditionalCharge()) + " €");

                TextField quantity = (TextField) nodes[i].lookup("#quantity");
                quantity.setText(String.valueOf(parts.get(i).getQuantity()));

                Button lowerQuantity = (Button) nodes[i].lookup("#lowerQuantityBtn");
                lowerQuantity.setOnAction(event -> {
                    Part part = parts.get(j);
                    if (part.getQuantity() > 0) {
                        part.setQuantity(part.getQuantity() - 1);
                        quantity.setText(String.valueOf(part.getQuantity()));
                        partRepository.update(part);
                    }
                });

                Button raiseQuantity = (Button) nodes[i].lookup("#raiseQuantityBtn");
                raiseQuantity.setOnAction(event -> {
                    Part part = parts.get(j);
                    part.setQuantity(part.getQuantity() + 1);
                    quantity.setText(String.valueOf(part.getQuantity()));
                    partRepository.update(part);
                });

                Button deletePart = (Button) nodes[i].lookup("#deleteBtn");
                deletePart.setOnAction(event -> {
                    deletePart(parts.get(j).getSerialNumber());
                });

                deletePart.getStyleClass().addAll(
                        Styles.DANGER, Styles.SMALL
                );

                nodes[j].setStyle("-fx-border-width: 1");
                nodes[j].setStyle("-fx-border-color:  #ffffff");

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #3f48d8");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #ffffff");
                });


                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deletePart(String serialNumber) {
        partRepository.delete(serialNumber);
        setParts();
        displayParts();
    }
}
