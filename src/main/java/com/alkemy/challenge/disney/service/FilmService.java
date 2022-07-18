package com.alkemy.challenge.disney.service;

import com.alkemy.challenge.disney.dto.FilmDTO;

import java.util.List;

public interface FilmService {
    
    List<FilmDTO> getAllFilms();

    FilmDTO save(FilmDTO film);

    FilmDTO getDetailsById(Long id);

    FilmDTO update(FilmDTO film, Long id);

    void delete(Long id);

    List<FilmDTO> getByFilters(String name, String genre, String creationDate, String order);

    FilmDTO addActor(Long idFilm, Long idActor);

    FilmDTO deleteActor(Long idFilm, Long idActor);
}
