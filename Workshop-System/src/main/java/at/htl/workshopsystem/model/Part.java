package at.htl.workshopsystem.model;

public class Part {
    private String serialNumber;
    private String name;
    private String manufacturer;
    private double price;
    private double additionalCharge;
    private int quantity;

    public Part(String serialNumber, String name, String manufacturer, double price, double additionalCharge, int quantity) {
        setSerialNumber(serialNumber);
        setName(name);
        setManufacturer(manufacturer);
        setPrice(price);
        setAdditionalCharge(additionalCharge);
        setQuantity(quantity);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAdditionalCharge() {
        return additionalCharge;
    }

    public void setAdditionalCharge(double additionalCharge) {
        this.additionalCharge = additionalCharge;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name;
    }
}


