package com.alkemy.challenge.disney.mapper;

import com.alkemy.challenge.disney.dto.ActorBasicDTO;
import com.alkemy.challenge.disney.dto.ActorDTO;
import com.alkemy.challenge.disney.entity.ActorEntity;
import com.alkemy.challenge.disney.entity.FilmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


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
        basicEntity2DTO(entity, actorDTO);
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


    public List<ActorBasicDTO> actorEntityFilterList2DTOList(List<ActorEntity> entities) {
        List<ActorBasicDTO> dtos = new ArrayList<>();
        for (ActorEntity entity : entities) {
            if (!entity.isDeleted()) {
                dtos.add(this.actorEntityFilter2DTO(entity));
            }
        }
        return dtos;
    }


    public ActorBasicDTO actorEntityFilter2DTO(ActorEntity entity) {
        ActorBasicDTO actorDTO = new ActorBasicDTO();
        actorDTO.setImage(entity.getImage());
        actorDTO.setName(entity.getName());
        return actorDTO;
    }


    private void basicEntity2DTO(ActorEntity entity, ActorDTO actorDTO) {
        actorDTO.setId(entity.getId());
        actorDTO.setImage(entity.getImage());
        actorDTO.setName(entity.getName());
    }

    public ActorEntity entityUpdate(ActorDTO actor, ActorEntity entity, boolean loadFilms) {
        if (actor.getImage() != null) { entity.setImage(actor.getImage()); }
        if (actor.getName() != null) { entity.setName(actor.getName()); }
        if (actor.getAge() != null) { entity.setAge(actor.getAge()); }
        if (actor.getWeight() != null) { entity.setWeight(actor.getWeight()); }
        if (actor.getStory() != null) { entity.setStory(actor.getStory()); }
        if (actor.isDeleted() != false) { entity.setDeleted(actor.isDeleted()); }
        return entity;

    }


}

