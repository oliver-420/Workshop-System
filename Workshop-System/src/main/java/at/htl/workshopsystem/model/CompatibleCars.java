package at.htl.workshopsystem.model;

public class CompatibleCars {
    private Long id;
    private Car car;
    private Part part;

    public CompatibleCars(Car car, Part part) {
        set_car(car);
        set_part(part);
    }

    public void set_id(Long id) {
        this.id = id;
    }

    public void set_car(Car car) {
        this.car = car;
    }

    public void set_part(Part part) {
        this.part = part;
    }

    public Long get_id() {
        return this.id;
    }

    public Car get_car() {
        return this.car;
    }

    public Part get_part() {
        return this.part;
    }
}
