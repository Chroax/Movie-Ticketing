package com.binar.kampusmerdeka.services;

import com.binar.kampusmerdeka.dto.CinemaHallRequest;
import com.binar.kampusmerdeka.dto.SeatRequest;
import com.binar.kampusmerdeka.model.CinemaHall;
import com.binar.kampusmerdeka.model.Seats;
import com.binar.kampusmerdeka.repository.CinemaHallRepository;
import com.binar.kampusmerdeka.repository.SeatRepository;
import com.binar.kampusmerdeka.service.SeatService;
import com.binar.kampusmerdeka.service.impl.CinemaHallImpl;
import com.binar.kampusmerdeka.service.impl.SeatServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class SeatServiceImplTest {
    @Mock
    CinemaHallRepository cinemaHallRepository;
    @Mock
    SeatRepository seatRepository;
    @InjectMocks
    private SeatServiceImpl seatService;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    @DisplayName("Register new seat, Positive")
    void testPositiveRegisSeat(){
        CinemaHallRequest cinemaHallRequest = new CinemaHallRequest("A1");
        CinemaHall cinemaHall = cinemaHallRequest.toCinemaHall();
        Optional<CinemaHall> optionalCinemaHall = Optional.of(cinemaHall);
        Integer cinemaHallId = 1;

        SeatRequest seatRequest = new SeatRequest("A11", 1);
        Seats seats = Seats.builder()
                .seatNumber(seatRequest.getSeatNumber())
                .seatCinemaHall(cinemaHall)
                .build();

        Mockito.when(cinemaHallRepository.findById(cinemaHallId)).thenReturn(optionalCinemaHall);
        Mockito.when(seatRepository.save(seats)).thenReturn(seats);

        var actualValue = seatService.addSeat(seatRequest);
        var expectedValue = "A11";

        Assertions.assertEquals(expectedValue, actualValue.getSeatNumber());
    }

    @Test
    @DisplayName("Get all seat by cinema hall, Positive")
    void testPositiveGetAllSeatByCinemaHall(){
        Integer cinemaHallId = 1;

        CinemaHallRequest cinemaHallRequest = new CinemaHallRequest("A1");
        CinemaHall cinemaHall = cinemaHallRequest.toCinemaHall();
        cinemaHall.setCinemaHallId(cinemaHallId);

        List<Seats> allSeats = new ArrayList<>();
        allSeats.add(Seats.builder()
                .seatNumber("A11")
                .seatCinemaHall(cinemaHall)
                .build());
        allSeats.add(Seats.builder()
                .seatNumber("A21")
                .seatCinemaHall(cinemaHall)
                .build());
        allSeats.add(Seats.builder()
                .seatNumber("A31")
                .seatCinemaHall(cinemaHall)
                .build());

        Mockito.when(seatRepository.findAllSeatByCinemaHall(cinemaHallId)).thenReturn(allSeats);

        var actualValue = seatService.showAllSeatByCinemaHall(cinemaHallId);
        var expectedSize = 3;
        var expectedValue1 = "A11";
        var expectedValue2 = "A21";
        var expectedValue3 = "A31";

        Assertions.assertEquals(expectedSize, actualValue.size());
        Assertions.assertEquals(expectedValue1, actualValue.get(0).getSeatNumber());
        Assertions.assertEquals(expectedValue2, actualValue.get(1).getSeatNumber());
        Assertions.assertEquals(expectedValue3, actualValue.get(2).getSeatNumber());
    }
}
