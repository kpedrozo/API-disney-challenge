package com.alkemy.challenge.disney.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

import java.util.List;

@Getter
@Setter
public class FilmDTO {

    private Long id;
    private String image;
    @NotNull()
    private String title;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "El formato de la fecha debe ser yyyy-MM-dd")
    private String creationDate;
    @Min(1)
    @Max(10)
    private Integer qualification;
    private boolean deleted;
    private GenreDTO genre;
    private Long genreID;
    private List<ActorDTO> actors;


}
