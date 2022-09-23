package com.binar.kampusmerdeka.repository;

import com.binar.kampusmerdeka.model.Films;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmRepository extends JpaRepository<Films, Integer>
{
    @Query("SELECT f FROM Films f where f.status = ?1")
    List<Films> findAllShowingFilm(Boolean isShowing);
}
