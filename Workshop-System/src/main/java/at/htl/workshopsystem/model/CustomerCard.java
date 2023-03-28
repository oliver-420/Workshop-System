package at.htl.workshopsystem.model;

import java.time.LocalDateTime;

public class CustomerCard {
    private Long id;
    private LocalDateTime joinedAt;
    private double discount;

    public CustomerCard(LocalDateTime joinedAt, double discount) {
        set_joinedAt(joinedAt);
        set_discount(discount);
    }

    public void set_id(Long id) {
        this.id = id;
    }

    public void set_joinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }

    public void set_discount(double discount) {
        this.discount = discount;
    }

    public Long get_id() {
        return this.id;
    }

    public LocalDateTime get_joinedAt() {
        return this.joinedAt;
    }

    public double get_discount() {
        return this.discount;
    }
}
