package at.htl.workshopsystem.model;

import java.time.LocalDateTime;

public class Task {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private double duration;

    public Task(String name, String description, LocalDateTime startDate, double duration) {
        setName(name);
        setDescription(description);
        setStartDate(startDate);
        setDuration(duration);
    }

    public Task(Long id, String name, String description, LocalDateTime startDate, double duration) {
        setId(id);
        setName(name);
        setDescription(description);
        setStartDate(startDate);
        setDuration(duration);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
