package com.alkemy.challenge.disney.service;

import com.alkemy.challenge.disney.dto.FilmBasicDTO;
import com.alkemy.challenge.disney.dto.FilmDTO;

import java.util.List;

public interface FilmService {
    
    FilmDTO save(FilmDTO film);

    FilmDTO getDetailsById(Long id);

    FilmDTO update(FilmDTO film, Long id);

    void delete(Long id);

    List<FilmBasicDTO> getByFilters(String name, String genre, String creationDate, String order);

    FilmDTO addActor(Long idFilm, Long idActor);

    FilmDTO deleteActor(Long idFilm, Long idActor);
}
