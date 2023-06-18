package at.htl.workshopsystem.model;

import java.time.LocalDateTime;

public class SubTask {
    private Long id;
    private String description;
    private int duration;

    public SubTask(String description, int duration) {
        setDescription(description);
        setDuration(duration);
    }

    public SubTask(Long id, String description, int duration) {
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
