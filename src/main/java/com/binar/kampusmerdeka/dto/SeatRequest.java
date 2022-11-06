package com.binar.kampusmerdeka.dto;

import com.binar.kampusmerdeka.model.CinemaHall;
import com.binar.kampusmerdeka.model.Seats;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class SeatRequest {

    @NotEmpty(message = "Seat number is required.")
    private String seatNumber;

    @NotEmpty(message = "Cinema Hall is required.")
    private CinemaHall seatCinemaHall;

    public Seats toSeat() {
        return Seats.builder()
                .seatNumber(this.seatNumber)
                .seatCinemaHall(this.seatCinemaHall)
                .build();
    }
}
