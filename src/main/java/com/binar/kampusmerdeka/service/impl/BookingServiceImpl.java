package com.binar.kampusmerdeka.service.impl;

import com.binar.kampusmerdeka.dto.BookingRequest;
import com.binar.kampusmerdeka.dto.BookingResponse;
import com.binar.kampusmerdeka.model.*;
import com.binar.kampusmerdeka.repository.*;
import com.binar.kampusmerdeka.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    SchedulesRepository schedulesRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        try {
            Optional<Schedules> schedules = schedulesRepository.findById(bookingRequest.getSchedulesId());
            Optional<Users> users = userRepository.findById(bookingRequest.getUserId());

            if(schedules.isPresent() && users.isPresent())
            {
                Booking booking = Booking.builder()
                        .userBooking(users.get())
                        .schedulesBook(schedules.get())
                        .totalSeat(bookingRequest.getTotalSeat())
                        .build();

                bookingRepository.saveAndFlush(booking);

                return BookingResponse.builder()
                        .bookingId(booking.getBookingId())
                        .createdAt(booking.getCreatedAt())
                        .userId(booking.getUserBooking().getUserId())
                        .schedulesId(booking.getSchedulesBook().getScheduleId())
                        .totalSeat(booking.getTotalSeat())
                        .build();
            }
            else
            {
                return BookingResponse.builder()
                        .message("Schedule id or user id not exist")
                        .build();
            }
        }catch (Exception ignore){
            return BookingResponse.builder()
                    .message("Create booking failed")
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
                    .userId(booking.getUserBooking().getUserId())
                    .schedulesId(booking.getSchedulesBook().getScheduleId())
                    .totalSeat(booking.getTotalSeat())
                    .build();
            allBookingResponse.add(bookingResponse);
        }
        return allBookingResponse;
    }
}
