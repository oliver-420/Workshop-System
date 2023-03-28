package at.htl.workshopsystem.model;

public class Customer {
    private Long _id;
    private String _name;
    private String _phoneNumber;
    private String _email;
    private CustomerCard _customerCard;

    public Customer(String name, String phoneNumber, String email, CustomerCard customerCard) {
        set_name(name);
        set_phoneNumber(phoneNumber);
        set_email(email);
        set_customerCard(customerCard);
    }

    public void set_id(Long id) {
        _id = id;
    }

    public void set_name(String name) {
        _name = name;
    }

    public void set_phoneNumber(String phoneNumber) {
        _phoneNumber = phoneNumber;
    }

    public void set_email(String email) {
        _email = email;
    }

    public void set_customerCard(CustomerCard customerCard) {
        _customerCard = customerCard;
    }

    public Long get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public String get_phoneNumber() {
        return _phoneNumber;
    }

    public String get_email() {
        return _email;
    }

    public CustomerCard get_customerCard() {
        return _customerCard;
    }
}
