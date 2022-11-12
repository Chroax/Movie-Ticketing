package com.binar.kampusmerdeka.controller;

import com.binar.kampusmerdeka.dto.*;
import com.binar.kampusmerdeka.service.SeatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/seat", produces = {"application/json"})
public class SeatController {

    @Autowired
    SeatService seatService;

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Create seat",
                            description = "Pastikan cinema hall id valid.",
                            value = "{\n"
                                    + "  \"responseCode\": 200,\n"
                                    + "  \"responseMessage\": \"Register new seat\",\n"
                                    + "  \"data\": [\n"
                                    + "    {\n"
                                    + "      \"seat_id\": 1,\n"
                                    + "      \"seat_number\": A01,\n"
                                    + "      \"cinema_hall_id\": \"1\"\n"
                                    + "    }\n"
                                    + "  ]\n"
                                    + "}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @PostMapping("/add")
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

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Get all cinema hall seat",
                            description = "Pastikan cinema hall id valid.",
                            value = "{\n"
                                    + "  \"responseCode\": 200,\n"
                                    + "  \"responseMessage\": \"Register new seat\",\n"
                                    + "  \"data\": [\n"
                                    + "    {\n"
                                    + "      \"seat_id\": 1,\n"
                                    + "      \"seat_number\": A01,\n"
                                    + "      \"cinema_hall_id\": \"1\"\n"
                                    + "    },\n"
                                    + "    {\n"
                                    + "      \"seat_id\": 2,\n"
                                    + "      \"seat_number\": A02,\n"
                                    + "      \"cinema_hall_id\": \"1\"\n"
                                    + "    }\n"
                                    + "  ]\n"
                                    + "}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
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
            messageModel.setMessage("Failed get all seat by cinema hall");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
        }
        return ResponseEntity.ok().body(messageModel);
    }
}
