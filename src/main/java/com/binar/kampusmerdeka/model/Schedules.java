package com.binar.kampusmerdeka.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Schedules {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer schedule_id;

    @Column(nullable = false, precision = 2)
    private Float price;

    @Column(nullable = false, columnDefinition="TIME WITH TIME ZONE")
    private Date start_film;

    @Column(nullable = false, columnDefinition="TIME WITH TIME ZONE")
    private Date end_film;

    @Column(nullable = false, columnDefinition="DATE")
    private Date schedule_date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="film_id", nullable = false)
    private Films films;

    /* Fitur Selanjutnya
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cinema_hall_id", nullable = false)
    private CinemaHall cinemaHall;
    */

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reserveSeatSchedule")
    private Set<ReservationSeat> reservationSeats;
}
