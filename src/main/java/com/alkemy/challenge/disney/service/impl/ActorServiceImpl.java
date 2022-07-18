package com.alkemy.challenge.disney.service.impl;

import com.alkemy.challenge.disney.dto.ActorDTO;
import com.alkemy.challenge.disney.dto.ActorFiltersDTO;
import com.alkemy.challenge.disney.entity.ActorEntity;
import com.alkemy.challenge.disney.exception.ParamNotFound;
import com.alkemy.challenge.disney.mapper.ActorMapper;
import com.alkemy.challenge.disney.repository.ActorRepository;
import com.alkemy.challenge.disney.repository.specifications.ActorSpecification;
import com.alkemy.challenge.disney.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorMapper actorMapper;

    @Autowired(required = false)
    private ActorEntity actorEntity;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ActorSpecification actorSpecification;

    public ActorDTO save(ActorDTO dto) {
        ActorEntity entity = actorMapper.actorDTO2Entity(dto, true);
        ActorEntity entitySaved = actorRepository.save(entity);
        ActorDTO result = actorMapper.actorEntity2DTO(entitySaved, true);
        return result;
    }

    public ActorDTO update(ActorDTO actor, Long id) {
        Boolean exists = actorRepository.existsById(id);
        if (!exists) {
            throw new ParamNotFound("ID actor no valido");
        }
            ActorEntity entity = actorRepository.getReferenceById(id);
            actorMapper.entityUpdate(actor, entity, true);
            ActorEntity entityUpdated = actorRepository.save(entity);
            ActorDTO result = actorMapper.actorEntity2DTO(entityUpdated, true);
            return result;
    }


    public List<ActorDTO> getByFilters(String name, String age, String weight, Set<Long> movies, String order) {
        ActorFiltersDTO filtersDTO = new ActorFiltersDTO (name, age, weight, movies, order);
        List<ActorEntity> entities = actorRepository.findAll(actorSpecification.getByFilters(filtersDTO));
        List<ActorDTO> dtos = actorMapper.actorEntityFilterList2DTOList(entities);
        return dtos;
    }

    public List<ActorDTO> getAllActors() {
        List<ActorEntity> entities = actorRepository.findAll();
        List<ActorDTO> result = actorMapper.actorEntityList2DTOList(entities, true);
        return result;
    }

    public void delete(Long id) {
        Boolean exists = actorRepository.existsById(id);
        if (!exists) {
            throw new ParamNotFound("ID actor no valido");
        }
            ActorEntity actorEntity = actorRepository.getReferenceById(id);
            actorEntity.setDeleted(true);
            actorRepository.save(actorEntity);
    }

    public ActorDTO getDetailsByID(Long id) {
        Boolean exists = actorRepository.existsById(id);
        if (!exists) {
            throw new ParamNotFound("ID actor no valido");
        }
        ActorEntity entity = actorRepository.getReferenceById(id);
        ActorDTO dto = actorMapper.actorEntity2DTO(entity, true);
        return dto;
    }



}
