package com.alkemy.challenge.disney.repository;

import com.alkemy.challenge.disney.entity.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<ActorEntity, Long> {


}
