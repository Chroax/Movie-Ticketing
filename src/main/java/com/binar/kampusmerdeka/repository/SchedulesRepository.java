package com.binar.kampusmerdeka.repository;

import com.binar.kampusmerdeka.model.Films;
import com.binar.kampusmerdeka.model.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchedulesRepository extends JpaRepository<Schedules, Integer>
{
    @Query("SELECT s FROM Schedules s where s.films = ?1")
    List<Schedules>findAllFilmSchedule(int filmId);
}