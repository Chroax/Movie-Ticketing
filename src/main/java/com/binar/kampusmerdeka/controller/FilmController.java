package com.binar.kampusmerdeka.controller;

import com.binar.kampusmerdeka.dto.*;
import com.binar.kampusmerdeka.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/film", produces = {"application/json"})
public class FilmController
{
    @Autowired
    FilmService filmService;

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Add Film",
                            description = "Menambahkan film baru",
                            value = "{\n"
                                    + "  \"responseCode\": 200,\n"
                                    + "  \"responseMessage\": \"Register new film\",\n"
                                    + "  \"data\": [\n"
                                    + "    {\n"
                                    + "      \"film_id\": 03aad5f0-5dda-11ed-9b6a-0242ac120002,\n"
                                    + "      \"film_name\": Petualangan Cahyadi,\n"
                                    + "      \"show_status\": TRUE,\n"
                                    + "      \"description\": Ini adalah deskripsi filmnya,\n"
                                    + "      \"duration_min\": 180,\n"
                                    + "      \"release_date\": \"2022-12-05\"\n"
                                    + "    }\n"
                                    + "  ]\n"
                                    + "}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
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

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Data Films",
                            description = "Mendapatkan semua data film",
                            value = "{\n"
                                    + "  \"responseCode\": 200,\n"
                                    + "  \"responseMessage\": \"Success get all film\",\n"
                                    + "  \"data\": [\n"
                                    + "    {\n"
                                    + "      \"film_id\": 03aad5f0-5dda-11ed-9b6a-0242ac120002,\n"
                                    + "      \"film_name\": Petualangan Cahyadi,\n"
                                    + "      \"show_status\": TRUE,\n"
                                    + "      \"description\": Ini adalah deskripsi filmnya,\n"
                                    + "      \"duration_min\": 180,\n"
                                    + "      \"release_date\": \"2022-12-05\"\n"
                                    + "    },\n"
                                    + "    {\n"
                                    + "      \"film_id\": 04ssd6c1-8dew-13xd-9b6a-0242ac120002,\n"
                                    + "      \"film_name\": Petualangan Cahyadi II,\n"
                                    + "      \"show_status\": TRUE,\n"
                                    + "      \"description\": Ini adalah deskripsi filmnya,\n"
                                    + "      \"duration_min\": 180,\n"
                                    + "      \"release_date\": \"2025-12-05\"\n"
                                    + "    }\n"
                                    + "  ]\n"
                                    + "}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
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

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Data Films By Name",
                            description = "Mendapatkan film berdasarkan filter name",
                            value = "{\n"
                                    + "  \"responseCode\": 200,\n"
                                    + "  \"responseMessage\": \"Success get all film\",\n"
                                    + "  \"data\": [\n"
                                    + "    {\n"
                                    + "      \"film_id\": 03aad5f0-5dda-11ed-9b6a-0242ac120002,\n"
                                    + "      \"film_name\": Petualangan Cahyadi,\n"
                                    + "      \"show_status\": TRUE,\n"
                                    + "      \"description\": Ini adalah deskripsi filmnya,\n"
                                    + "      \"duration_min\": 180,\n"
                                    + "      \"release_date\": \"2022-12-05\"\n"
                                    + "    },\n"
                                    + "    {\n"
                                    + "      \"film_id\": 04ssd6c1-8dew-13xd-9b6a-0242ac120002,\n"
                                    + "      \"film_name\": Petualangan Cahyadi II,\n"
                                    + "      \"show_status\": TRUE,\n"
                                    + "      \"description\": Ini adalah deskripsi filmnya,\n"
                                    + "      \"duration_min\": 180,\n"
                                    + "      \"release_date\": \"2025-12-05\"\n"
                                    + "    }\n"
                                    + "  ]\n"
                                    + "}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
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

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Data Film By Id",
                            description = "Pastikan id film yang valid",
                            value = "{\n"
                                    + "  \"responseCode\": 200,\n"
                                    + "  \"responseMessage\": \"Success get film\",\n"
                                    + "  \"data\": [\n"
                                    + "    {\n"
                                    + "      \"film_id\": 04ssd6c1-8dew-13xd-9b6a-0242ac120002,\n"
                                    + "      \"film_name\": Petualangan Cahyadi II,\n"
                                    + "      \"show_status\": TRUE,\n"
                                    + "      \"description\": Ini adalah deskripsi filmnya,\n"
                                    + "      \"duration_min\": 180,\n"
                                    + "      \"release_date\": \"2025-12-05\"\n"
                                    + "    }\n"
                                    + "  ]\n"
                                    + "}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
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

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Data Film By Id",
                            description = "Pastikan id film valid, data yang bisa diubah adalah film_name, show_status, description, duration_min dan release_date.",
                            value = "{\n"
                                    + "  \"responseCode\": 200,\n"
                                    + "  \"responseMessage\": \"Update film with id : 04ssd6c1-8dew-13xd-9b6a-0242ac120002\",\n"
                                    + "  \"data\": [\n"
                                    + "    {\n"
                                    + "      \"film_id\": 04ssd6c1-8dew-13xd-9b6a-0242ac120002,\n"
                                    + "      \"film_name\": Petualangan Cahyadi III,\n"
                                    + "      \"show_status\": TRUE,\n"
                                    + "      \"description\": Ini adalah deskripsi filmnya,\n"
                                    + "      \"duration_min\": 180,\n"
                                    + "      \"release_date\": \"2025-12-05\"\n"
                                    + "    }\n"
                                    + "  ]\n"
                                    + "}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
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

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Delete Film",
                            description = "Pastikan id film valid.",
                            value = "{\"responseCode\": 200, \"responseMessage\": \"Success delete film by id : 04ssd6c1-8dew-13xd-9b6a-0242ac120002\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
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


    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                        @ExampleObject(name = "Showing Films",
                            description = "Mendapatkan film yang sedang tayang",
                            value = "{\n"
                                    + "  \"responseCode\": 200,\n"
                                    + "  \"responseMessage\": \"Success get all film showing\",\n"
                                    + "  \"data\": [\n"
                                    + "    {\n"
                                    + "      \"film_id\": 03aad5f0-5dda-11ed-9b6a-0242ac120002,\n"
                                    + "      \"film_name\": Petualangan Cahyadi,\n"
                                    + "      \"show_status\": TRUE,\n"
                                    + "      \"description\": Ini adalah deskripsi filmnya,\n"
                                    + "      \"duration_min\": 180,\n"
                                    + "      \"release_date\": \"2022-12-05\"\n"
                                    + "    },\n"
                                    + "    {\n"
                                    + "      \"film_id\": 04ssd6c1-8dew-13xd-9b6a-0242ac120002,\n"
                                    + "      \"film_name\": Petualangan Cahyadi II,\n"
                                    + "      \"show_status\": TRUE,\n"
                                    + "      \"description\": Ini adalah deskripsi filmnya,\n"
                                    + "      \"duration_min\": 180,\n"
                                    + "      \"release_date\": \"2025-12-05\"\n"
                                    + "    }\n"
                                    + "  ]\n"
                                    + "}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
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
