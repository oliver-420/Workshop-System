package at.htl.workshopsystem.model;

import java.time.LocalDateTime;

public class Task {
    private Long _id;
    private String _name;
    private String _description;
    private LocalDateTime _startDate;
    private int _duration;

    public Task(String name, String description, LocalDateTime startDate, int duration) {
        set_name(name);
        set_description(description);
        set_startDate(startDate);
        set_duration(duration);
    }

    public void set_id(Long id) {
        _id = id;
    }

    public void set_name(String name) {
        _name = name;
    }

    public void set_description(String description) {
        _description = description;
    }

    public void set_startDate(LocalDateTime startDate) {
        _startDate = startDate;
    }

    public void set_duration(int duration) {
        _duration = duration;
    }

    public Long get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public String get_description() {
        return _description;
    }

    public LocalDateTime get_startDate() {
        return _startDate;
    }

    public int get_duration() {
        return _duration;
    }
}
