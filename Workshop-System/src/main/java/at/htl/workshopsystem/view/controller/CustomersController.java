package at.htl.workshopsystem.view.controller;

import javafx.scene.control.Button;
import at.htl.workshopsystem.WorkshopSystem;

public class CustomersController {
    public Button homeBtn;
    public Button customersBtn;
    public Button tasksBtn;

    public void initialize() {
        WorkshopSystem.onPageChange(this.homeBtn, this.customersBtn, this.tasksBtn);
    }
}
