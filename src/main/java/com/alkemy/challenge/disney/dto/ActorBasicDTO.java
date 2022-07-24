package com.alkemy.challenge.disney.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ActorBasicDTO {

    private String image;
    @NotNull
    private String name;

    public ActorBasicDTO(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public ActorBasicDTO() {

    }

}
