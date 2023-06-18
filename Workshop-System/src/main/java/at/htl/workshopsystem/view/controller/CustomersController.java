package at.htl.workshopsystem.view.controller;

import at.htl.workshopsystem.controller.database.CustomerRepository;
import at.htl.workshopsystem.model.Customer;
import atlantafx.base.theme.Styles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.*;
import at.htl.workshopsystem.WorkshopSystem;
import javafx.scene.input.KeyCode;

public class CustomersController {
    public Button homeBtn;
    public Button customersBtn;
    public Button tasksBtn;
    public ListView<Customer> lvCustomers;
    public TextField searchField;
    public TextField nameField;
    public TextField emailField;
    public TextField numberField;
    public TextField discountField;
    public Button deleteCustomerBtn;
    public Button saveCustomerBtn;
    public Button newCustomerBtn;

    FilteredList<Customer> customers;
    ObservableList<Customer> olCustomers = FXCollections.observableArrayList();


    public void initialize() {
        WorkshopSystem.onPageChange(this.homeBtn, this.customersBtn, this.tasksBtn);
        setButtonStyles();

        this.lvCustomers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.nameField.setText(newValue.getName());
                this.emailField.setText(newValue.getEmail());
                this.numberField.setText(newValue.getPhoneNumber());
                if (newValue.getCustomerCard() != null) {
                    this.discountField.setDisable(false);
                    this.discountField.setEditable(false);
                    this.discountField.setText(String.valueOf(newValue.getCustomerCard().getDiscount()) + "%");
                } else {
                    this.discountField.setDisable(true);
                    this.discountField.setEditable(false);
                    this.discountField.setText("No customer card");
                }
            }
        });

        loadCustomers();

        this.searchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                customers.setPredicate(s -> s.getName().toLowerCase().contains(this.searchField.getText().toLowerCase()));
            }
        });

        this.newCustomerBtn.setOnAction(event -> {
            addCustomer();
        });
        this.deleteCustomerBtn.setOnAction(event -> {
            deleteCustomer();
        });
        this.saveCustomerBtn.setOnAction(event -> {
            saveCustomer();
        });
    }


    private void saveCustomer() {
        if (checkIfFieldsAreFull()) return;
        if (checkIfCustomerIsSelected()) return;
        Customer customer = new Customer(
                this.lvCustomers.getSelectionModel().getSelectedItem().getId(),
                this.nameField.getText(),
                this.numberField.getText(),
                this.emailField.getText(),
                null
        );
        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.update(customer);
        olCustomers.set(olCustomers.indexOf(this.lvCustomers.getSelectionModel().getSelectedItem()), customer);
    }

    private boolean checkIfFieldsAreFull() {
        if(this.nameField.getText().isEmpty() || this.numberField.getText().isEmpty() || this.emailField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty fields");
            alert.setContentText("Please fill out all fields");
            alert.showAndWait();
            return true;
        }
        return false;
    }

    private boolean checkIfCustomerIsSelected() {
        if (this.lvCustomers.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No customer selected");
            alert.setContentText("Please select a customer to delete");
            alert.showAndWait();
            return true;
        }
        return false;
    }

    private void addCustomer() {
        if (checkIfFieldsAreFull()) return;

        Customer customer = new Customer(
                this.nameField.getText(),
                this.numberField.getText(),
                this.emailField.getText(),
                null
        );
        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.insert(customer);
        this.olCustomers.add(customer);
    }

    private void deleteCustomer() {
        if (checkIfCustomerIsSelected()) return;
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = this.lvCustomers.getSelectionModel().getSelectedItem();
        customerRepository.delete(customer.getId());
        this.olCustomers.remove(customer);
        this.lvCustomers.getSelectionModel().clearSelection();
    }

    private void loadCustomers() {
        CustomerRepository customerRepository = new CustomerRepository();
        olCustomers.setAll(customerRepository.getAll());
        customers = new FilteredList<>(olCustomers);
        lvCustomers.setItems(customers);
    }

    private void setButtonStyles() {
        deleteCustomerBtn.getStyleClass().addAll(
                Styles.ROUNDED, Styles.DANGER, Styles.BUTTON_OUTLINED, Styles.MEDIUM
        );
        newCustomerBtn.getStyleClass().addAll(
                Styles.ROUNDED, Styles.ACCENT, Styles.BUTTON_OUTLINED, Styles.MEDIUM
        );
        saveCustomerBtn.getStyleClass().addAll(
                Styles.ROUNDED, Styles.ACCENT, Styles.BUTTON_OUTLINED, Styles.MEDIUM
        );
    }
}
