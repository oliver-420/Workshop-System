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
        set_model(model);
        set_manufacturer(manufacturer);
        set_productionYear(productionYear);
        set_registrationYear(registrationYear);
        set_owner(owner);
        set_fuel(fuel);
        set_numberPlate(numberPlate);
    }
    public void set_id(Long id) {
        this.id = id;
    }

    public void set_model(String model) {
        this.model = model;
    }

    public void set_manufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void set_productionYear(int productionYear) { this.productionYear = productionYear; }

    public void set_registrationYear(int registrationYear) {
        this.registrationYear = registrationYear;
    }

    public void set_owner(String owner) {
        this.owner = owner;
    }

    public void set_fuel(String fuel) {
        this.fuel = fuel;
    }

    public void set_numberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public Long get_id() {
        return this.id;
    }

    public String get_model() {
        return this.model;
    }

    public String get_manufacturer() {
        return this.manufacturer;
    }
    public int get_productionYear() {
        return this.productionYear;
    }

    public int get_registrationYear() {
        return this.registrationYear;
    }

    public String get_owner() {
        return this.owner;
    }

    public String get_fuel() {
        return this.fuel;
    }

    public String get_numberPlate() {
        return this.numberPlate;
    }
}
