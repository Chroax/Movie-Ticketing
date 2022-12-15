package com.binar.kampusmerdeka.dto;

import com.binar.kampusmerdeka.model.Films;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FilmRequest {

    @NotEmpty(message = "Film name is required.")
    private String filmName;

    @NotEmpty(message = "Show status is required.")
    private Boolean showStatus = false;

    @NotEmpty(message = "Description is required.")
    private String description;

    @NotEmpty(message = "Duration is required.")
    private Integer durationMin;

    @NotEmpty(message = "Release date is required.")
    private LocalDate releaseDate;

    public Films toFilms() {
        return Films.builder()
                .filmName(this.filmName)
                .showStatus(this.showStatus)
                .description(this.description)
                .durationMin(this.durationMin)
                .releaseDate(this.releaseDate)
                .build();
    }
}
