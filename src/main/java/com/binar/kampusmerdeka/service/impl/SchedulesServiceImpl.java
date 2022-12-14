package com.binar.kampusmerdeka.service.impl;

import com.binar.kampusmerdeka.dto.ScheduleRequest;
import com.binar.kampusmerdeka.dto.ScheduleResponse;
import com.binar.kampusmerdeka.model.CinemaHall;
import com.binar.kampusmerdeka.model.Films;
import com.binar.kampusmerdeka.model.Schedules;
import com.binar.kampusmerdeka.repository.CinemaHallRepository;
import com.binar.kampusmerdeka.repository.FilmRepository;
import com.binar.kampusmerdeka.repository.SchedulesRepository;
import com.binar.kampusmerdeka.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SchedulesServiceImpl implements ScheduleService {
    @Autowired
    SchedulesRepository schedulesRepository;
    @Autowired
    CinemaHallRepository cinemaHallRepository;
    @Autowired
    FilmRepository filmRepository;

    @Override
    public ScheduleResponse addSchedule(ScheduleRequest scheduleRequest) {
        try {
            Optional<CinemaHall> cinemaHall = cinemaHallRepository.findById(scheduleRequest.getCinemaHallId());
            Optional<Films> films = filmRepository.findById(scheduleRequest.getFilmsId());

            if(cinemaHall.isPresent() && films.isPresent())
            {
                Schedules schedules = Schedules.builder()
                        .startTime(scheduleRequest.getStartTime())
                        .endTime(scheduleRequest.getEndTime())
                        .price(scheduleRequest.getPrice())
                        .date(scheduleRequest.getDate())
                        .cinemaHall(cinemaHall.get())
                        .films(films.get())
                        .build();

                try {
                    schedulesRepository.save(schedules);
                    return ScheduleResponse.builder()
                            .scheduleId(schedules.getScheduleId())
                            .startTime(schedules.getStartTime())
                            .endTime(schedules.getEndTime())
                            .price(schedules.getPrice())
                            .date(schedules.getDate())
                            .cinemaHallId(schedules.getCinemaHall().getCinemaHallId())
                            .filmsId(schedules.getFilms().getFilmId())
                            .build();
                }
                catch(Exception exception)
                {
                    return ScheduleResponse.builder()
                            .message("Schedule already exist")
                            .build();
                }
            }
            else
            {
                return ScheduleResponse.builder()
                        .message("Cinema hall id or film id not exist")
                        .build();
            }
        }catch (Exception ignore){
            return ScheduleResponse.builder()
                    .message("Create schedules failed")
                    .build();
        }
    }

    @Override
    public List<ScheduleResponse> showFilmSchedulesByFilmName(String filmName) {
        List<Schedules> allSchedule = schedulesRepository.findAllFilmScheduleByName(filmName);
        return toListScheduleResponse(allSchedule);
    }

    private List<ScheduleResponse> toListScheduleResponse(List<Schedules> allSchedule) {
        List<ScheduleResponse> allScheduleResponse = new ArrayList<>();
        for (Schedules schedules : allSchedule) {
            ScheduleResponse scheduleResponse = ScheduleResponse.builder()
                    .scheduleId(schedules.getScheduleId())
                    .startTime(schedules.getStartTime())
                    .endTime(schedules.getEndTime())
                    .price(schedules.getPrice())
                    .date(schedules.getDate())
                    .cinemaHallId(schedules.getCinemaHall().getCinemaHallId())
                    .filmsId(schedules.getFilms().getFilmId())
                    .build();
            allScheduleResponse.add(scheduleResponse);
        }
        return allScheduleResponse;
    }
}
