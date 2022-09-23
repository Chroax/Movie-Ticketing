package com.binar.kampusmerdeka.repository;

import com.binar.kampusmerdeka.model.Genre;
import com.binar.kampusmerdeka.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    
}
