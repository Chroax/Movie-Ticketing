package com.binar.kampusmerdeka.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetails
{
    @EmbeddedId
    BookingDetailsKey id;

    @ManyToOne
    @MapsId("booking_id")
    @JoinColumn(name = "booking_id")
    Booking booking;

    @ManyToOne
    @MapsId("seat_id")
    @JoinColumn(name = "seat_id")
    Seats seats;
}
