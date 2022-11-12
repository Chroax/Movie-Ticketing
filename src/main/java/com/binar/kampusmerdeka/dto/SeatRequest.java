package com.binar.kampusmerdeka.dto;

import com.binar.kampusmerdeka.model.CinemaHall;
import com.binar.kampusmerdeka.model.Seats;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class SeatRequest {

    @NotEmpty(message = "Seat number is required.")
    private String seatNumber;

    private Integer cinemaHallId;
}
