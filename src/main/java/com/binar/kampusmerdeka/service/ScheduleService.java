package com.binar.kampusmerdeka.service;


import com.binar.kampusmerdeka.dto.ScheduleRequest;
import com.binar.kampusmerdeka.dto.ScheduleResponse;

import java.util.List;
import java.util.UUID;

public interface ScheduleService
{
    ScheduleResponse addSchedule(ScheduleRequest scheduleRequest);
    List<ScheduleResponse> showFilmSchedulesByFilmName(String filmName);
}
