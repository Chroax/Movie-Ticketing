package com.binar.kampusmerdeka.controller;

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
    public Users addEmployee(@RequestBody Users user) {
        return userService.addUser(user);
    }

    @GetMapping
    public List<Users> getAllUsers(){
        return userService.getAllUser();
    }

    @GetMapping("/id/{userId}")
    public Users getUserById(@PathVariable int userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/username/{username}")
    public List<Users> getUserById(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable int userId, @RequestBody Users user) {
        try {
            userService.updateUser(user, userId);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch(NoSuchElementException ex){
            System.out.println(ex.getMessage());
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId){
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch(RuntimeException ex){
            System.out.println(ex.getMessage());
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }
}
