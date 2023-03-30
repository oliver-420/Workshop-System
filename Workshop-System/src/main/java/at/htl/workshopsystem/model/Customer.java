package at.htl.workshopsystem.model;

public class Customer {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private CustomerCard customerCard;

    public Customer(String name, String phoneNumber, String email, CustomerCard customerCard) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCustomerCard(customerCard);
    }

    public Customer(Long id, String name, String phoneNumber, String email, CustomerCard customerCard) {
        setId(id);
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCustomerCard(customerCard);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerCard getCustomerCard() {
        return customerCard;
    }

    public void setCustomerCard(CustomerCard customerCard) {
        this.customerCard = customerCard;
    }
}
