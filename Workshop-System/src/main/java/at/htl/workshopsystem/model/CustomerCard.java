package at.htl.workshopsystem.model;

import java.time.LocalDateTime;

public class CustomerCard {
    private Long _id;
    private LocalDateTime _joinedAt;
    private double _discount;

    public CustomerCard(LocalDateTime joinedAt, double discount) {
        set_joinedAt(joinedAt);
        set_discount(discount);
    }

    public void set_id(Long id) {
        _id = id;
    }

    public void set_joinedAt(LocalDateTime joinedAt) {
        _joinedAt = joinedAt;
    }

    public void set_discount(double discount) {
        _discount = discount;
    }

    public Long get_id() {
        return _id;
    }

    public LocalDateTime get_joinedAt() {
        return _joinedAt;
    }

    public double get_discount() {
        return _discount;
    }
}
