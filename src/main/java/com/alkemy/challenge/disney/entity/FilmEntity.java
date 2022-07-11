package com.alkemy.challenge.disney.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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
public class FilmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String title;
    @Column(name = "created_date")
    @DateTimeFormat(pattern = "yyy/MM/dd")
    private LocalDate creationDate;
    private Integer qualification;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "gender_id", insertable = false, updatable = false)
    private GenderEntity gender;

    @Column(name = "gender_id", nullable = false)
    private Long genderID;

    @ManyToMany(
            cascade = { CascadeType.PERSIST, CascadeType.MERGE })
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
