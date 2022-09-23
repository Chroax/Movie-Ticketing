package com.binar.kampusmerdeka.controller;

import com.binar.kampusmerdeka.service.GenreFilmsService;
import com.binar.kampusmerdeka.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class ScheduleController
{
    @Autowired
    ScheduleService scheduleService;

}
