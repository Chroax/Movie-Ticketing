package com.binar.kampusmerdeka.controller;

import com.binar.kampusmerdeka.dto.MessageModel;
import com.binar.kampusmerdeka.model.Genre;
import com.binar.kampusmerdeka.model.Users;
import com.binar.kampusmerdeka.service.GenreService;
import com.binar.kampusmerdeka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController
{
    @Autowired
    GenreService genreService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageModel addGenre(@RequestBody Genre genre) {
        MessageModel messageModel = new MessageModel();
        try {
            Genre genreInsert = genreService.addGenre(genre);
            messageModel.setMessage("SUCCESS ADD NEW GENRE");
            messageModel.setStatus(200);
            messageModel.setData(genreInsert);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED ADD NEW GENRE");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @PostMapping("/adds")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageModel addGenres(@RequestBody List<Genre> genres) {
        MessageModel messageModel = new MessageModel();
        try {
            List<Genre> genresInsert = genreService.addGenres(genres);
            messageModel.setMessage("SUCCESS ADD NEW GENRE");
            messageModel.setStatus(200);
            messageModel.setData(genresInsert);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED ADD NEW GENRE");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @GetMapping
    public MessageModel getAllGenres(){
        MessageModel messageModel = new MessageModel();
        try {
            List<Genre> genresGet = genreService.getAllGenre();
            messageModel.setMessage("SUCCESS GET ALL GENRE");
            messageModel.setStatus(200);
            messageModel.setData(genresGet);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED GET ALL GENRE");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @GetMapping("/id/{genreId}")
    public MessageModel getGenreById(@PathVariable int genreId){
        MessageModel messageModel = new MessageModel();
        try {
            Genre genreGet= genreService.getGenreById(genreId);
            messageModel.setMessage("SUCCESS GET GENRE");
            messageModel.setStatus(200);
            messageModel.setData(genreGet);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED GET GENRE");
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @PutMapping("/update/{genreId}")
    public MessageModel updateGenre(@PathVariable int genreId, @RequestBody Genre genre) {
        MessageModel messageModel = new MessageModel();
        try {
            genreService.updateGenre(genre, genreId);
            messageModel.setMessage("SUCCESS UPDATE GENRE BY ID : " + genreId);
            messageModel.setStatus(200);
            messageModel.setData(genreService.getGenreById(genreId));
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED UPDATE GENRE BY ID : " + genreId);
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }

    @DeleteMapping("/{genreId}")
    public MessageModel deleteGenre(@PathVariable int genreId){
        MessageModel messageModel = new MessageModel();
        try {
            genreService.deleteGenre(genreId);
            messageModel.setMessage("SUCCESS DELETE GENRE BY ID : " + genreId);
            messageModel.setStatus(200);
            messageModel.setData(null);
        }catch (Exception exception)
        {
            messageModel.setMessage("FAILED DELETE GENRE BY ID : " + genreId);
            messageModel.setStatus(500);
            messageModel.setMessage(exception.getMessage());
        }
        return messageModel;
    }
}
