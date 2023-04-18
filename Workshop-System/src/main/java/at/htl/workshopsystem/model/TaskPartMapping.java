package at.htl.workshopsystem.model;

public class TaskPartMapping {
    private Long id;
    private Task task;
    private Part part;
    private int quantity;
    private Double price;

    public TaskPartMapping(Task task, Part part, int quantity, Double price) {
        setTask(task);
        setPart(part);
        setQuantity(quantity);
        setPrice(price);
    }

    public TaskPartMapping(Long id, Task task, Part part, int quantity, Double price) {
        setId(id);
        setTask(task);
        setPart(part);
        setQuantity(quantity);
        setPrice(price);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TaskPartMapping{" +
                "id=" + id +
                ", task=" + task +
                ", part=" + part +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
