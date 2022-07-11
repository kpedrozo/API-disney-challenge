package com.alkemy.challenge.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ActorDTO {
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Integer weight;
    private String story;
    private List<FilmDTO> films;



}
