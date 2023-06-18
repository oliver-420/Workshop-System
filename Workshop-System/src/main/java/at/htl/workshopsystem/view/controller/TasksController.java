package at.htl.workshopsystem.view.controller;

import at.htl.workshopsystem.WorkshopSystem;
import at.htl.workshopsystem.controller.database.MechanicRepository;
import at.htl.workshopsystem.controller.database.SubTaskRepository;
import at.htl.workshopsystem.controller.database.TaskRepository;
import at.htl.workshopsystem.model.Mechanic;
import at.htl.workshopsystem.model.SubTask;
import at.htl.workshopsystem.model.Task;
import at.htl.workshopsystem.model.factory.CarFactory;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.skin.ChoiceBoxSkin;

import java.util.List;

public class TasksController {
    public Button homeBtn;
    public Button customersBtn;
    public Button tasksBtn;
    public Button finishTaskBtn;
    public Button finishSubTaskBtn;
    public ComboBox mechanicsDrd;
    public ListView<Task> lvTasks;
    public ListView<SubTask> lvSubTasks;
    private ObservableList<Mechanic> mechanics = FXCollections.observableArrayList();
    private ObservableList<Task> tasks = FXCollections.observableArrayList();
    private ObservableList<SubTask> subTasks = FXCollections.observableArrayList();
    private final TaskRepository taskRepository = new TaskRepository();
    private final SubTaskRepository subTaskRepository = new SubTaskRepository();
    private final MechanicRepository mechanicRepository = new MechanicRepository();
    public void initialize() {
        WorkshopSystem.onPageChange(this.homeBtn, this.customersBtn, this.tasksBtn);

        tasks.addAll(taskRepository.getAll());
        lvTasks.setItems(tasks);

        mechanics.addAll(mechanicRepository.getAll());
        mechanicsDrd.setItems(mechanics);

        finishTaskBtn.setDisable(true);
        finishSubTaskBtn.setDisable(true);

        lvTasks.setCellFactory(param -> new ListCell<Task>() {
            @Override
            protected void updateItem(Task item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        this.lvTasks.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                subTasks.clear();

                subTasks.addAll(subTaskRepository.getByTaskId(newValue.getId()));

                lvSubTasks.setItems(subTasks);

                lvSubTasks.setCellFactory(param -> new ListCell<SubTask>() {
                    @Override
                    protected void updateItem(SubTask item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null || item.getDescription() == null) {
                            setText(null);
                            finishSubTaskBtn.setDisable(true);
                        } else {
                            setText(item.getDescription() + " - " + (item.getIsDone() ? "Done" : "Not done"));
                            finishSubTaskBtn.setDisable(false);

                            if(subTasks.stream().allMatch(subTask -> subTask.getIsDone())) {
                                finishTaskBtn.setDisable(false);
                            }else {
                                finishTaskBtn.setDisable(true);
                            }
                        }
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
        });
    }

    public void finishSubTask() {
        SubTask subTask = lvSubTasks.getSelectionModel().getSelectedItem();
        subTask.setIsDone(true);
        subTaskRepository.updateIsDone(subTask);
    }

    public void finishTask() {
        Task task = lvTasks.getSelectionModel().getSelectedItem();
        taskRepository.delete(task.getId());
        //change scene to pdfsite
    }
}
