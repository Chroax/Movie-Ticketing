package com.binar.kampusmerdeka.controller;

import com.binar.kampusmerdeka.dto.*;
import com.binar.kampusmerdeka.service.BookingService;
import com.binar.kampusmerdeka.service.UserService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserService userService;

    @Autowired
    BookingService bookingService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> registerUser(@RequestBody UserRequest userRequest) {
        MessageModel messageModel = new MessageModel();
        boolean isEmailValid = EmailValidator.getInstance().isValid(userRequest.getEmail());

        if(isEmailValid)
        {
            UserResponse userResponse = userService.registerUser(userRequest);

            if(userResponse.getMessage() != null)
            {
                messageModel.setStatus(HttpStatus.CONFLICT.value());
                messageModel.setMessage(userResponse.getMessage());
            }
            else
            {
                messageModel.setStatus(HttpStatus.OK.value());
                messageModel.setMessage("Register new user");
                messageModel.setData(userResponse);
            }
        }
        else
        {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Email is not valid");
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @GetMapping("/get-all")
    public ResponseEntity<MessageModel> getAllUsers(){
        MessageModel messageModel = new MessageModel();
        try {
            List<UserResponse> usersGet = userService.searchAllUser();
            messageModel.setMessage("Success get all user");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(usersGet);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all user");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
        }
        return ResponseEntity.ok().body(messageModel);
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<MessageModel> getUserById(@PathVariable UUID userId){
        MessageModel messageModel = new MessageModel();
        try {
            UserResponse userGet = userService.searchUserById(userId);
            messageModel.setMessage("Success get user");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(userGet);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get user");
            messageModel.setStatus(HttpStatus.NO_CONTENT.value());
        }
        return ResponseEntity.ok().body(messageModel);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<MessageModel> getUserByName(@PathVariable String name){
        MessageModel messageModel = new MessageModel();
        try {
            List<UserResponse> usersGet = userService.searchUserByName(name);
            messageModel.setMessage("Success get user");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(usersGet);
        }
        catch (Exception exception)
        {
            messageModel.setMessage("Failed get user");
            messageModel.setStatus(HttpStatus.NO_CONTENT.value());
        }
        return ResponseEntity.ok().body(messageModel);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<MessageModel> updateUser(@PathVariable UUID userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        MessageModel messageModel = new MessageModel();

        if(userUpdateRequest.getEmail() != null)
        {
            boolean isEmailValid = EmailValidator.getInstance().isValid(userUpdateRequest.getEmail());

            if(isEmailValid)
            {
                UserResponse userResponse = userService.updateUser(userUpdateRequest, userId);

                if(userResponse.getMessage() != null)
                {
                    messageModel.setStatus(HttpStatus.CONFLICT.value());
                    messageModel.setMessage(userResponse.getMessage());
                }
                else
                {
                    messageModel.setStatus(HttpStatus.OK.value());
                    messageModel.setMessage("Update user with id : " + userId);
                    messageModel.setData(userResponse);
                }
            }
            else
            {
                messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
                messageModel.setMessage("Email is not valid");
            }
        }
        else
        {
            UserResponse userResponse = userService.updateUser(userUpdateRequest, userId);

            if(userResponse.getMessage() != null)
            {
                messageModel.setStatus(HttpStatus.CONFLICT.value());
                messageModel.setMessage(userResponse.getMessage());
            }
            else
            {
                messageModel.setStatus(HttpStatus.OK.value());
                messageModel.setMessage("Update user with id : " + userId);
                messageModel.setData(userResponse);
            }
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<MessageModel> deleteUser(@PathVariable UUID userId){
        MessageModel messageModel = new MessageModel();
        Boolean deleteUser = userService.deleteUser(userId);
        if(deleteUser)
        {
            messageModel.setMessage("Success non-active user by id : " + userId);
            messageModel.setStatus(HttpStatus.OK.value());
        }
        else
        {
            messageModel.setMessage("Failed non-active user by id : " + userId + ", not found");
            messageModel.setStatus(HttpStatus.NO_CONTENT.value());
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @GetMapping("/{userId}/history")
    public ResponseEntity<MessageModel> getAllHistoryOrder(@PathVariable UUID userId){
        MessageModel messageModel = new MessageModel();
        try {
            List<BookingResponse> bookingGet = bookingService.searchAllBookingByUser(userId);
            messageModel.setMessage("Success get booking history");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(bookingGet);
        }
        catch (Exception exception)
        {
            messageModel.setMessage("Success get booking history");
            messageModel.setStatus(HttpStatus.NO_CONTENT.value());
        }
        return ResponseEntity.ok().body(messageModel);
    }
}
