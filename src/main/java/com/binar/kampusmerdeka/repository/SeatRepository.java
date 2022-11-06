package com.binar.kampusmerdeka.repository;

import com.binar.kampusmerdeka.model.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seats, Integer> {

    @Query("SELECT s FROM Seats s where s.seatCinemaHall = (:cinemaHallId)")
    List<Seats> findAllSeatByCinemaHall(@Param("cinemaHallId") Integer cinemaHallId);
}
