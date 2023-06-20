package at.htl.workshopsystem.view.controller;

import at.htl.workshopsystem.WorkshopSystem;
import at.htl.workshopsystem.controller.database.*;
import at.htl.workshopsystem.model.*;
import atlantafx.base.theme.Styles;
import com.fasterxml.jackson.databind.JsonNode;
import com.itextpdf.text.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;

public class TasksController {
    public javafx.scene.control.Button homeBtn;
    public javafx.scene.control.Button customersBtn;
    public javafx.scene.control.Button tasksBtn;
    public javafx.scene.control.Button partRepoBtn;
    public TextField durationTf;
    public Button finishTaskBtn;
    public Button finishSubTaskBtn;
    public ComboBox mechanicsDrd;
    public ListView<Task> lvTasks;
    public ListView<SubTask> lvSubTasks;
    public Button newTaskBtn;
    public TextField quantityField;
    public Button deletePartBtn;
    public Button addPartBtn;
    public ListView<Part> lvParts;
    private ObservableList<Mechanic> mechanics = FXCollections.observableArrayList();
    private ObservableList<Task> tasks = FXCollections.observableArrayList();
    private ObservableList<SubTask> subTasks = FXCollections.observableArrayList();
    private ObservableList<Part> olParts = FXCollections.observableArrayList();
    private final TaskRepository taskRepository = new TaskRepository();
    private final SubTaskRepository subTaskRepository = new SubTaskRepository();
    private final MechanicRepository mechanicRepository = new MechanicRepository();
    private final TaskPartMappingRepository taskPartMappingRepository = new TaskPartMappingRepository();
    public void initialize() {
        WorkshopSystem.onPageChange(this.homeBtn, this.customersBtn, this.tasksBtn, this.partRepoBtn);

        newTaskBtn.setOnAction(event -> {
            WorkshopSystem.changeScene(event,"addTask.fxml", "New Task");
        });
        newTaskBtn.getStyleClass().addAll(
                Styles.MEDIUM, Styles.ACCENT, Styles.BUTTON_OUTLINED
        );

        deletePartBtn.setDisable(true);
        addPartBtn.setDisable(true);
        deletePartBtn.getStyleClass().addAll(
                Styles.ROUNDED, Styles.DANGER, Styles.BUTTON_OUTLINED, Styles.MEDIUM
        );
        addPartBtn.getStyleClass().addAll(
                Styles.ROUNDED, Styles.ACCENT, Styles.BUTTON_OUTLINED, Styles.MEDIUM
        );
        tasks.addAll(taskRepository.getAll());
        lvTasks.setItems(tasks);

        mechanics.addAll(mechanicRepository.getAll());
        mechanicsDrd.setItems(mechanics);

        finishTaskBtn.setDisable(true);
        finishSubTaskBtn.setDisable(true);
        durationTf.setDisable(true);

        this.lvTasks.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                subTasks.clear();
                subTasks.addAll(subTaskRepository.getByTaskId(newValue.getId()));
                lvSubTasks.setItems(subTasks);

                olParts.clear();
                olParts.addAll(taskPartMappingRepository.getPartsByTaskId(newValue.getId()));
                lvParts.setItems(olParts);
                addPartBtn.setDisable(false);

                lvParts.getSelectionModel().selectedItemProperty().addListener((observable1, oldValue1, newValue1) -> {
                    if (newValue1 != null) {
                        deletePartBtn.setDisable(false);
                    }
                });

                deletePartBtn.setOnAction(event ->{
                    taskPartMappingRepository.deleteByTaskIdAndPartId(lvTasks.getSelectionModel().getSelectedItem().getId(), lvParts.getSelectionModel().getSelectedItem().getSerialNumber());
                    olParts.remove(lvParts.getSelectionModel().getSelectedItem());
                });

                addPartBtn.setOnAction(event -> {
                    try {
                        Node node = FXMLLoader.load(getClass().getResource("/at/htl/workshopsystem/" + "newPart.fxml"));
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        VBox layout =new VBox ((VBox) node.lookup("#hbmain"));

                        layout.setAlignment(Pos.CENTER);
                        Scene scene = new Scene(layout, 500, 400);

                        stage.setTitle("Dialog");
                        stage.setScene(scene);
                        stage.show();

                        ListView<Part> lvParts = (ListView<Part>) layout.lookup("#lvParts");
                        PartRepository partRepository = new PartRepository();
                        ObservableList<Part> parts = FXCollections.observableArrayList();
                        parts.addAll(partRepository.getAll());
                        lvParts.setItems(parts);

                        lvParts.setOnMouseClicked( event1 -> {
                            if(lvParts.getSelectionModel().getSelectedItem() != null) {
                                taskPartMappingRepository.insert(
                                        lvTasks.getSelectionModel().getSelectedItem(),
                                        lvParts.getSelectionModel().getSelectedItem(),
                                        1,
                                        lvParts.getSelectionModel().getSelectedItem().getPrice());
                                WorkshopSystem.changeScene("tasks.fxml", "Tasks");
                                stage.close();
                            }
                        });
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                });


                mechanicsDrd.getSelectionModel().select(mechanics.stream().filter(mechanic -> mechanic.getId() == newValue.getFkMechanic()).findFirst().orElse(null));

                finishTaskBtn.setDisable(!subTasks.stream().allMatch(SubTask::getIsDone));

                lvSubTasks.getSelectionModel().selectedItemProperty().addListener((observable1, oldValueSubTask, newValueSubTask) -> {
                    if (newValueSubTask != null) {
                        durationTf.setDisable(false);

                        if(newValueSubTask.getIsDone()){
                            durationTf.setDisable(true);
                            finishSubTaskBtn.setDisable(true);
                        }
                        else{
                            durationTf.setDisable(false);

                            durationTf.textProperty().addListener((observable2, oldValueDuration, newValueDuration) -> {
                                if (newValueDuration.matches("\\d*\\.?\\d*") || newValueDuration.matches("\\d*\\,?\\d*") && !newValueDuration.isEmpty()) {
                                    finishSubTaskBtn.setDisable(false);
                                } else {
                                    finishSubTaskBtn.setDisable(true);
                                }
                            });
                        }
                        if(subTasks.stream().allMatch(SubTask::getIsDone)){
                            finishTaskBtn.setDisable(false);
                        }
                        else{
                            finishTaskBtn.setDisable(true);
                        }
                    } else {
                        finishSubTaskBtn.setDisable(true);
                        durationTf.setDisable(true);
                    }
                });
            }
        });

        finishSubTaskBtn.setOnAction(event -> {
            finishSubTask();
            subTasks.clear();
            subTasks.addAll(subTaskRepository.getByTaskId(lvTasks.getSelectionModel().getSelectedItem().getId()));
            lvSubTasks.setItems(subTasks);
        });

        finishTaskBtn.setOnAction(event -> {
            finishTask();
            tasks.clear();
            tasks.addAll(taskRepository.getAll());
            lvTasks.setItems(tasks);

            WorkshopSystem.changeScene(event,"finishTask.fxml", "Finish Task");
        });
    }

    public void finishSubTask() {
        SubTask subTask = lvSubTasks.getSelectionModel().getSelectedItem();
        subTask.setIsDone(true);
        subTask.setDuration(Double.parseDouble(durationTf.getText().replace(',', '.')));
        durationTf.clear();
        subTaskRepository.update(subTask);
    }
    public void finishTask() {
        Task task = lvTasks.getSelectionModel().getSelectedItem();
        Mechanic mechanic = (Mechanic) mechanicsDrd.getSelectionModel().getSelectedItem();
        task.setFkMechanic(mechanic.getId());
        FinishTaskId.getInstance(task.getId());

        taskRepository.update(task);
    }

    private void showAddPartDialog(){

    }
}
