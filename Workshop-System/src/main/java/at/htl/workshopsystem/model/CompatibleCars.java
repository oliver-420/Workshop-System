package at.htl.workshopsystem.model;

public class CompatibleCars {
    private Long _id;
    private Car _car;
    private Part _part;

    public CompatibleCars(Car car, Part part) {
        set_car(car);
        set_part(part);
    }

    public void set_id(Long id) {
        _id = id;
    }

    public void set_car(Car car) {
        _car = car;
    }

    public void set_part(Part part) {
        _part = part;
    }

    public Long get_id() {
        return _id;
    }

    public Car get_car() {
        return _car;
    }

    public Part get_part() {
        return _part;
    }
}
