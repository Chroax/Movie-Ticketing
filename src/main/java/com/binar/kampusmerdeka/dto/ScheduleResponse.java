package com.binar.kampusmerdeka.dto;


import com.binar.kampusmerdeka.model.CinemaHall;
import com.binar.kampusmerdeka.model.Films;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleResponse {

    private UUID scheduleId;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private Float price;
    private Films films;
    private CinemaHall cinemaHall;
    private String message;
}
