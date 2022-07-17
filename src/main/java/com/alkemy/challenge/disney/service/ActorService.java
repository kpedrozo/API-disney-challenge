package com.alkemy.challenge.disney.service;

import com.alkemy.challenge.disney.dto.ActorBasicDTO;
import com.alkemy.challenge.disney.dto.ActorDTO;

import java.util.List;

public interface ActorService {

    List<ActorDTO> getAllActors();

    ActorDTO save(ActorDTO actor);

    void delete(Long id);

    ActorDTO getDetailsByID(Long id);

    ActorDTO update(ActorBasicDTO actor, Long id);
}
