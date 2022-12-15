package com.binar.kampusmerdeka.services;

import com.binar.kampusmerdeka.dto.CinemaHallRequest;
import com.binar.kampusmerdeka.dto.RoleRequest;
import com.binar.kampusmerdeka.model.CinemaHall;
import com.binar.kampusmerdeka.model.Roles;
import com.binar.kampusmerdeka.repository.CinemaHallRepository;
import com.binar.kampusmerdeka.repository.RoleRepository;
import com.binar.kampusmerdeka.service.impl.CinemaHallImpl;
import com.binar.kampusmerdeka.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class CinemaHallServiceImplTest {

    @Mock
    CinemaHallRepository cinemaHallRepository;

    @InjectMocks
    private CinemaHallImpl cinemaHallImpl;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    @DisplayName("Register new cinema hall, Positive")
    void testPositiveRegisCinemaHall(){
        CinemaHallRequest cinemaHallRequest = new CinemaHallRequest("A1");
        CinemaHall cinemaHall = cinemaHallRequest.toCinemaHall();
        Mockito.when(cinemaHallRepository.save(cinemaHall)).thenReturn(cinemaHall);
        var actualValue = cinemaHallImpl.registerCinemaHall(cinemaHallRequest);
        var expectedValue = "A1";

        Assertions.assertEquals(expectedValue, actualValue.getCinemaHallName());
    }

    @Test
    @DisplayName("Get all Cinema hall, Positive")
    void testPositiveGetAllCinemaHall(){
        List<CinemaHall> allCinemaHall = new ArrayList<>();
        allCinemaHall.add(new CinemaHallRequest("A1").toCinemaHall());
        allCinemaHall.add(new CinemaHallRequest("A2").toCinemaHall());
        allCinemaHall.add(new CinemaHallRequest("A3").toCinemaHall());

        Mockito.when(cinemaHallRepository.findAll()).thenReturn(allCinemaHall);

        var actualValue = cinemaHallImpl.searchAllCinemaHall();
        var expectedSize = 3;
        var expectedValue1 = "A1";
        var expectedValue2 = "A2";
        var expectedValue3 = "A3";

        Assertions.assertEquals(expectedSize, actualValue.size());
        Assertions.assertEquals(expectedValue1, actualValue.get(0).getCinemaHallName());
        Assertions.assertEquals(expectedValue2, actualValue.get(1).getCinemaHallName());
        Assertions.assertEquals(expectedValue3, actualValue.get(2).getCinemaHallName());
    }
}
