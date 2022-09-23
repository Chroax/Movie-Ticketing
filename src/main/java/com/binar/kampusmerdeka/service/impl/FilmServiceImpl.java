package com.binar.kampusmerdeka.service.impl;

import com.binar.kampusmerdeka.model.Films;
import com.binar.kampusmerdeka.repository.FilmRepository;
import com.binar.kampusmerdeka.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService
{
    @Autowired
    FilmRepository filmRepository;

    @Override
    public Films addFilm(Films film) {
        return filmRepository.save(film);
    }

    @Override
    public List<Films> addFilms(List<Films> films) {
        return filmRepository.saveAll(films);
    }

    @Override
    public Films getFilmById(int id) {
        return filmRepository.findById(id).orElse(null);
    }

    @Override
    public List<Films> getAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public void updateFilm(Films films, int filmId) {
        Films filmDB = filmRepository.findById(filmId).orElseThrow();
        filmDB.setFilm_name(films.getFilm_name());
        filmDB.setDescription(films.getDescription());
        filmDB.setDuration(films.getDuration());
        filmDB.setRelease_date(films.getRelease_date());
        filmDB.setStatus(films.getStatus());
        filmRepository.save(filmDB);
    }

    @Override
    public void deleteFilm(int id) {
        try {
            filmRepository.deleteById(id);
        }catch(DataAccessException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public List<Films> showFilm() {
        return filmRepository.findAllShowingFilm(true);
    }
}
