package com.binar.kampusmerdeka.services;

import com.binar.kampusmerdeka.dto.FilmRequest;
import com.binar.kampusmerdeka.dto.RoleRequest;
import com.binar.kampusmerdeka.model.Films;
import com.binar.kampusmerdeka.model.Roles;
import com.binar.kampusmerdeka.repository.CinemaHallRepository;
import com.binar.kampusmerdeka.repository.FilmRepository;
import com.binar.kampusmerdeka.service.impl.CinemaHallImpl;
import com.binar.kampusmerdeka.service.impl.FilmServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class FilmServiceImplTest {

    @Mock
    FilmRepository filmRepository;

    @InjectMocks
    private FilmServiceImpl filmService;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    @DisplayName("Register new film, Positive")
    void testPositiveRegisFilm(){
        FilmRequest filmRequest = new FilmRequest("Djumandji", true, "Ini adalah film djumandji", 180, LocalDate.now());
        Films films = filmRequest.toFilms();
        Mockito.when(filmRepository.save(films)).thenReturn(films);
        var actualValue = filmService.registerFilm(filmRequest);
        var expectedValue = "Djumandji";
        var expectedValue2 = true;
        var expectedValue3 = "Ini adalah film djumandji";

        Assertions.assertEquals(expectedValue, actualValue.getFilmName());
        Assertions.assertEquals(expectedValue2, actualValue.getShowStatus());
        Assertions.assertEquals(expectedValue3, actualValue.getDescription());
    }

    @Test
    @DisplayName("Get all film, Positive")
    void testPositiveGetAllRole(){
        List<Films> allFilms = new ArrayList<>();
        allFilms.add(new FilmRequest("Djumandji 1", true, "Ini adalah film djumandji 1", 180, LocalDate.now()).toFilms());
        allFilms.add(new FilmRequest("Djumandji 2", true, "Ini adalah film djumandji 2", 180, LocalDate.now()).toFilms());
        allFilms.add(new FilmRequest("Djumandji 3", true, "Ini adalah film djumandji 3", 180, LocalDate.now()).toFilms());

        Mockito.when(filmRepository.findAll()).thenReturn(allFilms);

        var actualValue = filmService.searchAllFilm();
        var expectedSize = 3;
        var expectedValue1 = "Djumandji 1";
        var expectedValue2 = "Djumandji 2";
        var expectedValue3 = "Djumandji 3";

        Assertions.assertEquals(expectedSize, actualValue.size());
        Assertions.assertEquals(expectedValue1, actualValue.get(0).getFilmName());
        Assertions.assertEquals(expectedValue2, actualValue.get(1).getFilmName());
        Assertions.assertEquals(expectedValue3, actualValue.get(2).getFilmName());
    }

    @Test
    @DisplayName("Delete film, Positive")
    void testPositiveDeleteFilm(){
        FilmRequest filmRequest = new FilmRequest("Djumandji", true, "Ini adalah film djumandji", 180, LocalDate.now());
        Films films = filmRequest.toFilms();
        UUID filmId = UUID.randomUUID();

        Mockito.when(filmRepository.existsById(filmId)).thenReturn(true);
        Mockito.doNothing().when(filmRepository).deleteById(filmId);
        var actualValue = filmService.deleteFilm(filmId);
        var expectedValue = true;

        Assertions.assertEquals(expectedValue, actualValue);
    }
}
