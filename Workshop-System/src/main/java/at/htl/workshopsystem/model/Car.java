package at.htl.workshopsystem.model;

public class Car {
    private Long id;
    private String model;
    private String manufacturer;
    private int productionYear;
    private int registrationYear;
    private String owner;
    private String fuel;
    private String numberPlate;

    public Car(String model, String manufacturer, int productionYear, int registrationYear, String owner, String fuel, String numberPlate) {
        setModel(model);
        setManufacturer(manufacturer);
        setProductionYear(productionYear);
        setRegistrationYear(registrationYear);
        setOwner(owner);
        setFuel(fuel);
        setNumberPlate(numberPlate);
    }

    public Car(Long id, String model, String manufacturer, int productionYear, int registrationYear, String owner, String fuel, String numberPlate) {
        setId(id);
        setModel(model);
        setManufacturer(manufacturer);
        setProductionYear(productionYear);
        setRegistrationYear(registrationYear);
        setOwner(owner);
        setFuel(fuel);
        setNumberPlate(numberPlate);
    }

    public Car() {
    }

    public Car(String model, String manufacturer, int productionYear, String fuel) {
        setModel(model);
        setManufacturer(manufacturer);
        setProductionYear(productionYear);
        setFuel(fuel);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public void setRegistrationYear(int registrationYear) {
        this.registrationYear = registrationYear;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    @Override
    public String toString() {
        return getProductionYear() + " " + getManufacturer() + " " + getModel();
    }
}
