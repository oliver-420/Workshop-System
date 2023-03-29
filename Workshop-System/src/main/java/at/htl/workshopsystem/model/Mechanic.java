package at.htl.workshopsystem.model;

public class Mechanic {
    private long id;
    private String name;
    private Double hourlyWage;

    public Mechanic(String name, Double hourlyWage) {
        setName(name);
        setHourlyWage(hourlyWage);
    }

    public Mechanic(long id, String name, Double hourlyWage) {
        this.id = id;
        this.name = name;
        this.hourlyWage = hourlyWage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(Double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
}
