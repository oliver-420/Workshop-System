package at.htl.workshopsystem.model;

import java.time.LocalDateTime;

public class Task {
    private Long id;
    private String name;
    private LocalDateTime startDate;
    private Long fk_mechanic;
    private Long fk_car;
    private Long fk_customer;

    public Task(String name, LocalDateTime startDate, Long fk_mechanic, Long fk_car, Long fk_customer) {
        setName(name);
        setStartDate(startDate);
        setFkMechanic(fk_mechanic);
        setFkCar(fk_car);
        setFkCustomer(fk_customer);
    }

    public Task(Long id, String name, LocalDateTime startDate, Long fk_mechanic, Long fk_car, Long fk_customer) {
        setId(id);
        setName(name);
        setStartDate(startDate);
        setFkMechanic(fk_mechanic);
        setFkCar(fk_car);
        setFkCustomer(fk_customer);
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

    public Long getFkMechanic() {
        return fk_mechanic;
    }

    public void setFkMechanic(Long fk_mechanic) {
        this.fk_mechanic = fk_mechanic;
    }

    public Long getFkCar() {
        return fk_car;
    }

    public void setFkCar(Long fk_car) {
        this.fk_car = fk_car;
    }

    public Long getFkCustomer() {
        return fk_customer;
    }

    public void setFkCustomer(Long fk_customer) {
        this.fk_customer = fk_customer;
    }
}
