package com.binar.kampusmerdeka.dto;

import com.binar.kampusmerdeka.model.CinemaHall;
import com.binar.kampusmerdeka.model.Films;
import com.binar.kampusmerdeka.model.Schedules;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleRequest {

    @NotEmpty(message = "Start Time is required.")
    private LocalTime startTime;

    @NotEmpty(message = "End Time is required.")
    private LocalTime endTime;

    @NotEmpty(message = "Date is required.")
    private LocalDate date;

    @NotEmpty(message = "Price is required.")
    private Float price;

    @NotEmpty(message = "Film is required.")
    private Films films;

    @NotEmpty(message = "Cinema Hall is required.")
    private CinemaHall cinemaHall;

    public Schedules toSchedule() {
        return Schedules.builder()
                .startTime(this.startTime)
                .endTime(this.endTime)
                .price(this.price)
                .date(this.date)
                .films(this.films)
                .cinemaHall(this.cinemaHall)
                .build();
    }
}
