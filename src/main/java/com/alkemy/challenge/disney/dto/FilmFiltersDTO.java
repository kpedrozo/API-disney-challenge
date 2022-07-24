package com.alkemy.challenge.disney.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class FilmFiltersDTO {
    @NotNull
    private String name;
    private String genre;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "El formato de la fecha debe ser yyyy-MM-dd")
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
