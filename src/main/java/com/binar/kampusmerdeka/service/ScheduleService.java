package com.binar.kampusmerdeka.service;


import com.binar.kampusmerdeka.model.Schedules;

import java.util.List;

public interface ScheduleService
{
    public Schedules addSchedule(Schedules schedule);
    public List<Schedules> addSchedules(List<Schedules> schedules);
    public Schedules getScheduleById(int id);
    public List<Schedules> getAllSchedules();
    public void updateSchedule(Schedules schedules, int schedulesId);
    public void deleteSchedule(int id);
    public void showFilmSchedules(int id);
}
