package at.htl.workshopsystem.model;

import java.time.LocalDateTime;

public class Task {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private int duration;

    public Task(String name, String description, LocalDateTime startDate, int duration) {
        set_name(name);
        set_description(description);
        set_startDate(startDate);
        set_duration(duration);
    }

    public void set_id(Long id) {
        this.id = id;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public void set_description(String description) {
        this.description = description;
    }

    public void set_startDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void set_duration(int duration) {
        this.duration = duration;
    }

    public Long get_id() {
        return this.id;
    }

    public String get_name() {
        return this.name;
    }

    public String get_description() {
        return this.description;
    }

    public LocalDateTime get_startDate() {
        return this.startDate;
    }

    public int get_duration() {
        return this.duration;
    }
}
