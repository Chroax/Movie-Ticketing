package com.binar.kampusmerdeka.dto;

import com.binar.kampusmerdeka.model.CinemaHall;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class CinemaHallRequest {

    @NotEmpty(message = "Cinema hall name is required.")
    private String cinemaHallName;

    public CinemaHall toCinemaHall() {
        return CinemaHall.builder()
                .cinemaHallName(this.cinemaHallName)
                .build();
    }
}
