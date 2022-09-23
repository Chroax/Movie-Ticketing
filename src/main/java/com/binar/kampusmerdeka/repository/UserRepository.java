package com.binar.kampusmerdeka.repository;

import com.binar.kampusmerdeka.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query("SELECT u FROM Users u where u.username LIKE %?1%")
    List<Users>findDetailByUsernameALike(String name);
}
