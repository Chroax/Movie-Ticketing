package com.binar.kampusmerdeka.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GenreFilmsKey implements Serializable
{
    @Column(name = "film_id")
    private Integer film_id;

    @Column(name = "genre_id")
    private Integer genre_id;
}
