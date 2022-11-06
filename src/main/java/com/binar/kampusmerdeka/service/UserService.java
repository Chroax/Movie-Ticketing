package com.binar.kampusmerdeka.service;

import com.binar.kampusmerdeka.dto.UserRequest;
import com.binar.kampusmerdeka.dto.UserResponse;
import com.binar.kampusmerdeka.dto.UserUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse registerUser(UserRequest userRequest);
    UserResponse updateUser(UserUpdateRequest userUpdateRequest, UUID userId);
    Boolean deleteUser(UUID userId);
    UserResponse searchUserById(UUID userId);
    List<UserResponse> searchAllUser();
    List<UserResponse> searchUserByName(String name);
    Boolean isUserExist(String email);
    Boolean isPhoneNumberExist(String phoneNumber);
}
