package com.binar.kampusmerdeka.controller;

import com.binar.kampusmerdeka.dto.MessageModel;
import com.binar.kampusmerdeka.model.Films;
import com.binar.kampusmerdeka.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController
{
    @Autowired
    FilmService filmService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageModel addFilm(@RequestBody Films film) {
        MessageModel messageModel = new MessageModel();
        try {
            Films filmInsert = filmService.addFilm(film);
            messageModel.setMessage("SUCCESS ADD NEW FILMS");
            messageModel.setStatus(200);
            messageModel.setData(filmInsert);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED ADD NEW FILMS");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @PostMapping("/adds")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageModel addFilms(@RequestBody List<Films> films) {
        MessageModel messageModel = new MessageModel();
        try {
            List<Films> filmsInsert = filmService.addFilms(films);
            messageModel.setMessage("SUCCESS ADD NEW FILMS");
            messageModel.setStatus(200);
            messageModel.setData(filmsInsert);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED ADD NEW FILMS");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @GetMapping
    public MessageModel getAllFilms(){
        MessageModel messageModel = new MessageModel();
        try {
            List<Films> filmsGet = filmService.getAllFilms();
            messageModel.setMessage("SUCCESS GET ALL FILMS");
            messageModel.setStatus(200);
            messageModel.setData(filmsGet);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED GET ALL FILMS");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @GetMapping("/id/{filmId}")
    public MessageModel getFilmById(@PathVariable int filmId){
        MessageModel messageModel = new MessageModel();
        try {
            Films filmGet= filmService.getFilmById(filmId);
            messageModel.setMessage("SUCCESS GET FILM");
            messageModel.setStatus(200);
            messageModel.setData(filmGet);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED GET FILM");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @PutMapping("/update/{filmId}")
    public MessageModel updateFilm(@PathVariable int filmId, @RequestBody Films film) {
        MessageModel messageModel = new MessageModel();
        try {
            filmService.updateFilm(film, filmId);
            messageModel.setMessage("SUCCESS UPDATE FILM BY ID : " + filmId);
            messageModel.setStatus(200);
            messageModel.setData(filmService.getFilmById(filmId));
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED UPDATE GENRE BY ID : " + filmId);
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @DeleteMapping("/{filmId}")
    public MessageModel deleteFilm(@PathVariable int filmId){
        MessageModel messageModel = new MessageModel();
        try {
            filmService.deleteFilm(filmId);
            messageModel.setMessage("SUCCESS DELETE FILM BY ID : " + filmId);
            messageModel.setStatus(200);
            messageModel.setData(null);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED DELETE FILM BY ID : " + filmId);
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @GetMapping("/showing")
    public MessageModel getAllShowingFilms(){
        MessageModel messageModel = new MessageModel();
        try {
            List<Films> filmsGet = filmService.showFilm();
            messageModel.setMessage("SUCCESS GET ALL SHOWING FILMS");
            messageModel.setStatus(200);
            messageModel.setData(filmsGet);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED GET ALL SHOWING FILMS");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }
}
