package com.binar.kampusmerdeka.service.impl;

import com.binar.kampusmerdeka.dto.SeatRequest;
import com.binar.kampusmerdeka.dto.SeatResponse;
import com.binar.kampusmerdeka.model.Seats;
import com.binar.kampusmerdeka.repository.SeatRepository;
import com.binar.kampusmerdeka.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    SeatRepository seatRepository;

    @Override
    public SeatResponse addSeat(SeatRequest seatRequest) {
        Seats seats = seatRequest.toSeat();

        try {
            seatRepository.save(seats);
            return SeatResponse.builder()
                    .seatId(seats.getSeatId())
                    .seatNumber(seats.getSeatNumber())
                    .seatCinemaHall(seats.getSeatCinemaHall())
                    .build();
        }
        catch(Exception exception)
        {
            return SeatResponse.builder()
                    .message("Role already exist")
                    .build();
        }
    }

    @Override
    public List<SeatResponse> showAllSeatByCinemaHall(Integer cinemaHallId) {
        List<Seats> allSeat = seatRepository.findAllSeatByCinemaHall(cinemaHallId);
        List<SeatResponse> allSeatResponse = new ArrayList<>();
        for (Seats seats : allSeat) {
            SeatResponse seatResponse = SeatResponse.builder()
                    .seatId(seats.getSeatId())
                    .seatNumber(seats.getSeatNumber())
                    .seatCinemaHall(seats.getSeatCinemaHall())
                    .build();
            allSeatResponse.add(seatResponse);
        }
        return allSeatResponse;
    }
}
