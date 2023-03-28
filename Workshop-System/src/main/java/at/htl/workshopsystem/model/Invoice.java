package at.htl.workshopsystem.model;

import java.time.LocalDateTime;

public class Invoice {
    private Long id;
    private LocalDateTime date;
    private double price;
    private Task task;
    private Boolean isPaid;

    public Invoice(LocalDateTime date, double price, Task task, Boolean isPaid) {
        set_date(date);
        set_price(price);
        set_task(task);
        set_isPaid(isPaid);
    }

    public void set_id(Long id) {
        this.id = id;
    }

    public void set_date(LocalDateTime date) {
        this.date = date;
    }

    public void set_price(double price) {
        this.price = price;
    }

    public void set_task(Task task) {
        this.task = task;
    }

    public void set_isPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Long get_id() {
        return this.id;
    }

    public LocalDateTime get_date() {
        return this.date;
    }

    public double get_price() {
        return this.price;
    }

    public Task get_task() {
        return this.task;
    }

    public Boolean get_isPaid() {
        return this.isPaid;
    }
}
