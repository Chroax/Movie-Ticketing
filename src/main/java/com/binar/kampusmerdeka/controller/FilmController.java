package com.binar.kampusmerdeka.controller;

import com.binar.kampusmerdeka.dto.*;
import com.binar.kampusmerdeka.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/film")
public class FilmController
{
    @Autowired
    FilmService filmService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> addFilm(@RequestBody FilmRequest filmRequest) {
        MessageModel messageModel = new MessageModel();

        FilmResponse filmResponse = filmService.registerFilm(filmRequest);

        if(filmResponse.getMessage() != null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage(filmResponse.getMessage());
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Register new film");
            messageModel.setData(filmResponse);
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @GetMapping("/get-all")
    public ResponseEntity<MessageModel> getAllFilms(){

        MessageModel messageModel = new MessageModel();
        try {
            List<FilmResponse> filmsGet = filmService.searchAllFilm();
            messageModel.setMessage("Success get all film");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(filmsGet);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all film");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @GetMapping("/name/{filmName}")
    public ResponseEntity<MessageModel> getFilmByName(@PathVariable String filmName){
        MessageModel messageModel = new MessageModel();
        try {
            List<FilmResponse> filmsGet = filmService.searchFilmByName(filmName);
            messageModel.setMessage("Success get film");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(filmsGet);
        }
        catch (Exception exception)
        {
            messageModel.setMessage("Failed get film");
            messageModel.setStatus(HttpStatus.NO_CONTENT.value());
        }
        return ResponseEntity.ok().body(messageModel);
    }

    @GetMapping("/id/{filmId}")
    public ResponseEntity<MessageModel> getFilmById(@PathVariable UUID filmId){
        MessageModel messageModel = new MessageModel();
        try {
            FilmResponse filmsGet = filmService.searchFilmById(filmId);
            messageModel.setMessage("Success get film");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(filmsGet);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get film");
            messageModel.setStatus(HttpStatus.NO_CONTENT.value());
        }
        return ResponseEntity.ok().body(messageModel);
    }

    @PutMapping("/update/{filmId}")
    public ResponseEntity<MessageModel> updateFilm(@PathVariable UUID filmId, @RequestBody FilmUpdateRequest filmUpdateRequest) {
        MessageModel messageModel = new MessageModel();
        FilmResponse filmResponse = filmService.updateFilm(filmUpdateRequest, filmId);

        if(filmResponse.getMessage() != null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage(filmResponse.getMessage());
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Update film with id : " + filmId);
            messageModel.setData(filmResponse);
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @DeleteMapping("/delete/{filmId}")
    public ResponseEntity<MessageModel> deleteFilm(@PathVariable UUID filmId){
        MessageModel messageModel = new MessageModel();
        Boolean deleteFilm = filmService.deleteFilm(filmId);

        if(deleteFilm)
        {
            messageModel.setMessage("Success delete film by id : " + filmId);
            messageModel.setStatus(HttpStatus.OK.value());
        }
        else
        {
            messageModel.setMessage("Failed delete film by id : " + filmId + ", not found");
            messageModel.setStatus(HttpStatus.NO_CONTENT.value());
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @GetMapping("/showing")
    public ResponseEntity<MessageModel> getAllShowingFilms(){

        MessageModel messageModel = new MessageModel();
        try {
            List<FilmResponse> filmsGet = filmService.searchFilmShowing();
            messageModel.setMessage("Success get all film showing");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(filmsGet);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all film showing");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
        }

        return ResponseEntity.ok().body(messageModel);
    }
}
