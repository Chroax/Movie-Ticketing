package com.binar.kampusmerdeka.service;

import com.binar.kampusmerdeka.model.GenreFilms;

import java.util.List;

public interface GenreFilmsService
{
    public GenreFilms addGenreFilm(GenreFilms film);
    public List<GenreFilms> addGenreFilms(List<GenreFilms> films);
    public GenreFilms getGenreFilmById(int id);
    public List<GenreFilms> getAllGenreFilm();
    public void updateGenreFilm(GenreFilms films, int filmId);
    public void deleteGenreFilm(int id);
}
