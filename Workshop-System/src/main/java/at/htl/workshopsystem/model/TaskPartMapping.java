package at.htl.workshopsystem.model;

public class TaskPartMapping {
    private Long _id;
    private Task _task;
    private Part _part;
    private int _partCount;
    private Double _price;

    public TaskPartMapping(Task task, Part part, int partCount, Double price) {
        set_task(task);
        set_part(part);
        set_partCount(partCount);
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

    public void set_partCount(int partCount) {
        _partCount = partCount;
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

    public int get_partCount() {
        return _partCount;
    }

    public Double get_price() {
        return _price;
    }
}
