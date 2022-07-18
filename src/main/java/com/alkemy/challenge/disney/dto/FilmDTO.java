package com.alkemy.challenge.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class FilmDTO {

    private Long id;
    private String image;
    private String title;
    private LocalDate creationDate;
    private Integer qualification;
    private boolean deleted;
    private GenreDTO genre;
    private Long genreID;
    private List<ActorDTO> actors;


}
