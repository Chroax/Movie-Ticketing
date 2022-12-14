package com.binar.kampusmerdeka.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;


@Data
public class BookingRequest {

    @NotEmpty(message = "User is required.")
    private UUID userId;

    @NotEmpty(message = "Schedule is required.")
    private UUID schedulesId;

    @NotEmpty(message = "Total seat is required.")
    private Integer totalSeat;
}
