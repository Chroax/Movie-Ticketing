package com.binar.kampusmerdeka.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class City
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int city_id;

    @Column(nullable = false, unique = true, length = 256)
    private String city_name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private Set<Cinema> cinemas;
}
