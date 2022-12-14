package com.binar.kampusmerdeka.controller;

import com.binar.kampusmerdeka.dto.*;
import com.binar.kampusmerdeka.service.BookingDetailService;
import com.binar.kampusmerdeka.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/booking", produces = {"application/json"})
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    BookingDetailService bookingDetailService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> createBooking(@RequestBody BookingRequest bookingRequest)
    {
        MessageModel messageModel = new MessageModel();

        BookingResponse bookingResponse = bookingService.createBooking(bookingRequest);

        if(bookingResponse.getMessage() != null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage(bookingResponse.getMessage());
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Create new booking");
            messageModel.setData(bookingRequest);
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @PostMapping("/add/details")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> createBookingDetails(@RequestBody BookingDetailRequest bookingDetails)
    {
        MessageModel messageModel = new MessageModel();

        BookingDetailResponse bookingResponse = bookingDetailService.createBookingDetail(bookingDetails);

        if(bookingResponse.getMessage() != null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage("Failed to create booking detail");
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Create new booking detail");
            messageModel.setData(bookingResponse);
        }

        return ResponseEntity.ok().body(messageModel);
    }
}
