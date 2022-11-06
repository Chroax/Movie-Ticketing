package com.binar.kampusmerdeka.service.impl;

import com.binar.kampusmerdeka.model.Schedules;
import com.binar.kampusmerdeka.repository.SchedulesRepository;
import com.binar.kampusmerdeka.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulesServiceImpl implements ScheduleService {
    @Autowired
    SchedulesRepository schedulesRepository;

    @Override
    public Schedules addSchedule(Schedules schedule) {
        return schedulesRepository.save(schedule);
    }

    @Override
    public List<Schedules> addSchedules(List<Schedules> schedules) {
        return schedulesRepository.saveAll(schedules);
    }

    @Override
    public Schedules getScheduleById(int id) {
        return schedulesRepository.findById(id).orElse(null);
    }

    @Override
    public List<Schedules> getAllSchedules() {
        return schedulesRepository.findAll();
    }

    @Override
    public void deleteSchedule(int id) {
        try {
            schedulesRepository.deleteById(id);
        }catch(DataAccessException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public List<Schedules> showFilmSchedules(int id) {
        return schedulesRepository.findAllFilmSchedule(id);
    }
}
