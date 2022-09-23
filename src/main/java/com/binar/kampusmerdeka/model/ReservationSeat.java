package com.binar.kampusmerdeka.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Reservation_Seat")
public class ReservationSeat
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer reservation_seat_id;

    @Column(nullable = false, precision = 2)
    private Float price;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="seat_id", nullable = false)
    private Seats reserveSeat;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="schedule_id", nullable = false)
    private Schedules reserveSeatSchedule;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="booking_id", nullable = false)
    private Schedules reserveSeatBooking;
}
