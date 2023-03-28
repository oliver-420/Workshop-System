package at.htl.workshopsystem.model;

public class Car {
    private Long _id;
    private String _model;
    private String _manufacturer;
    private int _productionYear;
    private int _registrationYear;
    private String _owner;
    private String _fuel;
    private String _numberPlate;

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
        _id = id;
    }

    public void set_model(String model) {
        _model = model;
    }

    public void set_manufacturer(String manufacturer) {
        _manufacturer = manufacturer;
    }

    public void set_productionYear(int productionYear) {
        _productionYear = productionYear;
    }

    public void set_registrationYear(int registrationYear) {
        _registrationYear = registrationYear;
    }

    public void set_owner(String owner) {
        _owner = owner;
    }

    public void set_fuel(String fuel) {
        _fuel = fuel;
    }

    public void set_numberPlate(String numberPlate) {
        _numberPlate = numberPlate;
    }

    public Long get_id() {
        return _id;
    }

    public String get_model() {
        return _model;
    }

    public String get_manufacturer() {
        return _manufacturer;
    }
    public int get_productionYear() {
        return _productionYear;
    }

    public int get_registrationYear() {
        return _registrationYear;
    }

    public String get_owner() {
        return _owner;
    }

    public String get_fuel() {
        return _fuel;
    }

    public String get_numberPlate() {
        return _numberPlate;
    }
}
