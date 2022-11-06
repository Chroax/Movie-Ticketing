package com.binar.kampusmerdeka.repository;

import com.binar.kampusmerdeka.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {

    @Query("SELECT u FROM Users u WHERE LOWER(u.name) LIKE LOWER(:name)")
    List<Users> findByName(@Param("name") String name);

    @Query("SELECT u FROM Users u WHERE LOWER(u.email) = LOWER(:email)")
    Users findByEmail(@Param("email") String email);

    @Query("SELECT u FROM Users u WHERE (u.phoneNumber) = (:phoneNumber)")
    Users findPhoneNumber(@Param("phoneNumber") String phoneNumber);
}

