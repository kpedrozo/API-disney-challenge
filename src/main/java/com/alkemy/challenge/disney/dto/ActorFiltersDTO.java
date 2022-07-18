package com.alkemy.challenge.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ActorFiltersDTO {
    private String name;
    private String age;
    private String weight;
    private Set<Long> movies;
    private String order;

    public ActorFiltersDTO(String name, String age, String weight, Set<Long> movies, String order) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.movies = movies;
        this.order = order;
    }

    public boolean isASC() {
        return order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean idDESC() {
        return order.compareToIgnoreCase("DESC") == 0;
    }
}
