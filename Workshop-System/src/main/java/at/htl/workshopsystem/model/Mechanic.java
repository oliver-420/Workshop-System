package at.htl.workshopsystem.model;

public class Mechanic {
    private long id;
    private String name;
    private Double hourlyWage;

    public Mechanic(String name, Double hourlyWage) {
        set_name(name);
        set_hourlyWage(hourlyWage);
    }

    public void set_id(long id) {
        this.id = id;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public void set_hourlyWage(Double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public long get_id() {
        return this.id;
    }

    public String get_name() {
        return this.name;
    }

    public Double get_hourlyWage() {
        return this.hourlyWage;
    }
}
