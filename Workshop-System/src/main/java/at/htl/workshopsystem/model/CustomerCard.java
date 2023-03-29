package at.htl.workshopsystem.model;

import java.sql.Date;

public class CustomerCard {
    private Long id;
    private Date joinedAt;
    private double discount;

    public CustomerCard(Date joinedAt, double discount) {
        setJoinedAt(joinedAt);
        setDiscount(discount);
    }

    public CustomerCard(Long id, Date joinedAt, double discount) {
        setId(id);
        setJoinedAt(joinedAt);
        setDiscount(discount);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
