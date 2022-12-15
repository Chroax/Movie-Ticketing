package com.binar.kampusmerdeka.service.impl;

import com.binar.kampusmerdeka.dto.SeatRequest;
import com.binar.kampusmerdeka.dto.SeatResponse;
import com.binar.kampusmerdeka.model.CinemaHall;
import com.binar.kampusmerdeka.model.Seats;
import com.binar.kampusmerdeka.repository.CinemaHallRepository;
import com.binar.kampusmerdeka.repository.SeatRepository;
import com.binar.kampusmerdeka.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    CinemaHallRepository cinemaHallRepository;

    @Override
    public SeatResponse addSeat(SeatRequest seatRequest) {

        try {
            Optional<CinemaHall> cinemaHall = cinemaHallRepository.findById(seatRequest.getCinemaHallId());
            if(cinemaHall.isPresent())
            {
                Seats seats = Seats.builder()
                        .seatNumber(seatRequest.getSeatNumber())
                        .seatCinemaHall(cinemaHall.get())
                        .build();

                seatRepository.save(seats);
                return SeatResponse.builder()
                        .seatId(seats.getSeatId())
                        .seatNumber(seats.getSeatNumber())
                        .seatCinemaHall(seats.getSeatCinemaHall().getCinemaHallId())
                        .build();
            }
            else
            {
                return SeatResponse.builder()
                        .message("Cinema hall id not exist")
                        .build();
            }
        }catch (Exception ignore){
            return SeatResponse.builder()
                    .message("Create role failed")
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
                    .seatCinemaHall(seats.getSeatCinemaHall().getCinemaHallId())
                    .build();
            allSeatResponse.add(seatResponse);
        }
        return allSeatResponse;
    }
}
