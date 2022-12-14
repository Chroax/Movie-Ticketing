package com.binar.kampusmerdeka.controller;


import com.binar.kampusmerdeka.dto.*;
import com.binar.kampusmerdeka.service.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cinema-hall", produces = {"application/json"})
public class CinemaHallController {

    @Autowired
    CinemaHallService cinemaHallService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> createCinemaHall(@RequestBody CinemaHallRequest cinemaHallRequest)
    {
        MessageModel messageModel = new MessageModel();

        CinemaHallResponse cinemaHallResponse = cinemaHallService.registerCinemaHall(cinemaHallRequest);

        if(cinemaHallResponse.getMessage() != null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage(cinemaHallResponse.getMessage());
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Register new cinema hall");
            messageModel.setData(cinemaHallResponse);
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @GetMapping("/get-all")
    public ResponseEntity<MessageModel> getAllCinemaHall()
    {
        MessageModel messageModel = new MessageModel();
        try {
            List<CinemaHallResponse> cinemaHallGet = cinemaHallService.searchAllCinemaHall();
            messageModel.setMessage("Success get all cinema hall");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(cinemaHallGet);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all cinema hall");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
        }
        return ResponseEntity.ok().body(messageModel);
    }
}
