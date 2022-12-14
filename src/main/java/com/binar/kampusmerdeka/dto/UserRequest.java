package com.binar.kampusmerdeka.dto;

import com.binar.kampusmerdeka.model.Roles;
import com.binar.kampusmerdeka.model.Users;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class UserRequest {

    @NotEmpty(message = "Name is required.")
    private String name;

    @NotEmpty(message = "Email is required.")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotEmpty(message = "Password is required.")
    @Pattern(regexp = "(?=[A-Za-z\\d@#$%^&+!=]+$)^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+!=])(?=^.{8,32}$).*$",
            message = "Password must have 1 Uppercase, 1 Lowercase, 1 Number, 1 special character, and min = 8 and max = 32")
    private String password;

    @NotEmpty(message = "Phone number is required.")
    @Pattern(regexp = "^\\d*$",message = "The phone number is invalid.")
    private String phoneNumber;

    @NotEmpty(message = "Roles is required.")
    private List<Integer> rolesId;

    @NotEmpty(message = "Status is required.")
    private Boolean status = true;
}
