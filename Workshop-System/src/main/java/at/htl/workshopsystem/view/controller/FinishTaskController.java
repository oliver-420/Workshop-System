package at.htl.workshopsystem.view.controller;

import at.htl.workshopsystem.WorkshopSystem;
import at.htl.workshopsystem.controller.database.*;
import at.htl.workshopsystem.model.*;
import at.htl.workshopsystem.pdf.PDFFactory;
import com.itextpdf.text.DocumentException;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;


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
    public Button backBtn;
    public Button saveAndPrintBtn;
    public ListView usedPartsLv;
    private final Long taskId = FinishTaskId.getId();
    private final TaskRepository taskRepository = new TaskRepository();
    private final SubTaskRepository subTaskRepository = new SubTaskRepository();
    private final MechanicRepository mechanicRepository = new MechanicRepository();
    private final InvoiceRepository invoiceRepository = new InvoiceRepository();
    private final CarRepository carRepository = new CarRepository();
    private final CustomerRepository customerRepository = new CustomerRepository();
    private final TaskPartMappingRepository taskPartMappingRepository = new TaskPartMappingRepository();
    private final PartRepository partRepository = new PartRepository();
    private ObservableList<Part> parts = FXCollections.observableArrayList();


    public void initialize() {
        WorkshopSystem.onPageChange(this.homeBtn, this.customersBtn, this.tasksBtn, this.partRepoBtn);

        Task task = taskRepository.getById(taskId);
        Mechanic mechanic = mechanicRepository.getById(task.getFkCar());
        Car car = carRepository.getById(task.getFkMechanic());
        Customer customer = customerRepository.getById(task.getFkCustomer());
        List<TaskPartMapping> partIds = taskPartMappingRepository.getByTaskId(taskId);
        int totalPrice = 0;

        for (TaskPartMapping partId : partIds) {
            parts.add(partRepository.getById(String.valueOf(partId.getId())));
           // totalPrice += partRepository.getById(String.valueOf(partId.getId())).getPrice();
        }

        mechanicTf.setText(mechanic.getName());
        customerTf.setText(customer.getName());
        carTf.setText(car.getManufacturer() + " " + car.getModel());
        //set duration of task, it is calculatet by adding the duration of the subtasks of the task
        durationTf.setText(String.valueOf(subTaskRepository.getDurationByTaskId(taskId)));
        usedPartsLv.setItems(parts);

        totalPriceTf.setText(String.valueOf(totalPrice));

        backBtn.setOnAction(event -> {
            FinishTaskId.cleanTaskSession();
            WorkshopSystem.changeScene(event,"tasks.fxml", "Tasks");
        });

        saveAndPrintBtn.setOnAction(event -> {
            Invoice invoice = new Invoice(
                    task.getStartDate().toString(),
                    mechanic.getName(),
                    customer.getName(),
                    car.getManufacturer() + " " + car.getModel(),
                    durationTf.getText(),
                    totalPriceTf.getText(),
                    taskId
            );

            invoiceRepository.insert(invoice);

            try {
                PDFFactory.CreateInvoicePDF(invoiceRepository.getByTaskId(taskId));
            } catch (IOException e) {
                System.out.println("IOException");
            } catch (DocumentException e) {
                e.printStackTrace();
            }

            taskRepository.delete(taskId);
            FinishTaskId.cleanTaskSession();
            WorkshopSystem.changeScene(event,"tasks.fxml", "Tasks");
        });
    }
}
