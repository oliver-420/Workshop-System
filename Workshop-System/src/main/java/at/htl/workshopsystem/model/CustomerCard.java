package at.htl.workshopsystem.model;

import java.time.LocalDateTime;

public class CustomerCard {
    private Long id;
    private LocalDateTime joinedAt;
    private double discount;

    public CustomerCard(LocalDateTime joinedAt, double discount) {
        setJoinedAt(joinedAt);
        setDiscount(discount);
    }

    public CustomerCard(Long id, LocalDateTime joinedAt, double discount) {
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

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
