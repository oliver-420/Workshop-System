package at.htl.workshopsystem.model;

public class Part {
    private String _serialNumber;
    private String _name;
    private String _manufacturer;
    private double _price;
    private double _additionalCharge;
    private int _quantity;

    public Part(String serialNumber, String name, String manufacturer, double price, double additionalCharge, int quantity) {
        set_serialNumber(serialNumber);
        set_name(name);
        set_manufacturer(manufacturer);
        set_price(price);
        set_additionalCharge(additionalCharge);
        set_quantity(quantity);
    }

    public void set_serialNumber(String serialNumber) {
        _serialNumber = serialNumber;
    }

    public void set_name(String name) {
        _name = name;
    }

    public void set_manufacturer(String manufacturer) {
        _manufacturer = manufacturer;
    }

    public void set_price(double price) {
        _price = price;
    }

    public void set_additionalCharge(double additionalCharge) {
        _additionalCharge = additionalCharge;
    }

    public void set_quantity(int quantity) {
        _quantity = quantity;
    }

    public String get_serialNumber() {
        return _serialNumber;
    }

    public String get_name() {
        return _name;
    }

    public String get_manufacturer() {
        return _manufacturer;
    }

    public double get_price() {
        return _price;
    }

    public double get_additionalCharge() {
        return _additionalCharge;
    }

    public int get_quantity() {
        return _quantity;
    }
}
