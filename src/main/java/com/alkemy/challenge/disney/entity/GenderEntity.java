package com.alkemy.challenge.disney.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genders")
@Getter
@Setter
public class GenderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;

    @OneToMany(mappedBy = "gender")
    private Set<FilmEntity> films = new HashSet<>();

}
