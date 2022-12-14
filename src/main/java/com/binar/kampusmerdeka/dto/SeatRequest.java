package com.binar.kampusmerdeka.dto;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class SeatRequest {

    @NotEmpty(message = "Seat number is required.")
    private String seatNumber;

    private Integer cinemaHallId;
}
