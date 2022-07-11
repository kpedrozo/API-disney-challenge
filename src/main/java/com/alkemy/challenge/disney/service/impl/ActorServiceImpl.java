package com.alkemy.challenge.disney.service.impl;

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

    @Autowired
    private ActorRepository actorRepository;

    public ActorDTO save(ActorDTO dto) {
        ActorEntity entity = actorMapper.actorDTO2Entity(dto, false);
        ActorEntity entitySaved = actorRepository.save(entity);
        ActorDTO result = actorMapper.actorEntity2DTO(entitySaved, false);
        return result;
    }

    public List<ActorDTO> getAllActors() {
        List<ActorEntity> entities = actorRepository.findAll();
        List<ActorDTO> result = actorMapper.actorEntityList2DTOList(entities, false);
        return result;
    }


}
