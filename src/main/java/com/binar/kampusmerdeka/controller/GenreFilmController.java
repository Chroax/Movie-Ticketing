package com.binar.kampusmerdeka.controller;

import com.binar.kampusmerdeka.dto.MessageModel;
import com.binar.kampusmerdeka.model.GenreFilms;
import com.binar.kampusmerdeka.service.GenreFilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre-film")
public class GenreFilmController
{
    @Autowired
    GenreFilmsService genreFilmsService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageModel addGenreFilm(@RequestBody GenreFilms genreFilm) {
        MessageModel messageModel = new MessageModel();
        try {
            GenreFilms genreInsert = genreFilmsService.addGenreFilm(genreFilm);
            messageModel.setMessage("SUCCESS ADD NEW GENRE_FILM");
            messageModel.setStatus(200);
            messageModel.setData(genreInsert);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED ADD NEW GENRE_FILM");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @PostMapping("/adds")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageModel addGenreFilms(@RequestBody List<GenreFilms> genreFilm) {
        MessageModel messageModel = new MessageModel();
        try {
            List<GenreFilms> genresFilmsInsert = genreFilmsService.addGenreFilms(genreFilm);
            messageModel.setMessage("SUCCESS ADD NEW GENRE_FILMS");
            messageModel.setStatus(200);
            messageModel.setData(genresFilmsInsert);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED ADD NEW GENRE_FILMS");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @GetMapping
    public MessageModel getAllGenreFilms(){
        MessageModel messageModel = new MessageModel();
        try {
            List<GenreFilms> genreFilmsGet = genreFilmsService.getAllGenreFilm();
            messageModel.setMessage("SUCCESS GET ALL GENRE_FILM");
            messageModel.setStatus(200);
            messageModel.setData(genreFilmsGet);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED GET ALL GENRE_FILM");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @GetMapping("/id/{genreFilmId}")
    public MessageModel getGenreFilmById(@PathVariable int genreFilmId){
        MessageModel messageModel = new MessageModel();
        try {
            GenreFilms genreFilmGet= genreFilmsService.getGenreFilmById(genreFilmId);
            messageModel.setMessage("SUCCESS GET GENRE_FILM");
            messageModel.setStatus(200);
            messageModel.setData(genreFilmGet);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED GET GENRE_FILM");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @PutMapping("/update/{genreFilmId}")
    public MessageModel updateGenreFilm(@PathVariable int genreFilmId, @RequestBody GenreFilms genreFilm) {
        MessageModel messageModel = new MessageModel();
        try {
            genreFilmsService.updateGenreFilm(genreFilm, genreFilmId);
            messageModel.setMessage("SUCCESS UPDATE GENRE_FILM BY ID : " + genreFilmId);
            messageModel.setStatus(200);
            messageModel.setData(genreFilmsService.getGenreFilmById(genreFilmId));
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED UPDATE GENRE_FILM BY ID : " + genreFilmId);
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @DeleteMapping("/{genreFilmId}")
    public MessageModel deleteGenreFilm(@PathVariable int genreFilmId){
        MessageModel messageModel = new MessageModel();
        try {
            genreFilmsService.deleteGenreFilm(genreFilmId);
            messageModel.setMessage("SUCCESS DELETE GENRE_FILM BY ID : " + genreFilmId);
            messageModel.setStatus(200);
            messageModel.setData(null);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED DELETE GENRE_FILM BY ID : " + genreFilmId);
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }
}
