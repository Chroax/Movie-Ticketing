package com.binar.kampusmerdeka.dto;

import com.binar.kampusmerdeka.model.Booking;
import com.binar.kampusmerdeka.model.Schedules;
import com.binar.kampusmerdeka.model.Users;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class BookingRequest {

    @NotEmpty(message = "User is required.")
    private Users userBooking;

    @NotEmpty(message = "Schedule is required.")
    private Schedules schedulesBook;

    public Booking toBooking() {
        return Booking.builder()
                .userBooking(this.userBooking)
                .schedulesBook(this.schedulesBook)
                .build();
    }
}
