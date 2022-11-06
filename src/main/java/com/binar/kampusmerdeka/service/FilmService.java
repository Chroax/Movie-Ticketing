package com.binar.kampusmerdeka.service;

import com.binar.kampusmerdeka.dto.*;

import java.util.List;
import java.util.UUID;

public interface FilmService{

    FilmResponse registerFilm(FilmRequest filmRequest);
    FilmResponse searchFilmById(UUID filmId);
    List<FilmResponse> searchFilmByName(String filmName);
    List<FilmResponse> searchAllFilm();
    FilmResponse updateFilm(FilmUpdateRequest filmUpdateRequest, UUID filmId);
    Boolean deleteFilm(UUID filmId);
    List<FilmResponse> searchFilmShowing();
}
