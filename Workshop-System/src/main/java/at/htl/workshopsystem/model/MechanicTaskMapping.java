package at.htl.workshopsystem.model;

public class MechanicTaskMapping {
    private Long id;
    private Mechanic mechanic;
    private Task task;

    public MechanicTaskMapping(Mechanic mechanic, Task task) {
        set_mechanic(mechanic);
        set_task(task);
    }

    public void set_id(Long id) {
        this.id = id;
    }

    public void set_mechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public void set_task(Task task) {
        this.task = task;
    }

    public Long get_id() {
        return this.id;
    }

    public Mechanic get_mechanic() {
        return this.mechanic;
    }

    public Task get_task() {
        return this.task;
    }
}
