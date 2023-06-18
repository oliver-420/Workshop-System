package at.htl.workshopsystem.view.controller;

import at.htl.workshopsystem.WorkshopSystem;
import at.htl.workshopsystem.controller.database.SubTaskRepository;
import at.htl.workshopsystem.controller.database.TaskRepository;
import at.htl.workshopsystem.model.SubTask;
import at.htl.workshopsystem.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class TasksController {
    public Button homeBtn;
    public Button customersBtn;
    public Button tasksBtn;
    public Button finishTaskBtn;
    public Button finishSubTaskBtn;
    public ListView<Task> lvTasks;
    public ListView<SubTask> lvSubTasks;
    public Button partRepoBtn;
    private ObservableList<Task> tasks = FXCollections.observableArrayList();
    private ObservableList<SubTask> subTasks = FXCollections.observableArrayList();
    private final TaskRepository taskRepository = new TaskRepository();
    private final SubTaskRepository subTaskRepository = new SubTaskRepository();
    public void initialize() {
        WorkshopSystem.onPageChange(this.homeBtn, this.customersBtn, this.tasksBtn, this.partRepoBtn);

        tasks.addAll(taskRepository.getAll());

        lvTasks.setItems(tasks);

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
                this.finishTaskBtn.setDisable(false);
                this.finishSubTaskBtn.setDisable(false);

                subTasks.clear();

                subTasks.addAll(subTaskRepository.getByTaskId(newValue.getId()));

                lvSubTasks.setItems(subTasks);

                lvSubTasks.setCellFactory(param -> new ListCell<SubTask>() {
                    @Override
                    protected void updateItem(SubTask item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null || item.getDescription() == null) {
                            setText(null);
                        } else {
                            setText(item.getDescription());
                        }
                    }
                });
            } else {
                this.finishTaskBtn.setDisable(true);
                this.finishSubTaskBtn.setDisable(true);
            }
        });
        finishSubTaskBtn.setOnAction(event -> {

        });
    }
}
