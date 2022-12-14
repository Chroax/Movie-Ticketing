package com.binar.kampusmerdeka.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
public class BookingDetailRequest {

    @NotEmpty(message = "Booking id is required.")
    private UUID bookingId;

    @NotEmpty(message = "Seat id is required.")
    private Integer seatId;

    @NotEmpty(message = "Status seat is required.")
    private Boolean status;
}
