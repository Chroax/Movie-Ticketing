package com.binar.kampusmerdeka.service;

import com.binar.kampusmerdeka.dto.*;

import java.util.List;

public interface CinemaHallService {

    CinemaHallResponse registerCinemaHall(CinemaHallRequest cinemaHallRequest);
    List<CinemaHallResponse> searchAllCinemaHall();
}
