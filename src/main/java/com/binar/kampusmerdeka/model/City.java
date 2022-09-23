package com.binar.kampusmerdeka.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class City
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int user_id;

    @Column(nullable = false, unique = true, length = 256)
    private String city_name;
}
