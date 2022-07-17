package com.alkemy.challenge.disney.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmBasicDTO {

    private String title;
    private Integer qualification;
    private boolean deleted;
    private Long genderID;
}
