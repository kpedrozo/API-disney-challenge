package com.alkemy.challenge.disney.service.impl;

import com.alkemy.challenge.disney.dto.FilmBasicDTO;
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
    FilmMapper filmMapper;
    @Autowired
    FilmRepository filmRepository;

    public FilmDTO save(FilmDTO dto) {
        FilmEntity entity = filmMapper.filmDTO2Entity(dto, true);
        FilmEntity entitySaved = filmRepository.save(entity);
        FilmDTO result = filmMapper.filmEntity2DTO(entitySaved, true);
        return result;
    }

    public FilmDTO getDetailsById(Long id) {
        Boolean exists = filmRepository.existsById(id);
        if (exists) {
            FilmEntity entity = filmRepository.getReferenceById(id);
            FilmDTO dto = filmMapper.filmEntity2DTO(entity, true);
            return dto;
        }
        return null;
    }

    public FilmDTO update(FilmBasicDTO film, Long id) {
        Boolean exists = filmRepository.existsById(id);
        if (exists) {
            FilmEntity entity = filmRepository.getReferenceById(id);
            filmMapper.filmBasicDTO2Entity(film, entity);
            FilmEntity entityUpdated = filmRepository.save(entity);
            FilmDTO result = filmMapper.filmEntity2DTO(entityUpdated, true);
            return result;
        }
        return null;
    }

    public void delete(Long id) {
        Boolean exists = filmRepository.existsById(id);
        if (exists) {
            FilmEntity entity = filmRepository.getReferenceById(id);
            entity.setDeleted(true);
            filmRepository.save(entity);
        }
    }

    public List<FilmDTO> getAllFilms() {
        List<FilmEntity> entities = filmRepository.findAll();
        List<FilmDTO> result = filmMapper.filmEntityList2DTOList(entities, true);
        return result;
    }


}
