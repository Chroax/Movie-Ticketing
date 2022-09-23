package com.binar.kampusmerdeka.service.impl;

import com.binar.kampusmerdeka.model.Users;
import com.binar.kampusmerdeka.repository.UserRepository;
import com.binar.kampusmerdeka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    UserRepository userRepository;

    public Users addUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<Users> getUserByUsername(String username) {
        return userRepository.findDetailByUsernameALike(username);
    }

    @Override
    public List<Users> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(Users user, int userId) {
        Users userDB = userRepository.findById(userId).orElseThrow();
        userDB.setName(user.getName());
        userDB.setEmail(user.getEmail());
        userDB.setPassword(user.getPassword());
        userDB.setUsername(user.getUsername());
        userDB.setPhone_number(user.getPhone_number());
        userRepository.save(userDB);
    }

    @Override
    public void deleteUser(int id) {
        try {
            userRepository.deleteById(id);
        }catch(DataAccessException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
