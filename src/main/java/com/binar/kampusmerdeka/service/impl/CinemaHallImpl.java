package com.binar.kampusmerdeka.service.impl;

import com.binar.kampusmerdeka.dto.CinemaHallRequest;
import com.binar.kampusmerdeka.dto.CinemaHallResponse;
import com.binar.kampusmerdeka.model.CinemaHall;
import com.binar.kampusmerdeka.repository.CinemaHallRepository;
import com.binar.kampusmerdeka.service.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CinemaHallImpl implements CinemaHallService {

    @Autowired
    CinemaHallRepository cinemaHallRepository;

    @Override
    public CinemaHallResponse registerCinemaHall(CinemaHallRequest cinemaHallRequest) {
        CinemaHall cinemaHall = cinemaHallRequest.toCinemaHall();

        try {
            cinemaHallRepository.save(cinemaHall);
            return CinemaHallResponse.builder()
                    .cinemaHallId(cinemaHall.getCinemaHallId())
                    .cinemaHallName(cinemaHall.getCinemaHallName())
                    .build();
        }
        catch(Exception exception)
        {
            return CinemaHallResponse.builder()
                    .message("Cinema hall already exist")
                    .build();
        }
    }

    @Override
    public List<CinemaHallResponse> searchAllCinemaHall() {
        List<CinemaHall> allCinemaHall = cinemaHallRepository.findAll();
        List<CinemaHallResponse> allCinemaHallResponse = new ArrayList<>();
        for (CinemaHall cinemaHall: allCinemaHall) {
            CinemaHallResponse cinemaHallResponse = CinemaHallResponse.builder()
                    .cinemaHallId(cinemaHall.getCinemaHallId())
                    .cinemaHallName(cinemaHall.getCinemaHallName())
                    .build();
            allCinemaHallResponse.add(cinemaHallResponse);
        }
        return allCinemaHallResponse;
    }
}
