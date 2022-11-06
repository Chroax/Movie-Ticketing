package com.binar.kampusmerdeka.dto;


import com.binar.kampusmerdeka.model.Roles;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class UserUpdateRequest {

    private String name;

    @Email(message = "Please provide a valid email address")
    private String email;

    @Pattern(regexp = "^[0-9]*$",message = "The phone number is invalid.")
    private String phoneNumber;

    private Roles userRoles;
}
