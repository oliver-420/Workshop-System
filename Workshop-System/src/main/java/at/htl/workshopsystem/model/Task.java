package at.htl.workshopsystem.model;

import java.time.LocalDateTime;

public class Task {
    private Long id;
    private String name;
    private LocalDateTime startDate;

    public Task(String name, LocalDateTime startDate) {
        setName(name);
        setStartDate(startDate);
    }

    public Task(Long id, String name, LocalDateTime startDate) {
        setId(id);
        setName(name);
        setStartDate(startDate);
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
}
