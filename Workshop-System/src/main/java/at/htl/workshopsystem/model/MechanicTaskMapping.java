package at.htl.workshopsystem.model;

public class MechanicTaskMapping {
    private Long id;
    private Mechanic mechanic;
    private Task task;

    public MechanicTaskMapping(Mechanic mechanic, Task task) {
        setMechanic(mechanic);
        setTask(task);
    }

    public MechanicTaskMapping(Long id, Mechanic mechanic, Task task) {
        setId(id);
        setMechanic(mechanic);
        setTask(task);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
