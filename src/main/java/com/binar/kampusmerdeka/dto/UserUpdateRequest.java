package com.binar.kampusmerdeka.dto;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class UserUpdateRequest {

    private String name;

    @Email(message = "Please provide a valid email address")
    private String email;

    @Pattern(regexp = "^\\d*$",message = "The phone number is invalid.")
    private String phoneNumber;

    private List<Integer> rolesId;
}
