package at.htl.workshopsystem.model;

public class Mechanic {
    private long _id;
    private String _name;
    private Double _hourlyWage;

    public Mechanic(String name, Double hourlyWage) {
        set_name(name);
        set_hourlyWage(hourlyWage);
    }

    public void set_id(long id) {
        _id = id;
    }

    public void set_name(String name) {
        _name = name;
    }

    public void set_hourlyWage(Double hourlyWage) {
        _hourlyWage = hourlyWage;
    }

    public long get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public Double get_hourlyWage() {
        return _hourlyWage;
    }
}
