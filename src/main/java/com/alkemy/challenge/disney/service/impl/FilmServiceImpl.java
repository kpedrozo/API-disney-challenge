package com.alkemy.challenge.disney.service.impl;

import com.alkemy.challenge.disney.dto.FilmBasicDTO;
import com.alkemy.challenge.disney.dto.FilmDTO;
import com.alkemy.challenge.disney.dto.FilmFiltersDTO;
import com.alkemy.challenge.disney.entity.ActorEntity;
import com.alkemy.challenge.disney.entity.FilmEntity;
import com.alkemy.challenge.disney.exception.ErrorEnum;
import com.alkemy.challenge.disney.exception.ParamNotFound;
import com.alkemy.challenge.disney.mapper.FilmMapper;
import com.alkemy.challenge.disney.repository.ActorRepository;
import com.alkemy.challenge.disney.repository.FilmRepository;
import com.alkemy.challenge.disney.repository.specifications.FilmSpecification;
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

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private FilmSpecification filmSpecification;

    public FilmDTO save(FilmDTO dto) {
        FilmEntity entity = filmMapper.filmDTO2Entity(dto, true);
        FilmEntity entitySaved = filmRepository.save(entity);
        FilmDTO result = filmMapper.filmEntity2DTO(entitySaved, true);
        return result;
    }

    public FilmDTO update(FilmDTO film, Long id) {
        Boolean exists = filmRepository.existsById(id);
        if (!exists) {
            throw new ParamNotFound(ErrorEnum.IDFILMNOTVALID.getMessage());
        }
        FilmEntity entity = filmRepository.getReferenceById(id);
        filmMapper.entityUpdate(film, entity, false);
        FilmEntity entityUpdated = filmRepository.save(entity);
        FilmDTO result = filmMapper.filmEntity2DTO(entityUpdated, true);
        return result;
    }

    public FilmDTO getDetailsById(Long id) {
        Boolean exists = filmRepository.existsById(id);
        if (!exists) {
            throw new ParamNotFound(ErrorEnum.IDFILMNOTVALID.getMessage());
        }
        FilmEntity entity = filmRepository.getReferenceById(id);
        FilmDTO dto = filmMapper.filmEntity2DTO(entity, true);
        return dto;
    }

    public void delete(Long id) {
        Boolean exists = filmRepository.existsById(id);
        if (!exists) {
            throw new ParamNotFound(ErrorEnum.IDFILMNOTVALID.getMessage());
        }
        filmRepository.deleteById(id);
    }

    public List<FilmBasicDTO> getByFilters(String name, String genre, String creationDate, String order) {
        FilmFiltersDTO filtersDTO = new FilmFiltersDTO(name, genre, creationDate, order);
        List<FilmEntity> entities = filmRepository.findAll(filmSpecification.getByFilters(filtersDTO));
        List<FilmBasicDTO> dtos = filmMapper.filmEntityFilterList2DTOList(entities);
        return dtos;
    }

    public FilmDTO addActor(Long idFilm, Long idActor) {
        Boolean exists = filmRepository.existsById(idFilm);
        if (!exists) {
            throw new ParamNotFound(ErrorEnum.IDFILMNOTVALID.getMessage());
        }
        Boolean existsActor = actorRepository.existsById(idActor);
        if (!existsActor) {
            throw new ParamNotFound(ErrorEnum.IDACTORNOTVALID.getMessage());
        }
        FilmEntity entity = filmRepository.getReferenceById(idFilm);
        ActorEntity actor = actorRepository.getReferenceById(idActor);
        entity.addActor(actor);
        FilmEntity entitySaved = filmRepository.save(entity);
        FilmDTO result = filmMapper.filmEntity2DTO(entitySaved, true);
        return result;
    }

    public FilmDTO deleteActor(Long idFilm, Long idActor) {Boolean exists = filmRepository.existsById(idFilm);
        if (!exists) {
            throw new ParamNotFound(ErrorEnum.IDFILMNOTVALID.getMessage());
        }
            Boolean existsActor = actorRepository.existsById(idActor);
        if (!existsActor) {
            throw new ParamNotFound(ErrorEnum.IDACTORNOTVALID.getMessage());
        }
            FilmEntity entity = filmRepository.getReferenceById(idFilm);
            ActorEntity actor = actorRepository.getReferenceById(idActor);
            entity.removeActor(actor);
            FilmEntity entitySaved = filmRepository.save(entity);
            FilmDTO result = filmMapper.filmEntity2DTO(entitySaved, true);
            return result;
    }


}
