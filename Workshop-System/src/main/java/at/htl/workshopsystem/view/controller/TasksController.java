package at.htl.workshopsystem.view.controller;

import at.htl.workshopsystem.WorkshopSystem;
import javafx.scene.control.Button;

public class TasksController {
    public Button homeBtn;
    public Button customersBtn;
    public Button tasksBtn;

    public void initialize() {
        WorkshopSystem.onPageChange(this.homeBtn, this.customersBtn, this.tasksBtn);
    }
}
