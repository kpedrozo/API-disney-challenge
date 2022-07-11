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
public class FilmMapper {

    private ActorMapper actorMapper;
    private GenderMapper genderMapper;

    public FilmEntity filmDTO2Entity(FilmDTO dto, boolean loadActors) {
        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setImage(dto.getImage());
        filmEntity.setTitle(dto.getTitle());
        filmEntity.setCreationDate(dto.getCreationDate());
        filmEntity.setQualification(dto.getQualification());
        filmEntity.setGender(this.genderMapper.genderDTO2Entity(dto.getGender()));
        filmEntity.setGenderID(dto.getGenderID());

        if (loadActors) {
            Set<ActorEntity> actorsEntity = this.actorMapper.actorsDTOList2Entity(dto.getActors().stream().collect(Collectors.toList()), false);
            filmEntity.setActors(actorsEntity);
        }
        return filmEntity;
    }

    public Set<FilmEntity> filmDTOList2Entity(List<FilmDTO> dtos, boolean loadActors) {
        Set <FilmEntity> entities = new HashSet<>();
        for (FilmDTO dto : dtos) {
            entities.add(this.filmDTO2Entity(dto, loadActors));
        }
        return entities;
    }

    public FilmDTO filmEntity2DTO (FilmEntity entity, boolean loadActors) {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setId(entity.getId());
        filmDTO.setImage(entity.getImage());
        filmDTO.setTitle(entity.getTitle());
        filmDTO.setCreationDate(entity.getCreationDate());
        filmDTO.setQualification(entity.getQualification());
        filmDTO.setGender(this.genderMapper.genderEntity2DTO(entity.getGender()));
        filmDTO.setGenderID(entity.getGenderID());

        // Al tener un parametro loadActors en FALSE, no re cargamos los actores, cuando desde actores pedimos las peliculas.
        if (loadActors) {
            List<ActorDTO> actorsDTO = this.actorMapper.actorEntityList2DTOList(entity.getActors().stream().collect(Collectors.toList()), false);
            filmDTO.setActors(actorsDTO);
        }
        return filmDTO;
    }

    public List<FilmDTO> filmEntityList2DTOList(List<FilmEntity> entities, boolean loadActors) {
        List <FilmDTO> dtos = new ArrayList<>();
        for (FilmEntity entity : entities) {
            dtos.add(this.filmEntity2DTO(entity, loadActors));
        }
        return dtos;
    }


}
