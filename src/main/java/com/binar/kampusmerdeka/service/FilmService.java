package com.binar.kampusmerdeka.service;

import com.binar.kampusmerdeka.model.Films;

import java.util.List;

public interface FilmService
{
    public Films addFilm(Films film);
    public List<Films> addFilms(List<Films> films);
    public Films getFilmById(int id);
    public List<Films> getAllFilms();
    public void updateFilm(Films films, int filmId);
    public void deleteFilm(int id);
    public List<Films> showFilm();
}
