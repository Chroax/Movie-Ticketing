package com.binar.kampusmerdeka.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Cinema {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int cinema_id;

    @Column(nullable = false, length = 256)
    private String cinema_name;

    @Column(nullable = false, length = 512)
    private String cinema_address;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="city_id", nullable = false)//Optional
    private City city;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cinema")
    private Set<CinemaHall> cinemaHalls;
}
