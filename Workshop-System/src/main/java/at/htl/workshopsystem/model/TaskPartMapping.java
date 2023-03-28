package at.htl.workshopsystem.model;

public class TaskPartMapping {
    private Long id;
    private Task task;
    private Part part;
    private int quantity;
    private Double price;

    public TaskPartMapping(Task task, Part part, int quantity, Double price) {
        set_task(task);
        set_part(part);
        set_quantity(quantity);
        set_price(price);
    }

    public void set_id(Long id) {
        this.id = id;
    }

    public void set_task(Task task) {
        this.task = task;
    }

    public void set_part(Part part) {
        this.part = part;
    }

    public void set_quantity(int quantity) {
        this.quantity = quantity;
    }

    public void set_price(Double price) {
        this.price = price;
    }

    public Long get_id() {
        return this.id;
    }

    public Task get_task() {
        return this.task;
    }

    public Part get_part() {
        return this.part;
    }

    public int get_quantity() {
        return this.quantity;
    }

    public Double get_price() {
        return this.price;
    }

}
