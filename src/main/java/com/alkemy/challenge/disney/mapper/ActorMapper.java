package com.alkemy.challenge.disney.mapper;

import com.alkemy.challenge.disney.dto.ActorDTO;
import com.alkemy.challenge.disney.entity.ActorEntity;
import com.alkemy.challenge.disney.entity.FilmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class ActorMapper {

    @Autowired
    private FilmMapper filmMapper;

    public ActorEntity actorDTO2Entity(ActorDTO dto, boolean loadFilms) {
        ActorEntity actorEntity = new ActorEntity();
        actorEntity.setImage(dto.getImage());
        actorEntity.setName(dto.getName());
        actorEntity.setAge(dto.getAge());
        actorEntity.setWeight(dto.getWeight());
        actorEntity.setStory(dto.getStory());
        actorEntity.setDeleted(dto.isDeleted());
        if (loadFilms & !(dto.getFilms()==null)) {
            List<FilmEntity> filmsEntity = this.filmMapper.filmDTOList2Entity(dto.getFilms(), false);
            actorEntity.setFilms(filmsEntity.stream().collect(Collectors.toSet()));
        }
        return actorEntity;
    }

    public List<ActorEntity> actorsDTOList2Entity(List<ActorDTO> dtos, boolean loadFilms) {
        List<ActorEntity> entities = new ArrayList<>();
        for (ActorDTO dto : dtos) {
            if (!dto.isDeleted()) {
                entities.add(this.actorDTO2Entity(dto, loadFilms));
            }
        }
        return entities;
    }

    public ActorDTO actorEntity2DTO(ActorEntity entity, boolean loadFilms) {
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setId(entity.getId());
        actorDTO.setImage(entity.getImage());
        actorDTO.setName(entity.getName());
        actorDTO.setAge(entity.getAge());
        actorDTO.setWeight(entity.getWeight());
        actorDTO.setStory(entity.getStory());
        actorDTO.setDeleted(entity.isDeleted());

        if (loadFilms) {
            Set<FilmEntity> films = entity.getFilms();
            List<FilmEntity> filmEntities = new ArrayList<>(films);
            actorDTO.setFilms(this.filmMapper.filmEntityList2DTOList(filmEntities, false));
        }
        return actorDTO;
    }


    public List<ActorDTO> actorEntityList2DTOList(List<ActorEntity> entities, boolean loadFilms) {
        List<ActorDTO> dtos = new ArrayList<>();
        for (ActorEntity entity : entities) {
            if (!entity.isDeleted()) {
                dtos.add(this.actorEntity2DTO(entity, loadFilms));
            }
        }
        return dtos;
    }


    public List<ActorDTO> actorEntityFilterList2DTOList(List<ActorEntity> entities) {
        List<ActorDTO> dtos = new ArrayList<>();
        for (ActorEntity entity : entities) {
            if (!entity.isDeleted()) {
                dtos.add(this.actorEntityFilter2DTO(entity));
            }
        }
        return dtos;
    }

    private ActorDTO actorEntityFilter2DTO(ActorEntity entity) {
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setId(entity.getId());
        actorDTO.setImage(entity.getImage());
        actorDTO.setName(entity.getName());
        return actorDTO;
    }

    public ActorEntity entityUpdate(ActorDTO actor, ActorEntity entity, boolean loadFilms) {
        if (actor.getImage() != null) { entity.setImage(actor.getImage()); }
        if (actor.getName() != null) { entity.setName(actor.getName()); }
        if (actor.getAge() != null) { entity.setAge(actor.getAge()); }
        if (actor.getWeight() != null) { entity.setWeight(actor.getWeight()); }
        if (actor.getStory() != null) { entity.setStory(actor.getStory()); }
        if (actor.isDeleted() != false) { entity.setDeleted(actor.isDeleted()); }
        if (loadFilms & (actor.getFilms() != null)) {
            List<FilmEntity> filmsEntity = this.filmMapper.filmDTOList2Entity(actor.getFilms(), false);
            entity.setFilms(filmsEntity.stream().collect(Collectors.toSet()));
        }
        return entity;

    }
}

