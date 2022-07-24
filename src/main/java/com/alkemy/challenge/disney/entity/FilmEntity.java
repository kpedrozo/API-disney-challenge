package com.alkemy.challenge.disney.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "films")
@Getter
@Setter
@SQLDelete(sql = "UPDATE films SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")

public class FilmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String title;
    @Column(name = "created_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private String creationDate;
    private Integer qualification;
    private boolean deleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.EAGER
            , targetEntity = GenreEntity.class
    )
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)
    private GenreEntity genre;

    @Column(name = "genre_id", nullable = false)
    private Long genreID;

    @ManyToMany( fetch = FetchType.LAZY,
            cascade = {  CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
            name = "rel_film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<ActorEntity> actors = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final FilmEntity other = (FilmEntity) obj;
        return other.id == this.id;
    }

    public void addActor(ActorEntity actor) {
        if(actors == null) {
            actors = new HashSet<>();
        }
        actors.add(actor);
    }

    public void removeActor(ActorEntity actor) {
        if(actors.contains(actor)) {
            actors.remove(actor);
        }
    }
}
