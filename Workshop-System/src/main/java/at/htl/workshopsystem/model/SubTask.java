package at.htl.workshopsystem.model;

import java.time.LocalDateTime;

public class SubTask {
    private Long id;
    private String description;
    private double duration;
    private boolean isDone;

    public SubTask(String description, int duration) {
        setDescription(description);
        setDuration(duration);
    }

    public SubTask(Long id, String description, double duration) {
        setId(id);
        setDescription(description);
        setDuration(duration);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = name;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
