package at.htl.workshopsystem.model;

import java.time.LocalDateTime;

public class Invoice {
    private Long _id;
    private LocalDateTime _date;
    private double _price;
    private Task _task;
    private Boolean _isPaid;

    public Invoice(LocalDateTime date, double price, Task task, Boolean isPaid) {
        set_date(date);
        set_price(price);
        set_task(task);
        set_isPaid(isPaid);
    }

    public void set_id(Long id) {
        _id = id;
    }

    public void set_date(LocalDateTime date) {
        _date = date;
    }

    public void set_price(double price) {
        _price = price;
    }

    public void set_task(Task task) {
        _task = task;
    }

    public void set_isPaid(Boolean isPaid) {
        _isPaid = isPaid;
    }

    public Long get_id() {
        return _id;
    }

    public LocalDateTime get_date() {
        return _date;
    }

    public double get_price() {
        return _price;
    }

    public Task get_task() {
        return _task;
    }

    public Boolean get_isPaid() {
        return _isPaid;
    }
}
