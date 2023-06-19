package at.htl.workshopsystem.view.controller;

import at.htl.workshopsystem.WorkshopSystem;
import at.htl.workshopsystem.controller.database.*;
import at.htl.workshopsystem.model.Car;
import at.htl.workshopsystem.model.Customer;
import at.htl.workshopsystem.model.Mechanic;
import at.htl.workshopsystem.model.Task;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.awt.*;

public class FinishTaskController {
    public javafx.scene.control.Button homeBtn;
    public javafx.scene.control.Button customersBtn;
    public javafx.scene.control.Button tasksBtn;
    public javafx.scene.control.Button partRepoBtn;
    public TextField mechanicTf;
    public TextField customerTf;
    public TextField carTf;
    public TextField totalPriceTf;
    public TextField durationTf;
    public ListView usedPartsLv;
    private Long taskId = FinishTaskId.getId();
    private final TaskRepository taskRepository = new TaskRepository();
    private final SubTaskRepository subTaskRepository = new SubTaskRepository();
    private final MechanicRepository mechanicRepository = new MechanicRepository();
    private final CarRepository carRepository = new CarRepository();
    private final CustomerRepository customerRepository = new CustomerRepository();

    public void initialize() {
        WorkshopSystem.onPageChange(this.homeBtn, this.customersBtn, this.tasksBtn, this.partRepoBtn);

        Task task = taskRepository.getById(taskId);
        Mechanic mechanic = mechanicRepository.getById(task.getFkMechanic());
        Car car = carRepository.getById(task.getFkCar());
        Customer customer = customerRepository.getById(task.getFkCustomer());

        mechanicTf.setText(mechanic.getName());
        customerTf.setText(customer.getName());
        carTf.setText(car.getManufacturer() + " " + car.getModel());

    }
}
