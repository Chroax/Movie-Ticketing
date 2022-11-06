package com.binar.kampusmerdeka.service.impl;

import com.binar.kampusmerdeka.model.BookingDetails;
import com.binar.kampusmerdeka.repository.BookingDetailsRepository;
import com.binar.kampusmerdeka.service.BookingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingDetailServiceImpl implements BookingDetailService {

    @Autowired
    BookingDetailsRepository bookingDetailsRepository;

    @Override
    public BookingDetails createBookingDetail(BookingDetails bookingDetails) {
        return bookingDetailsRepository.save(bookingDetails);
    }
}
