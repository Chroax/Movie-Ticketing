package com.binar.kampusmerdeka.service;


import com.binar.kampusmerdeka.dto.ScheduleRequest;
import com.binar.kampusmerdeka.dto.ScheduleResponse;

import java.util.List;

public interface ScheduleService
{
    ScheduleResponse addSchedule(ScheduleRequest scheduleRequest);
    List<ScheduleResponse> showFilmSchedulesByFilmName(String filmName);
}
