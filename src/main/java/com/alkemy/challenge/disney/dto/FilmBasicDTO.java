package com.alkemy.challenge.disney.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class FilmBasicDTO {
    private String image;
    @NotNull
    private String title;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "El formato de la fecha debe ser yyyy-MM-dd")
    private String creationDate;

    public FilmBasicDTO(String image, String title, String creationDate) {
        this.image = image;
        this.title = title;
        this.creationDate = creationDate;
    }

    public FilmBasicDTO() {
    }
}
