package com.binar.kampusmerdeka.service;

import com.binar.kampusmerdeka.dto.BookingDetailRequest;
import com.binar.kampusmerdeka.dto.BookingDetailResponse;

public interface BookingDetailService {

    BookingDetailResponse createBookingDetail(BookingDetailRequest bookingDetails);
}
