package com.alkemy.challenge.disney.service;

import com.alkemy.challenge.disney.dto.FilmBasicDTO;
import com.alkemy.challenge.disney.dto.FilmDTO;

import java.util.List;

public interface FilmService {
    
    List<FilmDTO> getAllFilms();

    FilmDTO save(FilmDTO film);

    FilmDTO getDetailsById(Long id);

    FilmDTO update(FilmBasicDTO film, Long id);

    void delete(Long id);
}
