package com.alkemy.challenge.disney.repository.specifications;


import com.alkemy.challenge.disney.dto.ActorFiltersDTO;
import com.alkemy.challenge.disney.entity.ActorEntity;
import com.alkemy.challenge.disney.entity.FilmEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;

@Component
public class ActorSpecification {

    public Specification<ActorEntity> getByFilters(ActorFiltersDTO filtersDTO) {
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

            if (StringUtils.hasLength(filtersDTO.getAge())) {
                String age = filtersDTO.getAge();
                predicates.add(
                        criteriaBuilder.equal(root.get("age"), age)
                );
            }

            if (StringUtils.hasLength(filtersDTO.getWeight())) {
                String weight = filtersDTO.getWeight();
                predicates.add(
                        criteriaBuilder.equal(root.get("weight"), weight)
                );
            }

            if (!CollectionUtils.isEmpty(filtersDTO.getFilms())) {
                Join<FilmEntity, ActorEntity> join = root.join("movies", JoinType.INNER);
                Expression<String> filmsId = join.get("id");
                predicates.add(filmsId.in(filtersDTO.getFilms()));
            }

            // remove duplicates
            query.distinct(true);

            // order resolver
            String orderByField = "name";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
