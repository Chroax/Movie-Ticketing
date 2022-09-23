package com.binar.kampusmerdeka.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int user_id;

    @Column(nullable = false, length = 32, unique = true)
    private String username;

    @Column(nullable = false, length = 64, unique = true)
    private String email;

    @Column(nullable = false, length = 32)
    private String password;

    @Column(nullable = false, length = 64)
    private String name;

    @Column(nullable = false, unique = true, columnDefinition = "char(16)")
    private String phone_number;

    @Column(nullable = false)
    @CreationTimestamp
    private Date created_at;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date modified_at;
}
