package com.binar.kampusmerdeka.repository;

import com.binar.kampusmerdeka.model.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SchedulesRepository extends JpaRepository<Schedules, UUID>
{
    @Query(nativeQuery = true, value = "SELECT * FROM schedules s inner join films f on s.film_id = f.film_id where f.film_name = :filmName")
    List<Schedules>findAllFilmScheduleByName(@Param("filmName") String filmName);
}