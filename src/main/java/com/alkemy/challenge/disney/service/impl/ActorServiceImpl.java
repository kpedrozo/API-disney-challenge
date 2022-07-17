package com.alkemy.challenge.disney.service.impl;

import com.alkemy.challenge.disney.dto.ActorBasicDTO;
import com.alkemy.challenge.disney.dto.ActorDTO;
import com.alkemy.challenge.disney.entity.ActorEntity;
import com.alkemy.challenge.disney.mapper.ActorMapper;
import com.alkemy.challenge.disney.repository.ActorRepository;
import com.alkemy.challenge.disney.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorMapper actorMapper;

    @Autowired(required = false)
    private ActorEntity actorEntity;

    @Autowired
    private ActorRepository actorRepository;

    public ActorDTO save(ActorDTO dto) {
        ActorEntity entity = actorMapper.actorDTO2Entity(dto, true);
        ActorEntity entitySaved = actorRepository.save(entity);
        ActorDTO result = actorMapper.actorEntity2DTO(entitySaved, true);
        return result;
    }

    public ActorDTO update(ActorBasicDTO actor, Long id) {
        Boolean exists = actorRepository.existsById(id);
        if (exists) {
            ActorEntity entity = actorRepository.getReferenceById(id);
            actorMapper.actorBasicDTO2Entity(actor, entity);
            ActorEntity entityUpdated = actorRepository.save(entity);
            ActorDTO result = actorMapper.actorEntity2DTO(entityUpdated, true);
            return result;
        }
        return null;
    }

    public List<ActorDTO> getAllActors() {
        List<ActorEntity> entities = actorRepository.findAll();
        List<ActorDTO> result = actorMapper.actorEntityList2DTOList(entities, true);
        return result;
    }

    public void delete(Long id) {
        Boolean exists = actorRepository.existsById(id);
        if (exists) {
            ActorEntity actorEntity = actorRepository.getReferenceById(id);
            actorEntity.setDeleted(true);
            actorRepository.save(actorEntity);
        }
    }

    public ActorDTO getDetailsByID(Long id) {
        Boolean exists = actorRepository.existsById(id);
        if (exists) {
            ActorEntity entity = actorRepository.getReferenceById(id);
            ActorDTO dto = actorMapper.actorEntity2DTO(entity, true);
            return dto;
        }
        return null;
    }



}
