package com.binar.kampusmerdeka.service;

import com.binar.kampusmerdeka.model.Users;

import java.util.List;

public interface UserService {
    public Users addUser(Users user);
    public Users getUserById(int id);
    public List<Users> getUserByUsername(String username);
    public List<Users> getAllUser();
    public void updateUser(Users user, int userId);
    public void deleteUser(int id);
}
