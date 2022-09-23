package com.binar.kampusmerdeka.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Seats
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int seat_id;

    @Column(nullable = false, columnDefinition = "char(3)")
    private String seat_number;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cinema_hall_id", nullable = false)
    private CinemaHall seatCinemaHall;
}
