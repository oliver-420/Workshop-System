package at.htl.workshopsystem.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Invoice {
    private Long id;
    private String mechanic;
    private String customer;
    private String car;
    private String totalDuration;
    private String totalCost;
    private String date;
    private Long FK_TASK_ID;

    public Invoice(String date, String mechanic, String customer, String car, String totalDuration, String totalCost, Long taskId) {
        setDate(date);
        setMechanic(mechanic);
        setCustomer(customer);
        setCar(car);
        setTotalDuration(totalDuration);
        setTotalCost(totalCost);
        setFkTask(taskId);
    }

    public Invoice(Long id, String date, String mechanic, String customer, String car, String totalDuration, String totalCost, Long taskId) {
        setId(id);
        setDate(date);
        setMechanic(mechanic);
        setCustomer(customer);
        setCar(car);
        setTotalDuration(totalDuration);
        setTotalCost(totalCost);
        setFkTask(taskId);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMechanic() {
        return mechanic;
    }

    public void setMechanic(String mechanic) {
        this.mechanic = mechanic;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(String totalDuration) {
        this.totalDuration = totalDuration;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public Long getFkTask() {
        return FK_TASK_ID;
    }

    public void setFkTask(Long taskId) {
        this.FK_TASK_ID = taskId;
    }
}
