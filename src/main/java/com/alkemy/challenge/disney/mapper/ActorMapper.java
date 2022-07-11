package com.alkemy.challenge.disney.mapper;

import com.alkemy.challenge.disney.dto.ActorDTO;
import com.alkemy.challenge.disney.dto.FilmDTO;
import com.alkemy.challenge.disney.entity.ActorEntity;
import com.alkemy.challenge.disney.entity.FilmEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class ActorMapper {

    private FilmMapper filmMapper;

    public ActorEntity actorDTO2Entity(ActorDTO dto, boolean loadFilms) {
        ActorEntity actorEntity = new ActorEntity();
        actorEntity.setImage(dto.getImage());
        actorEntity.setName(dto.getName());
        actorEntity.setAge(dto.getAge());
        actorEntity.setWeight(dto.getWeight());
        actorEntity.setStory(dto.getStory());
        if (loadFilms) {
            Set<FilmEntity> filmsEntity = this.filmMapper.filmDTOList2Entity(dto.getFilms().stream().collect(Collectors.toList()), false);
            actorEntity.setFilms(filmsEntity);
        }
        return actorEntity;
    }

    public Set<ActorEntity> actorsDTOList2Entity(List<ActorDTO> dtos, boolean loadFilms) {
        Set <ActorEntity> entities = new HashSet<>();
        for (ActorDTO dto : dtos) {
            entities.add(this.actorDTO2Entity(dto, loadFilms));
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

        if (loadFilms) {
            List<FilmDTO> filmsDTO = this.filmMapper.filmEntityList2DTOList(entity.getFilms().stream().collect(Collectors.toList()), false);
            actorDTO.setFilms(filmsDTO);
        }
        return actorDTO;
    }


    public List<ActorDTO> actorEntityList2DTOList(List<ActorEntity> entities, boolean loadFilms) {
        List<ActorDTO> dtos = new ArrayList<>();
        for (ActorEntity entity : entities) {
            dtos.add(this.actorEntity2DTO(entity, loadFilms));
        }
        return dtos;
    }


}
