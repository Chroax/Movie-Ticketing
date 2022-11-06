package com.binar.kampusmerdeka.service;

import com.binar.kampusmerdeka.dto.SeatRequest;
import com.binar.kampusmerdeka.dto.SeatResponse;

import java.util.List;

public interface SeatService {

    SeatResponse addSeat(SeatRequest seatRequest);
    List<SeatResponse> showAllSeatByCinemaHall(Integer cinemaHallId);
}
