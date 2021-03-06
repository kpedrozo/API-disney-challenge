package com.alkemy.challenge.disney.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class ActorDTO {
    private Long id;
    private String image;
    @NotNull
    private String name;
    private Long age;
    private Long weight;
    private String story;
    private boolean deleted;
    private List<FilmDTO> films;

}
