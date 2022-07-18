package com.alkemy.challenge.disney.service;

import com.alkemy.challenge.disney.dto.GenreDTO;

import java.util.List;

public interface GenreService {

    List<GenreDTO> getAllGenres();

    GenreDTO save(GenreDTO dto);

    GenreDTO getById(Long id);
}
