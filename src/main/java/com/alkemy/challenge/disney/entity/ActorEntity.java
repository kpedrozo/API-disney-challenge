package com.alkemy.challenge.disney.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actors")
@Getter
@Setter
@SQLDelete(sql = "UPDATE actors SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")

public class ActorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Integer weight;
    private String story;
    private boolean deleted = Boolean.FALSE;

    //mappedBy : atributo de la entidad propietaria que controla la relacion.
    @ManyToMany(mappedBy = "actors")
    private Set<FilmEntity> films = new HashSet<>();

    // ADD and REMOVE filmaciones
    public void addFilms(FilmEntity film){
        this.films.add(film);
        film.getActors().add(this);
    }

    public void removeFilm(FilmEntity film){
        this.films.remove(film);
        film.getActors().remove(this);
    }


}
