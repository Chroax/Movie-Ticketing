package com.binar.kampusmerdeka.service.impl;

import com.binar.kampusmerdeka.config.EncoderConfiguration;
import com.binar.kampusmerdeka.dto.UserRequest;
import com.binar.kampusmerdeka.dto.UserResponse;
import com.binar.kampusmerdeka.dto.UserUpdateRequest;
import com.binar.kampusmerdeka.model.Users;
import com.binar.kampusmerdeka.repository.UserRepository;
import com.binar.kampusmerdeka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse registerUser(UserRequest userRequest) {
        String message = null;

        if(!isUserExist(userRequest.getEmail()))
        {
            if(!isPhoneNumberExist(userRequest.getPhoneNumber()))
            {
                Users users = userRequest.toUsers();
                users.setPassword(EncoderConfiguration.encrypt(users.getPassword()));
                userRepository.saveAndFlush(users);
                return UserResponse.builder()
                        .userId(users.getUserId())
                        .name(users.getName())
                        .email(users.getEmail())
                        .phoneNumber(users.getPhoneNumber())
                        .userRoles(users.getUserRoles())
                        .build();
            }
            else
                message = "Phone number already exist";
        }
        else
            message = "Email already exist";
        return UserResponse.builder()
                .message(message)
                .build();
    }

    @Override
    public UserResponse updateUser(UserUpdateRequest userUpdateRequest, UUID userId) {
        Optional<Users> isUser = userRepository.findById(userId);
        String message = null;
        if (isUser.isPresent()) {
            Users users = isUser.get();
            if (userUpdateRequest.getName() != null)
                users.setName(userUpdateRequest.getName());
            if (userUpdateRequest.getEmail() != null)
            {
                if(!isUserExist(userUpdateRequest.getEmail()))
                    users.setEmail(userUpdateRequest.getEmail());
                else
                    message = "Email already exist";
            }
            if (userUpdateRequest.getPhoneNumber() != null)
            {
                if(!isPhoneNumberExist(userUpdateRequest.getPhoneNumber()))
                    users.setPhoneNumber(userUpdateRequest.getPhoneNumber());
                else
                    message = "Phone number already exist";
            }
            if (userUpdateRequest.getUserRoles() != null)
                users.setUserRoles(userUpdateRequest.getUserRoles());
            userRepository.saveAndFlush(users);
            return UserResponse.builder()
                    .userId(users.getUserId())
                    .name(users.getName())
                    .email(users.getEmail())
                    .phoneNumber(users.getPhoneNumber())
                    .message(message)
                    .userRoles(users.getUserRoles())
                    .build();
        } else {
            throw new RuntimeException("User with id: " + userId + " not found");
        }
    }

    @Override
    public Boolean deleteUser(UUID userId) {
        Optional<Users> checkUser = userRepository.findById(userId);
        if(checkUser.isPresent())
        {
            Users users = checkUser.get();
            users.setStatus(false);
            userRepository.saveAndFlush(users);
            return true;
        }
        else
            return false;
    }

    @Override
    public UserResponse searchUserById(UUID userId) {
        Optional<Users> isUsers = userRepository.findById(userId);
        if (isUsers.isEmpty()) {
            throw new RuntimeException("User with id: " + userId + " not found");
        } else {
            Users users = isUsers.get();
            return UserResponse.builder()
                    .userId(users.getUserId())
                    .name(users.getName())
                    .email(users.getEmail())
                    .phoneNumber(users.getPhoneNumber())
                    .userRoles(users.getUserRoles())
                    .build();
        }
    }

    @Override
    public List<UserResponse> searchAllUser() {
        List<Users> allUser = userRepository.findAll();
        return toListUserResponses(allUser);
    }

    @Override
    public List<UserResponse> searchUserByName(String name) {
        List<Users> allUser = userRepository.findByName(name);
        return toListUserResponses(allUser);
    }

    @Override
    public Boolean isUserExist(String email) {
        Users users = userRepository.findByEmail(email);
        return users != null;
    }

    @Override
    public Boolean isPhoneNumberExist(String phoneNumber) {
        Users users = userRepository.findPhoneNumber(phoneNumber);
        return users != null;
    }

    private List<UserResponse> toListUserResponses(List<Users> allUser) {
        List<UserResponse> allUserResponse = new ArrayList<>();
        for (Users user : allUser) {
            UserResponse userResponse = UserResponse.builder()
                    .userId(user.getUserId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .userRoles(user.getUserRoles())
                    .build();
            allUserResponse.add(userResponse);
        }
        return allUserResponse;
    }
}
