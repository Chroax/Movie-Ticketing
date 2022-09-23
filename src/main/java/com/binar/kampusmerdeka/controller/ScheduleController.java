package com.binar.kampusmerdeka.controller;

import com.binar.kampusmerdeka.dto.MessageModel;
import com.binar.kampusmerdeka.model.Schedules;
import com.binar.kampusmerdeka.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController
{
    @Autowired
    ScheduleService scheduleService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageModel addSchedule(@RequestBody Schedules schedule) {
        MessageModel messageModel = new MessageModel();
        try {
            Schedules scheduleInsert = scheduleService.addSchedule(schedule);
            messageModel.setMessage("SUCCESS ADD NEW SCHEDULE");
            messageModel.setStatus(200);
            messageModel.setData(scheduleInsert);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED ADD NEW SCHEDULE");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @PostMapping("/adds")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageModel addSchedules(@RequestBody List<Schedules> schedules) {
        MessageModel messageModel = new MessageModel();
        try {
            List<Schedules> schedulesInsert = scheduleService.addSchedules(schedules);
            messageModel.setMessage("SUCCESS ADD NEW SCHEDULES");
            messageModel.setStatus(200);
            messageModel.setData(schedulesInsert);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED ADD NEW SCHEDULES");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @GetMapping
    public MessageModel getAllSchedules(){
        MessageModel messageModel = new MessageModel();
        try {
            List<Schedules> schedulesGet = scheduleService.getAllSchedules();
            messageModel.setMessage("SUCCESS GET ALL SCHEDULE");
            messageModel.setStatus(200);
            messageModel.setData(schedulesGet);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED GET ALL SCHEDULE");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @GetMapping("/id/{scheduleId}")
    public MessageModel getScheduleById(@PathVariable int scheduleId){
        MessageModel messageModel = new MessageModel();
        try {
            Schedules scheduleGet= scheduleService.getScheduleById(scheduleId);
            messageModel.setMessage("SUCCESS GET SCHEDULE");
            messageModel.setStatus(200);
            messageModel.setData(scheduleGet);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED GET SCHEDULE");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @DeleteMapping("/{scheduleId}")
    public MessageModel deleteSchedule(@PathVariable int scheduleId){
        MessageModel messageModel = new MessageModel();
        try {
            scheduleService.deleteSchedule(scheduleId);
            messageModel.setMessage("SUCCESS DELETE SCHEDULE BY ID : " + scheduleId);
            messageModel.setStatus(200);
            messageModel.setData(null);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED DELETE SCHEDULE BY ID : " + scheduleId);
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @GetMapping("/film/{filmId}")
    public MessageModel getAllFilmSchedule(@PathVariable int filmId){
        MessageModel messageModel = new MessageModel();
        try {
            List<Schedules> schedulesGet = scheduleService.showFilmSchedules(filmId);
            messageModel.setMessage("SUCCESS GET SELECTED FILM SCHEDULES");
            messageModel.setStatus(200);
            messageModel.setData(schedulesGet);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED GET SELECTED FILM SCHEDULES");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }
}
