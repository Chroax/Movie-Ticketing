package com.binar.kampusmerdeka.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Genre
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer genre_id;

    @Column(nullable = false, unique = true, length = 256)
    private String genre_name;

    @OneToMany(mappedBy = "genre")
    Set<GenreFilms> genreFilms;
}
