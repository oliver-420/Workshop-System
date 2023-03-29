package at.htl.workshopsystem.model;

public class CompatibleCars {
    private Long id;
    private Car car;
    private Part part;

    public CompatibleCars(Car car, Part part) {
        setCar(car);
        setPart(part);
    }

    public CompatibleCars(Long id, Car car, Part part) {
        setId(id);
        setCar(car);
        setPart(part);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
}
