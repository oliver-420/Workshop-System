package at.htl.workshopsystem.model;

public class Part {
    private String serialNumber;
    private String name;
    private String manufacturer;
    private double price;
    private double additionalCharge;
    private int quantity;

    public Part(String serialNumber, String name, String manufacturer, double price, double additionalCharge, int quantity) {
        set_serialNumber(serialNumber);
        set_name(name);
        set_manufacturer(manufacturer);
        set_price(price);
        set_additionalCharge(additionalCharge);
        set_quantity(quantity);
    }

    public void set_serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public void set_manufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void set_price(double price) {
        this.price = price;
    }

    public void set_additionalCharge(double additionalCharge) {
        this.additionalCharge = additionalCharge;
    }

    public void set_quantity(int quantity) {
        this.quantity = quantity;
    }

    public String get_serialNumber() {
        return this.serialNumber;
    }

    public String get_name() {
        return this.name;
    }

    public String get_manufacturer() {
        return this.manufacturer;
    }

    public double get_price() {
        return this.price;
    }

    public double get_additionalCharge() {
        return this.additionalCharge;
    }

    public int get_quantity() {
        return this.quantity;
    }
}
