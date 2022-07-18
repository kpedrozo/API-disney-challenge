package com.alkemy.challenge.disney.repository.specifications;

import com.alkemy.challenge.disney.dto.FilmFiltersDTO;
import com.alkemy.challenge.disney.entity.FilmEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import javax.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilmSpecification {

    public Specification<FilmEntity> getByFilters(FilmFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            if (StringUtils.hasLength(filtersDTO.getGenre())) {
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("genre")), "%" + filtersDTO.getGenre().toLowerCase() + "%"
                        )
                );
            }

            query.distinct(true);

            String orderByField = "creationDate";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}




