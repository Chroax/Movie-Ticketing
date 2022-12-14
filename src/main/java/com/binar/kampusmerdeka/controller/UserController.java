package com.binar.kampusmerdeka.controller;

import com.binar.kampusmerdeka.dto.*;
import com.binar.kampusmerdeka.model.Roles;
import com.binar.kampusmerdeka.service.BookingService;
import com.binar.kampusmerdeka.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = "/user", produces = {"application/json"})
public class UserController
{
    @Autowired
    UserService userService;

    @Autowired
    BookingService bookingService;

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Create User",
                            description = "Pastikan email valid, dan email hanya bisa didaftarkan 1 kali saja.",
                            value = "{\n"
                                    + "  \"responseCode\": 200,\n"
                                    + "  \"responseMessage\": \"Register new user\",\n"
                                    + "  \"data\": [\n"
                                    + "    {\n"
                                    + "      \"user_id\": 03aad5f0-5dda-11ed-9b6a-0242ac120002,\n"
                                    + "      \"name\": cahyadi,\n"
                                    + "      \"email\": cahyadisn6@gmail.com,\n"
                                    + "      \"phone_number\": 08121432478,\n"
                                    + "      \"roles\": \"1\"\n"
                                    + "    }\n"
                                    + "  ]\n"
                                    + "}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
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

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Data Users",
                            description = "Menampilkan semua data user.",
                            value = "{\n"
                                    + "  \"responseCode\": 200,\n"
                                    + "  \"responseMessage\": \"Update user with id : 90780f08-5dd9-11ed-9b6a-0242ac120002\",\n"
                                    + "  \"data\": [\n"
                                    + "    {\n"
                                    + "      \"user_id\": 03aad5f0-5dda-11ed-9b6a-0242ac120002,\n"
                                    + "      \"name\": cahyadi,\n"
                                    + "      \"email\": cahyadisn6@gmail.com,\n"
                                    + "      \"phone_number\": 08121432478,\n"
                                    + "      \"roles\": \"1\"\n"
                                    + "    },\n"
                                    + "    {\n"
                                    + "      \"user_id\": 09ccd5f0-5xxd-11ed-9b6a-0242ac120002,\n"
                                    + "      \"name\": surya,\n"
                                    + "      \"email\": surya6@gmail.com,\n"
                                    + "      \"phone_number\": 081214324745,\n"
                                    + "      \"roles\": \"1\"\n"
                                    + "    }\n"
                                    + "  ]\n"
                                    + "}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
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

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Data User",
                            description = "Menampilkan data user berdasarkan id.",
                            value = "{\n"
                                    + "  \"responseCode\": 200,\n"
                                    + "  \"responseMessage\": \"Update user with id : 90780f08-5dd9-11ed-9b6a-0242ac120002\",\n"
                                    + "  \"data\": [\n"
                                    + "    {\n"
                                    + "      \"user_id\": 03aad5f0-5dda-11ed-9b6a-0242ac120002,\n"
                                    + "      \"name\": cahyadi,\n"
                                    + "      \"email\": cahyadisn6@gmail.com,\n"
                                    + "      \"phone_number\": 08121432478,\n"
                                    + "      \"roles\": \"1\"\n"
                                    + "    }\n"
                                    + "  ]\n"
                                    + "}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
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

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Data Users",
                            description = "Menampilkan semua data user berdasarkan name.",
                            value = "{\n"
                                    + "  \"responseCode\": 200,\n"
                                    + "  \"responseMessage\": \"Update user with id : 90780f08-5dd9-11ed-9b6a-0242ac120002\",\n"
                                    + "  \"data\": [\n"
                                    + "    {\n"
                                    + "      \"user_id\": 03aad5f0-5dda-11ed-9b6a-0242ac120002,\n"
                                    + "      \"name\": cahyadi,\n"
                                    + "      \"email\": cahyadisn6@gmail.com,\n"
                                    + "      \"phone_number\": 08121432478,\n"
                                    + "      \"roles\": \"1\"\n"
                                    + "    },\n"
                                    + "    {\n"
                                    + "      \"user_id\": 09ccd5f0-5xxd-11ed-9b6a-0242ac120002,\n"
                                    + "      \"name\": cahyadi suryss,\n"
                                    + "      \"email\": surya6@gmail.com,\n"
                                    + "      \"phone_number\": 081214324745,\n"
                                    + "      \"roles\": \"1\"\n"
                                    + "    }\n"
                                    + "  ]\n"
                                    + "}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
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

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Update User",
                            description = "Pastikan id user valid, data yang bisa diubah adalah name, email, phone_number dan roles.",
                            value = "{\n"
                                    + "  \"responseCode\": 200,\n"
                                    + "  \"responseMessage\": \"Update user with id : 90780f08-5dd9-11ed-9b6a-0242ac120002\",\n"
                                    + "  \"data\": [\n"
                                    + "    {\n"
                                    + "      \"user_id\": 03aad5f0-5dda-11ed-9b6a-0242ac120002,\n"
                                    + "      \"name\": cahyadi,\n"
                                    + "      \"email\": cahyadisn6@gmail.com,\n"
                                    + "      \"phone_number\": 08121432478,\n"
                                    + "      \"roles\": \"1\"\n"
                                    + "    }\n"
                                    + "  ]\n"
                                    + "}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
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

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Delete User",
                            description = "Pastikan id user valid.",
                            value = "{\"responseCode\": 200, \"responseMessage\": \"Success non-active user by id : 90780f08-5dd9-11ed-9b6a-0242ac120002\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
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

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "History booking user",
                            description = "Menampilkan semua history booking oleh user.",
                            value = "{\n"
                                    + "  \"responseCode\": 200,\n"
                                    + "  \"responseMessage\": \"Success get booking history\",\n"
                                    + "  \"data\": [\n"
                                    + "    {\n"
                                    + "      \"booking_id\": 90780f08-5dd9-11ed-9b6a-0242ac120002,\n"
                                    + "      \"created_at\": \"2022-10-06 14:32:11\",\n"
                                    + "      \"schedule_id\": \"f2a93c2e-5dd9-11ed-9b6a-0242ac120002\"\n"
                                    + "    },\n"
                                    + "    {\n"
                                    + "      \"booking_id\": 03aad5f0-5dda-11ed-9b6a-0242ac120002,\n"
                                    + "      \"created_at\": \"2022-09-06 16:22:01\",\n"
                                    + "      \"schedule_id\": \"f97ec636-5dd9-11ed-9b6a-0242ac120002\"\n"
                                    + "    }\n"
                                    + "  ]\n"
                                    + "}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
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
            messageModel.setMessage("Failed get booking history");
            messageModel.setStatus(HttpStatus.NO_CONTENT.value());
        }
        return ResponseEntity.ok().body(messageModel);
    }
}
