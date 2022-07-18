package com.alkemy.challenge.disney.repository;

import com.alkemy.challenge.disney.entity.ActorEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<ActorEntity, Long> , JpaSpecificationExecutor<ActorEntity> {

    List<ActorEntity> findAll(Specification<ActorEntity> spec);

}
