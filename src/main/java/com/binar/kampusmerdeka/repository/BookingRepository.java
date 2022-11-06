package com.binar.kampusmerdeka.repository;

import com.binar.kampusmerdeka.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    @Query("SELECT b FROM Booking b where b.userBooking = (:userId)")
    List<Booking> findAllBookingByUser(@Param("userId") UUID userId);

    Booking findBookingDetail(@Param("bookingId") UUID bookingId);
}
