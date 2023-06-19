package at.htl.workshopsystem.view.controller;

import at.htl.workshopsystem.WorkshopSystem;
import at.htl.workshopsystem.controller.database.CustomerRepository;
import at.htl.workshopsystem.controller.database.MechanicRepository;
import at.htl.workshopsystem.controller.database.SubTaskRepository;
import at.htl.workshopsystem.controller.database.TaskRepository;
import at.htl.workshopsystem.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import org.w3c.dom.events.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;



public class AddTaskController {
    public Button homeBtn;
    public Button customersBtn;
    public Button tasksBtn;
    public Button partRepoBtn;
    public Button searchCarBtn;
    public Pane pnCar;
    public ComboBox cbCustomer;
    public ComboBox cbMechanic;
    public Button addTaskBtn;
    public TextField nameField;
    public TextField modelField;
    public DatePicker startDate;
    public TextField makerField;
    public ListView<SubTask> subTaskLv;
    public Button addSubTaskBtn;
    public TextField subTaskDescField;

    @FXML
    private TextField searchField;

    final CustomerRepository customerRepository = new CustomerRepository();
    final MechanicRepository mechanicRepository = new MechanicRepository();
    final SubTaskRepository subTaskRepository = new SubTaskRepository();
    final TaskRepository taskRepository = new TaskRepository();
    ObservableList<Customer> olCustomer = FXCollections.observableArrayList();
    ObservableList<Mechanic> olMechanics = FXCollections.observableArrayList();
    ObservableList<SubTask> olSubTasks = FXCollections.observableArrayList();

    public void initialize() {
        WorkshopSystem.onPageChange(this.homeBtn, this.customersBtn, this.tasksBtn, this.partRepoBtn);
        makerField.setDisable(true);
        modelField.setDisable(true);

        setComboBoxes();
        subTaskLv.setItems(olSubTasks);

        this.addSubTaskBtn.setOnAction(event -> addSubTask());
        this.addTaskBtn.setOnAction(event -> addTask());
        this.searchCarBtn.setOnAction(event -> setCarNode());
        this.startDate.setValue(java.time.LocalDate.now());
    }

    private void addSubTask() {
        String name = this.subTaskDescField.getText();
        if(name.isEmpty()){
            displayErrorMessage("Fehler", "Bitte geben Sie einen Beschreibungstext ein");
            return;
        }
        olSubTasks.add(new SubTask(name, 0, false));
        subTaskLv.setItems(olSubTasks);
    }


    private void setCarNode() {
        try {
            Node node = FXMLLoader.load(getClass().getResource("/at/htl/workshopsystem/" + "carSearch.fxml"));
            /*AnchorPane main = (AnchorPane) node.lookup("#pane");
            DialogPane pane = new DialogPane(500, 500);
            pane.getChildren().add(node);
            alert.setHeaderText("Auto suchen");
            alert.setDialogPane(pane);
            alert.showAndWait();*/

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            HBox hbMain = (HBox) node.lookup("#mainPage");
            VBox layout = new VBox(hbMain);
            layout.setAlignment(Pos.CENTER);


            Scene scene = new Scene(layout, 600, 500);

            stage.setTitle("Dialog");
            stage.setScene(scene);
            stage.show();

            stage.onCloseRequestProperty().setValue(event -> {
                Car car = CarSearchController.car;
                if(car != null){
                    makerField.setText(car.getManufacturer());
                    modelField.setText(car.getModel());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTask() {
        if (this.nameField.getText().isEmpty() || this.modelField.getText().isEmpty() || this.makerField.getText().isEmpty()) {
            displayErrorMessage("Fehler", "Bitte füllen Sie alle Felder aus");
            return;
        }

        Car currentCar = CarSearchController.car;
        Customer customer = (Customer) cbCustomer.getValue();
        Mechanic mechanic = (Mechanic) cbMechanic.getValue();
        LocalDateTime date = startDate.getValue().atStartOfDay();
        if(currentCar == null){
            displayErrorMessage("Fehler", "Bitte wählen Sie ein Auto aus");
            return;
        }
        Long carId = currentCar.getId();
        Task task = null;
        try {
            if(customer.getId() != null && mechanic.getId() != null){

                task = new Task(nameField.getText(), date, mechanic.getId(), carId, customer.getId());
                taskRepository.insert(task);
            }
        }
        catch (Exception e) {
            displayErrorMessage("Fehler", "Bitte geben Sie ein gültige Werte ein");
            return;
        }

        try {
            if (task.getId() != null)
                saveSubTasks(task.getId());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erfolgreich");
            alert.setHeaderText("Task wurde erfolgreich hinzugefügt");

            java.util.Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);

            if (button == ButtonType.OK) {
                WorkshopSystem.changeScene("tasks.fxml", "Tasks");
            } else {
                System.out.println("canceled");
            }
        }
        catch (Exception e) {
            displayErrorMessage("Fehler", "Fehler beim Speichern der Subtasks" + e.getMessage());
            return;
        }
    }

    private void saveSubTasks(Long taskId) {
        if(subTaskLv.getItems().isEmpty()){
            return;
        }
        try {
            subTaskLv.getItems().forEach(subTask -> {
                subTaskRepository.insert(subTask, taskId);
            });
        }
        catch (Exception e) {
            displayErrorMessage("Fehler", "Bitte geben Sie ein gültige Werte ein");
            return;
        }
    }

    private void displayErrorMessage(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void setComboBoxes() {
        olCustomer.addAll(customerRepository.getAll());
        olMechanics.addAll(mechanicRepository.getAll());

        cbCustomer.setItems(olCustomer);
        cbMechanic.setItems(olMechanics);
    }
}
