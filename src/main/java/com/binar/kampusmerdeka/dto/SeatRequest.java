package com.binar.kampusmerdeka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class SeatRequest {

    @NotEmpty(message = "Seat number is required.")
    private String seatNumber;

    private Integer cinemaHallId;
}
