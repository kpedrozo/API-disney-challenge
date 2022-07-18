package com.alkemy.challenge.disney.service;

import com.alkemy.challenge.disney.dto.ActorDTO;

import java.util.List;
import java.util.Set;

public interface ActorService {

    List<ActorDTO> getAllActors();

    ActorDTO save(ActorDTO actor);

    void delete(Long id);

    ActorDTO getDetailsByID(Long id);

    ActorDTO update(ActorDTO actor, Long id);

    List<ActorDTO> getByFilters(String name, String age, String weight, Set<Long> movies, String order);
}
