package com.binar.kampusmerdeka.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Cinema_Hall")
public class CinemaHall
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int cinema_hall_id;

    @Column(nullable = false, columnDefinition = "char(2)")
    private String cinema_hall_name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cinema_id", nullable = false)//Optional
    private Cinema cinema;

    @OneToOne(mappedBy = "cinemaHall")
    private Schedules schedules;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seatCinemaHall")
    private Set<Seats> seats;
}
