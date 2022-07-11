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
    private GenderDTO gender;
    private Long genderID;
    private List<ActorDTO> actors;
}
