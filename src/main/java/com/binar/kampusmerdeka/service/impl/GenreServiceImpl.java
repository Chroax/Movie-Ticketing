package com.binar.kampusmerdeka.service.impl;

import com.binar.kampusmerdeka.model.Genre;
import com.binar.kampusmerdeka.repository.GenreRepository;
import com.binar.kampusmerdeka.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    GenreRepository genreRepository;

    @Override
    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public List<Genre> addGenres(List<Genre> genres) {
        return genreRepository.saveAll(genres);
    }

    @Override
    public Genre getGenreById(int id) {
        return genreRepository.findById(id).orElse(null);
    }

    @Override
    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }

    @Override
    public void updateGenre(Genre genre, int genreId) {
        Genre genreDB = genreRepository.findById(genreId).orElseThrow();
        genreDB.setGenre_name(genre.getGenre_name());
        genreRepository.save(genreDB);
    }

    @Override
    public void deleteGenre(int id) {
        try {
            genreRepository.deleteById(id);
        }catch(DataAccessException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
