package com.binar.kampusmerdeka.dto;

import com.binar.kampusmerdeka.model.Schedules;
import com.binar.kampusmerdeka.model.Users;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingResponse {

    private UUID bookingId;
    private LocalDateTime createdAt;
    private UUID userId;
    private UUID schedulesId;
    private Integer totalSeat;
    private String message;
}
