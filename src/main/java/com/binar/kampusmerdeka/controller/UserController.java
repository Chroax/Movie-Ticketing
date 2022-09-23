package com.binar.kampusmerdeka.controller;

import com.binar.kampusmerdeka.dto.MessageModel;
import com.binar.kampusmerdeka.model.Users;
import com.binar.kampusmerdeka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserService userService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageModel addEmployee(@RequestBody Users user) {
        MessageModel messageModel = new MessageModel();
        try {
            Users userInsert = userService.addUser(user);
            messageModel.setMessage("SUCCESS ADD NEW USER");
            messageModel.setStatus(200);
            messageModel.setData(userInsert);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED ADD NEW USER");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @GetMapping
    public MessageModel getAllUsers(){
        MessageModel messageModel = new MessageModel();
        try {
            List<Users> userInsert = userService.getAllUser();
            messageModel.setMessage("SUCCESS GET ALL USER");
            messageModel.setStatus(200);
            messageModel.setData(userInsert);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED GET ALL USER");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @GetMapping("/id/{userId}")
    public MessageModel getUserById(@PathVariable int userId){
        MessageModel messageModel = new MessageModel();
        try {
            Users userInsert = userService.getUserById(userId);
            messageModel.setMessage("SUCCESS GET USER");
            messageModel.setStatus(200);
            messageModel.setData(userInsert);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED GET USER");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @GetMapping("/username/{username}")
    public MessageModel getUserById(@PathVariable String username){
        MessageModel messageModel = new MessageModel();
        try {
            List<Users> userInsert = userService.getUserByUsername(username);
            messageModel.setMessage("SUCCESS GET USER");
            messageModel.setStatus(200);
            messageModel.setData(userInsert);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED GET USER");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @PutMapping("/update/{userId}")
    public MessageModel updateUser(@PathVariable int userId, @RequestBody Users user) {
        MessageModel messageModel = new MessageModel();
        try {
            userService.updateUser(user, userId);
            messageModel.setMessage("SUCCESS UPDATE USER BY ID : " + userId);
            messageModel.setStatus(200);
            messageModel.setData(userService.getUserById(userId));
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED UPDATE USER BY ID : " + userId);
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @DeleteMapping("/{userId}")
    public MessageModel deleteUser(@PathVariable int userId){
        MessageModel messageModel = new MessageModel();
        try {
            userService.deleteUser(userId);
            messageModel.setMessage("SUCCESS DELETE USER BY ID : " + userId);
            messageModel.setStatus(200);
            messageModel.setData(null);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED DELETE USER BY ID : " + userId);
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }
}
