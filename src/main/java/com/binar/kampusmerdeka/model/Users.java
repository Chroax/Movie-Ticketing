package com.binar.kampusmerdeka.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int user_id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String phone_number;
}
