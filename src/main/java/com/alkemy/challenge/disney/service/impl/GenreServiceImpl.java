package com.alkemy.challenge.disney.service.impl;

import com.alkemy.challenge.disney.dto.GenreDTO;
import com.alkemy.challenge.disney.entity.GenreEntity;
import com.alkemy.challenge.disney.exception.ErrorEnum;
import com.alkemy.challenge.disney.exception.ParamNotFound;
import com.alkemy.challenge.disney.mapper.GenreMapper;
import com.alkemy.challenge.disney.repository.GenreRepository;
import com.alkemy.challenge.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private GenreRepository genreRepository;

    public GenreDTO save(GenreDTO dto) {
        GenreEntity entity = genreMapper.genreDTO2Entity(dto);
        GenreEntity entitySaved = genreRepository.save(entity);
        GenreDTO result = genreMapper.genreEntity2DTO(entitySaved);
        return result;
    }

    public GenreDTO getById(Long id) {
        boolean exists = genreRepository.existsById(id);
        if (!exists) {
            throw new ParamNotFound(ErrorEnum.IDGENRENOTVALID.getMessage());
        }
        GenreEntity entity = genreRepository.getReferenceById(id);
        GenreDTO result = genreMapper.genreEntity2DTO(entity);
        return result;
    }

    public List<GenreDTO> getAllGenres() {
        List<GenreEntity> entities = genreRepository.findAll();
        List<GenreDTO> result = genreMapper.genreEntityList2DTOList(entities);
        return result;
    }



}
