package at.htl.workshopsystem.model;

import java.time.LocalDateTime;

public class Invoice {
    private Long id;
    private LocalDateTime date;
    private double price;

    public Invoice(LocalDateTime date, double price) {
        setDate(date);
        setPrice(price);
    }

    public Invoice(Long id, LocalDateTime date, double price) {
        setId(id);
        setDate(date);
        setPrice(price);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
