package at.htl.workshopsystem.model;

public class Mechanic {
    private Long id;
    private String name;
    private Double hourlyWage;

    public Mechanic(String name, Double hourlyWage) {
        setName(name);
        setHourlyWage(hourlyWage);
    }

    public Mechanic(Long id, String name, Double hourlyWage) {
        this.id = id;
        this.name = name;
        this.hourlyWage = hourlyWage;
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

    public Double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(Double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    @Override
    public String toString() {
        return name;
    }
}
