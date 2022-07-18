package com.alkemy.challenge.disney.dto;

import com.alkemy.challenge.disney.entity.ActorEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ActorDTO {
    private Long id;
    private String image;
    private String name;
    private Long age;
    private Long weight;
    private String story;
    private boolean deleted;
    private List<FilmDTO> films;


}
