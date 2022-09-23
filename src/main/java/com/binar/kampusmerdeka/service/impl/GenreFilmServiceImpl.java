package com.binar.kampusmerdeka.service.impl;

import com.binar.kampusmerdeka.model.GenreFilms;
import com.binar.kampusmerdeka.repository.GenreFilmsRepository;
import com.binar.kampusmerdeka.service.GenreFilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreFilmServiceImpl implements GenreFilmsService {
    @Autowired
    GenreFilmsRepository genreFilmsRepository;

    @Override
    public GenreFilms addGenreFilm(GenreFilms film) {
        return genreFilmsRepository.save(film);
    }

    @Override
    public List<GenreFilms> addGenreFilms(List<GenreFilms> films) {
        return genreFilmsRepository.saveAll(films);
    }

    @Override
    public GenreFilms getGenreFilmById(int id) {
        return genreFilmsRepository.findById(id).orElse(null);
    }

    @Override
    public List<GenreFilms> getAllGenreFilm() {
        return genreFilmsRepository.findAll();
    }

    @Override
    public void updateGenreFilm(GenreFilms genreFilm, int genreFilmId) {
        GenreFilms genreFilmDB = genreFilmsRepository.findById(genreFilmId).orElseThrow();
        genreFilmDB.setFilm(genreFilm.getFilm());
        genreFilmDB.setGenre(genreFilm.getGenre());
        genreFilmsRepository.save(genreFilmDB);
    }

    @Override
    public void deleteGenreFilm(int id) {
        try {
            genreFilmsRepository.deleteById(id);
        }catch(DataAccessException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
