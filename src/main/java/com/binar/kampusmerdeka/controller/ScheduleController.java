package com.binar.kampusmerdeka.controller;

import com.binar.kampusmerdeka.dto.MessageModel;
import com.binar.kampusmerdeka.dto.ScheduleRequest;
import com.binar.kampusmerdeka.dto.ScheduleResponse;
import com.binar.kampusmerdeka.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/schedule", produces = {"application/json"})
public class ScheduleController
{
    @Autowired
    ScheduleService scheduleService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> addSchedule(@RequestBody ScheduleRequest scheduleRequest) {

        MessageModel messageModel = new MessageModel();

        ScheduleResponse scheduleResponse = scheduleService.addSchedule(scheduleRequest);

        if(scheduleResponse.getMessage() != null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage(scheduleResponse.getMessage());
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Register new schedule");
            messageModel.setData(scheduleResponse);
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @GetMapping("/film/name/{filmName}")
    public ResponseEntity<MessageModel> getAllFilmSchedule(@PathVariable String filmName){
        MessageModel messageModel = new MessageModel();
        try {
            List<ScheduleResponse> schedulesGet = scheduleService.showFilmSchedulesByFilmName(filmName);
            messageModel.setMessage("Success get all film schedules by name");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(schedulesGet);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all film schedules by name");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
        }
        return ResponseEntity.ok().body(messageModel);
    }
}
