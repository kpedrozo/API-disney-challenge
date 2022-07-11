package com.alkemy.challenge.disney.service.impl;

import com.alkemy.challenge.disney.dto.FilmDTO;
import com.alkemy.challenge.disney.entity.FilmEntity;
import com.alkemy.challenge.disney.mapper.FilmMapper;
import com.alkemy.challenge.disney.repository.FilmRepository;
import com.alkemy.challenge.disney.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmMapper filmMapper;

    @Autowired
    private FilmRepository filmRepository;

    public FilmDTO save(FilmDTO dto) {
        FilmEntity entity = filmMapper.filmDTO2Entity(dto, false);
        FilmEntity entitySaved = filmRepository.save(entity);
        FilmDTO result = filmMapper.filmEntity2DTO(entitySaved, false);
        return result;
    }

    public List<FilmDTO> getAllFilms() {
        List<FilmEntity> entities = filmRepository.findAll();
        List<FilmDTO> result = filmMapper.filmEntityList2DTOList(entities, false);
        return result;
    }
}
