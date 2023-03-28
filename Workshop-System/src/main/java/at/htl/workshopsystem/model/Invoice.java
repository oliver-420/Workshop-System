package at.htl.workshopsystem.model;

import java.time.LocalDateTime;

public class Invoice {
    private Long _id;
    private LocalDateTime _date;
    private double _price;
    private Task _task;
    private Boolean _isPaid;
}
