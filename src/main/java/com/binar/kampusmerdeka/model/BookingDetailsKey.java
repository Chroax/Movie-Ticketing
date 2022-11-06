package com.binar.kampusmerdeka.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable
public class BookingDetailsKey implements Serializable {

    @Column(name = "booking_id")
    private UUID booking_id;

    @Column(name = "seat_id")
    private Integer seat_id;

    @Column(name = "status")
    private Boolean status;
}
