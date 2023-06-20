package at.htl.workshopsystem.view.controller;

import at.htl.workshopsystem.WorkshopSystem;
import at.htl.workshopsystem.controller.database.MechanicRepository;
import at.htl.workshopsystem.controller.database.SubTaskRepository;
import at.htl.workshopsystem.controller.database.TaskPartMappingRepository;
import at.htl.workshopsystem.controller.database.TaskRepository;
import at.htl.workshopsystem.model.Mechanic;
import at.htl.workshopsystem.model.SubTask;
import at.htl.workshopsystem.model.Task;
import at.htl.workshopsystem.model.TaskPartMapping;
import at.htl.workshopsystem.model.factory.CarFactory;
import atlantafx.base.theme.Styles;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.skin.ChoiceBoxSkin;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Objects;

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
    public ListView taskPartMapLv;
    private ObservableList<Mechanic> mechanics = FXCollections.observableArrayList();
    private ObservableList<Task> tasks = FXCollections.observableArrayList();
    private ObservableList<SubTask> subTasks = FXCollections.observableArrayList();
    private ObservableList<TaskPartMapping> taskPartMappings = FXCollections.observableArrayList();
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

                finishTaskBtn.setDisable(!subTasks.stream().allMatch(SubTask::getIsDone));

                mechanicsDrd.getSelectionModel().select(mechanics.stream().filter(mechanic -> mechanic.getId() == newValue.getFkMechanic()).findFirst().orElse(null));

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

        taskRepository.update(task);
    }
}
