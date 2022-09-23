package com.binar.kampusmerdeka.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class GenreFilms {
    @EmbeddedId
    GenreFilmsKey id;

    @ManyToOne
    @MapsId("film_id")
    @JoinColumn(name = "film_id")
    Films film;

    @ManyToOne
    @MapsId("genre_id")
    @JoinColumn(name = "genre_id")
    Genre genre;
}
