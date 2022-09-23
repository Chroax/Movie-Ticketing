package com.binar.kampusmerdeka.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Films
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer film_id;

    @Column(nullable = false, length = 32, unique = true)
    private String film_name;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false, columnDefinition="TEXT")
    private String description;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false, columnDefinition="DATE")
    private Date release_date;

    @OneToMany(mappedBy = "film")
    Set<GenreFilms> genreFilms;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "films")
    private Set<Schedules> schedules;
}
