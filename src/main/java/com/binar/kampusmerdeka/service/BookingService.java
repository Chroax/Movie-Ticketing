package com.binar.kampusmerdeka.service;

import com.binar.kampusmerdeka.dto.BookingRequest;
import com.binar.kampusmerdeka.dto.BookingResponse;

import java.util.List;
import java.util.UUID;

public interface BookingService {

    BookingResponse createBooking(BookingRequest bookingRequest);

    List<BookingResponse> searchAllBookingByUser(UUID userId);
}
