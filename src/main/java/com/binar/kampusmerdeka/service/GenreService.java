package com.binar.kampusmerdeka.service;

import com.binar.kampusmerdeka.model.Genre;

import java.util.List;

public interface GenreService {
    public Genre addGenre(Genre genre);
    public List<Genre> addGenres(List<Genre> genres);
    public Genre getGenreById(int id);
    public List<Genre> getAllGenre();
    public void updateGenre(Genre genre, int genreId);
    public void deleteGenre(int id);
}
