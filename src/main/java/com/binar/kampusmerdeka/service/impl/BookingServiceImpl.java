package com.binar.kampusmerdeka.service.impl;

import com.binar.kampusmerdeka.dto.BookingRequest;
import com.binar.kampusmerdeka.dto.BookingResponse;
import com.binar.kampusmerdeka.model.Booking;
import com.binar.kampusmerdeka.repository.BookingRepository;
import com.binar.kampusmerdeka.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        Booking booking = bookingRequest.toBooking();

        try {
            bookingRepository.save(booking);
            return BookingResponse.builder()
                    .bookingId(booking.getBookingId())
                    .createdAt(booking.getCreatedAt())
                    .userBooking(booking.getUserBooking())
                    .schedulesBook(booking.getSchedulesBook())
                    .build();
        }
        catch(Exception exception)
        {
            return BookingResponse.builder()
                    .message("Booking already exist")
                    .build();
        }
    }

    @Override
    public List<BookingResponse> searchAllBookingByUser(UUID userId) {
        List<Booking> alLBooking = bookingRepository.findAllBookingByUser(userId);
        List<BookingResponse> allBookingResponse = new ArrayList<>();
        for (Booking booking : alLBooking) {
            BookingResponse bookingResponse = BookingResponse.builder()
                    .bookingId(booking.getBookingId())
                    .createdAt(booking.getCreatedAt())
                    .schedulesBook(booking.getSchedulesBook())
                    .build();
            allBookingResponse.add(bookingResponse);
        }
        return allBookingResponse;
    }
}
