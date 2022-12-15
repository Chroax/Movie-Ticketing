package com.binar.kampusmerdeka.services;

import com.binar.kampusmerdeka.dto.*;
import com.binar.kampusmerdeka.model.*;
import com.binar.kampusmerdeka.repository.CinemaHallRepository;
import com.binar.kampusmerdeka.repository.FilmRepository;
import com.binar.kampusmerdeka.repository.SchedulesRepository;
import com.binar.kampusmerdeka.repository.SeatRepository;
import com.binar.kampusmerdeka.service.impl.SchedulesServiceImpl;
import com.binar.kampusmerdeka.service.impl.SeatServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class ScheduleServiceImplTest {
    @Mock
    CinemaHallRepository cinemaHallRepository;
    @Mock
    FilmRepository filmRepository;
    @Mock
    SchedulesRepository schedulesRepository;
    @InjectMocks
    private SchedulesServiceImpl schedulesService;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    @DisplayName("Register new schedule, Positive")
    void testPositiveRegisSchedule(){
        CinemaHallRequest cinemaHallRequest = new CinemaHallRequest("A1");
        CinemaHall cinemaHall = cinemaHallRequest.toCinemaHall();
        Optional<CinemaHall> optionalCinemaHall = Optional.of(cinemaHall);
        Integer cinemaHallId = 1;

        FilmRequest filmRequest = new FilmRequest("Djumandji", true, "Ini adalah film djumandji", 180, LocalDate.now());
        Films films = filmRequest.toFilms();
        Optional<Films> optionalFilms = Optional.of(films);
        UUID filmId = UUID.randomUUID();

        ScheduleRequest scheduleRequest = new ScheduleRequest(LocalTime.now(), LocalTime.now(), LocalDate.now(), 50000f, filmId, cinemaHallId);
        Schedules schedules = Schedules.builder()
                .startTime(scheduleRequest.getStartTime())
                .endTime(scheduleRequest.getStartTime())
                .date(scheduleRequest.getDate())
                .price(scheduleRequest.getPrice())
                .cinemaHall(cinemaHall)
                .films(films)
                .build();

        Mockito.when(cinemaHallRepository.findById(cinemaHallId)).thenReturn(optionalCinemaHall);
        Mockito.when(filmRepository.findById(filmId)).thenReturn(optionalFilms);

        Mockito.when(schedulesRepository.save(schedules)).thenReturn(schedules);

        var actualValue = schedulesService.addSchedule(scheduleRequest);
        var expectedPrice = 50000f;

        Assertions.assertEquals(expectedPrice, actualValue.getPrice());
    }

    @Test
    @DisplayName("Get all schedule by film name, Positive")
    void testPositiveGetAllRole(){
        CinemaHallRequest cinemaHallRequest = new CinemaHallRequest("A1");
        CinemaHall cinemaHall = cinemaHallRequest.toCinemaHall();
        Integer cinemaHallId = 1;
        FilmRequest filmRequest = new FilmRequest("Djumandji", true, "Ini adalah film djumandji", 180, LocalDate.now());
        Films films = filmRequest.toFilms();
        UUID filmId = UUID.randomUUID();

        ScheduleRequest scheduleRequest = new ScheduleRequest(LocalTime.now(), LocalTime.now(), LocalDate.now(), 50000f, filmId, cinemaHallId);

        List<Schedules> allSchedules = new ArrayList<>();
        allSchedules.add(Schedules.builder()
                .startTime(scheduleRequest.getStartTime())
                .endTime(scheduleRequest.getStartTime())
                .date(scheduleRequest.getDate())
                .price(scheduleRequest.getPrice())
                .cinemaHall(cinemaHall)
                .films(films)
                .build());
        allSchedules.add(Schedules.builder()
                .startTime(scheduleRequest.getStartTime())
                .endTime(scheduleRequest.getStartTime())
                .date(scheduleRequest.getDate())
                .price(scheduleRequest.getPrice())
                .cinemaHall(cinemaHall)
                .films(films)
                .build());
        allSchedules.add(Schedules.builder()
                .startTime(scheduleRequest.getStartTime())
                .endTime(scheduleRequest.getStartTime())
                .date(scheduleRequest.getDate())
                .price(scheduleRequest.getPrice())
                .cinemaHall(cinemaHall)
                .films(films)
                .build());

        Mockito.when(schedulesRepository.findAllFilmScheduleByName(filmRequest.getFilmName())).thenReturn(allSchedules);

        var actualValue = schedulesService.showFilmSchedulesByFilmName(filmRequest.getFilmName());
        var expectedSize = 3;

        Assertions.assertEquals(expectedSize, actualValue.size());
    }
}
