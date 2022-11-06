package com.binar.kampusmerdeka.service.impl;

import com.binar.kampusmerdeka.dto.ScheduleRequest;
import com.binar.kampusmerdeka.dto.ScheduleResponse;
import com.binar.kampusmerdeka.model.Schedules;
import com.binar.kampusmerdeka.repository.SchedulesRepository;
import com.binar.kampusmerdeka.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SchedulesServiceImpl implements ScheduleService {
    @Autowired
    SchedulesRepository schedulesRepository;

    @Override
    public ScheduleResponse addSchedule(ScheduleRequest scheduleRequest) {
        Schedules schedules = scheduleRequest.toSchedule();

        try {
            schedulesRepository.save(schedules);
            return ScheduleResponse.builder()
                    .scheduleId(schedules.getScheduleId())
                    .startTime(schedules.getStartTime())
                    .endTime(schedules.getEndTime())
                    .price(schedules.getPrice())
                    .date(schedules.getDate())
                    .cinemaHall(schedules.getCinemaHall())
                    .films(schedules.getFilms())
                    .build();
        }
        catch(Exception exception)
        {
            return ScheduleResponse.builder()
                    .message("Schedule already exist")
                    .build();
        }
    }

    @Override
    public List<ScheduleResponse> showFilmSchedulesById(UUID filmId) {
        List<Schedules> allSchedule = schedulesRepository.findAllFilmScheduleById(filmId);
        return toListScheduleResponse(allSchedule);
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
                    .cinemaHall(schedules.getCinemaHall())
                    .films(schedules.getFilms())
                    .build();
            allScheduleResponse.add(scheduleResponse);
        }
        return allScheduleResponse;
    }
}
