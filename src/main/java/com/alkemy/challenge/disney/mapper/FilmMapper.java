package com.alkemy.challenge.disney.mapper;

import com.alkemy.challenge.disney.dto.FilmDTO;
import com.alkemy.challenge.disney.entity.ActorEntity;
import com.alkemy.challenge.disney.entity.FilmEntity;
import com.alkemy.challenge.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmMapper {

    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private ActorMapper actorMapper;
    @Autowired
    private GenreService genreService;

    public FilmEntity filmDTO2Entity(FilmDTO dto, boolean loadActors) {
        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setImage(dto.getImage());
        filmEntity.setTitle(dto.getTitle());
        filmEntity.setCreationDate(dto.getCreationDate());
        filmEntity.setQualification(dto.getQualification());
        filmEntity.setDeleted(dto.isDeleted());
        filmEntity.setGenreID(dto.getGenreID());
        if (loadActors) {
            List<ActorEntity> actorsEntity = this.actorMapper.actorsDTOList2Entity(dto.getActors(),false);
            filmEntity.setActors(actorsEntity.stream().collect(Collectors.toSet()));
        }
        return filmEntity;
    }


    private void basicEntity2DTO(FilmEntity entity, FilmDTO dto) {
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate());
    }

    public List<FilmEntity> filmDTOList2Entity(List<FilmDTO> dtos, boolean loadActors) {
        List <FilmEntity> entities = new ArrayList<>();
        for (FilmDTO dto : dtos) {
            if (!dto.isDeleted()) {
                entities.add(this.filmDTO2Entity(dto, loadActors));
            }
        }
        return entities;
    }


    public FilmDTO filmEntity2DTO (FilmEntity entity, boolean loadActors) {
        FilmDTO filmDTO = new FilmDTO();
        basicEntity2DTO(entity, filmDTO);

        filmDTO.setQualification(entity.getQualification());
        filmDTO.setDeleted(entity.isDeleted());
        filmDTO.setGenreID(entity.getGenreID());
        if (loadActors) {
            Set<ActorEntity> actors = entity.getActors();
            List<ActorEntity> actorEntities = new ArrayList<>(actors);
            filmDTO.setActors(this.actorMapper.actorEntityList2DTOList(actorEntities, false));
        }
        return filmDTO;
    }


    public List<FilmDTO> filmEntityList2DTOList(List<FilmEntity> entities, boolean loadActors) {
        List <FilmDTO> dtos = new ArrayList<>();
        for (FilmEntity entity : entities) {
            if (!entity.isDeleted()){
                dtos.add(this.filmEntity2DTO(entity, loadActors));
            }
        }
        return dtos;
    }


    public FilmEntity entityUpdate(FilmDTO film, FilmEntity entity, boolean loadActors) {
        if (film.getImage() != null) { entity.setImage(film.getImage()); }
        if (film.getTitle() != null) { entity.setTitle(film.getTitle()); }
        if (film.getCreationDate() != null) { entity.setCreationDate(film.getCreationDate()); }
        if (film.getQualification() != null) { entity.setQualification(film.getQualification()); }
        if (film.getGenreID() != null) { entity.setGenreID(film.getGenreID()); }
        if (film.isDeleted() != false) { entity.setDeleted(film.isDeleted()); }
        return entity;

    }


    public List<FilmDTO> filmEntityFilterList2DTOList(List<FilmEntity> entities) {
        List<FilmDTO> dtos = new ArrayList<>();
        for (FilmEntity entity : entities) {
            if (!entity.isDeleted()) {
                dtos.add(this.filmEntityFilter2DTO(entity));
            }
        }
        return dtos;
    }

    private FilmDTO filmEntityFilter2DTO(FilmEntity entity) {
        FilmDTO filmDTO = new FilmDTO();
        basicEntity2DTO(entity, filmDTO);
        return filmDTO;
    }

}
