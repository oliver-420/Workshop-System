package at.htl.workshopsystem.model;

import java.time.LocalDateTime;

public class Invoice {
    private Long id;
    private LocalDateTime date;
    private double price;
    private Task task;
    private Boolean isPaid;
    private Customer customer;

    public Invoice(LocalDateTime date, double price, Task task, Boolean isPaid, Customer customer) {
        setDate(date);
        setPrice(price);
        setTask(task);
        setPaid(isPaid);
        setCustomer(customer);
    }

    public Invoice(Long id, LocalDateTime date, double price, Task task, Boolean isPaid, Customer customer) {
        setId(id);
        setDate(date);
        setPrice(price);
        setTask(task);
        setPaid(isPaid);
        setCustomer(customer);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }
}
