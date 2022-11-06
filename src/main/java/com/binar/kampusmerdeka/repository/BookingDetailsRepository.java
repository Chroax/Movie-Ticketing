package com.binar.kampusmerdeka.repository;

import com.binar.kampusmerdeka.model.Booking;
import com.binar.kampusmerdeka.model.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Integer> {

    @Query("SELECT b FROM BookingDetails b where b.booking = (:bookingId)")
    List<BookingDetails> findAllBookingDetailById(@Param("bookingId") UUID bookingId);
}
