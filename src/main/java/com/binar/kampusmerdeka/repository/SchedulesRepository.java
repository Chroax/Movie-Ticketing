package com.binar.kampusmerdeka.repository;

import com.binar.kampusmerdeka.model.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SchedulesRepository extends JpaRepository<Schedules, UUID>
{
    @Query("SELECT s FROM Schedules s where s.films = (:filmId)")
    List<Schedules>findAllFilmScheduleById(@Param("filmId") UUID filmId);

    @Query("SELECT s FROM Schedules s JOIN Films f where f.filmName = (:filmName)")
    List<Schedules>findAllFilmScheduleByName(@Param("filmName") String filmName);
}