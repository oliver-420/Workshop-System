package at.htl.workshopsystem.model;

public class TaskPartMapping {
    private Long _id;
    private Task _task;
    private Part _part;
    private int _quantity;
    private Double _price;

    public TaskPartMapping(Task task, Part part, int quantity, Double price) {
        set_task(task);
        set_part(part);
        set_quantity(quantity);
        set_price(price);
    }

    public void set_id(Long id) {
        _id = id;
    }

    public void set_task(Task task) {
        _task = task;
    }

    public void set_part(Part part) {
        _part = part;
    }

    public void set_quantity(int quantity) {
        _quantity = quantity;
    }

    public void set_price(Double price) {
        _price = price;
    }

    public Long get_id() {
        return _id;
    }

    public Task get_task() {
        return _task;
    }

    public Part get_part() {
        return _part;
    }

    public int get_quantity() {
        return _quantity;
    }

    public Double get_price() {
        return _price;
    }

}
