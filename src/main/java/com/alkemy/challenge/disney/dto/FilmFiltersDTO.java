package com.alkemy.challenge.disney.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmFiltersDTO {
    private String name;
    private String genre;
    private String creationDate;
    private String order;

    public FilmFiltersDTO(String name, String genre, String creationDate, String order) {
        this.name = name;
        this.genre = genre;
        this.creationDate = creationDate;
        this.order = order;
    }

    public boolean isASC() {
        return order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean idDESC() {
        return order.compareToIgnoreCase("DESC") == 0;
    }
}
