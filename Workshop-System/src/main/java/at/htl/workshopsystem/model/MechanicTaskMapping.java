package at.htl.workshopsystem.model;

public class MechanicTaskMapping {
    private Long _id;
    private Mechanic _mechanic;
    private Task _task;

    public MechanicTaskMapping(Mechanic mechanic, Task task) {
        set_mechanic(mechanic);
        set_task(task);
    }

    public void set_id(Long id) {
        _id = id;
    }

    public void set_mechanic(Mechanic mechanic) {
        _mechanic = mechanic;
    }

    public void set_task(Task task) {
        _task = task;
    }

    public Long get_id() {
        return _id;
    }

    public Mechanic get_mechanic() {
        return _mechanic;
    }

    public Task get_task() {
        return _task;
    }
}
