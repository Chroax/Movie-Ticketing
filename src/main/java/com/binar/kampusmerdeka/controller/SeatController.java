package com.binar.kampusmerdeka.controller;

import com.binar.kampusmerdeka.dto.*;
import com.binar.kampusmerdeka.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {

    @Autowired
    SeatService seatService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> addSeat(@RequestBody SeatRequest seatRequest) {
        MessageModel messageModel = new MessageModel();

        SeatResponse seatResponse = seatService.addSeat(seatRequest);

        if(seatResponse.getMessage() != null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage(seatResponse.getMessage());
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Register new seat");
            messageModel.setData(seatResponse);
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @GetMapping("/cinema-hall/{cinemaHallId}")
    public ResponseEntity<MessageModel> getAllSeatByCinemaHall(@PathVariable Integer cinemaHallId) {

        MessageModel messageModel = new MessageModel();
        try {
            List<SeatResponse> seatGet = seatService.showAllSeatByCinemaHall(cinemaHallId);
            messageModel.setMessage("Success get all seat by cinema hall");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(seatGet);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all seat schedules by cinema hall");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
        }
        return ResponseEntity.ok().body(messageModel);
    }
}
