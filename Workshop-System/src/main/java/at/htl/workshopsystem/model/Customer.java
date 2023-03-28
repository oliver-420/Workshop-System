package at.htl.workshopsystem.model;

public class Customer {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private CustomerCard customerCard;

    public Customer(String name, String phoneNumber, String email, CustomerCard customerCard) {
        set_name(name);
        set_phoneNumber(phoneNumber);
        set_email(email);
        set_customerCard(customerCard);
    }

    public void set_id(Long id) {
        this.id = id;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public void set_phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void set_email(String email) {
        this.email = email;
    }

    public void set_customerCard(CustomerCard customerCard) {
        this.customerCard = customerCard;
    }

    public Long get_id() {
        return this.id;
    }

    public String get_name() {
        return this.name;
    }

    public String get_phoneNumber() {
        return this.phoneNumber;
    }

    public String get_email() {
        return this.email;
    }

    public CustomerCard get_customerCard() {
        return this.customerCard;
    }
}
