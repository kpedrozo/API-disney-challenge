package com.alkemy.challenge.disney.mapper;

import com.alkemy.challenge.disney.dto.FilmBasicDTO;
import com.alkemy.challenge.disney.dto.FilmDTO;
import com.alkemy.challenge.disney.dto.GenreDTO;
import com.alkemy.challenge.disney.entity.ActorEntity;
import com.alkemy.challenge.disney.entity.FilmEntity;
import com.alkemy.challenge.disney.entity.GenreEntity;
import com.alkemy.challenge.disney.repository.GenreRepository;
import com.alkemy.challenge.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmMapper {

    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private ActorMapper actorMapper;
    @Autowired
    private GenreService genreService;
    @Autowired
    private GenreRepository genreRepository;

    public FilmEntity filmDTO2Entity(FilmDTO dto, boolean loadActors) {
        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setImage(dto.getImage());
        filmEntity.setTitle(dto.getTitle());
        filmEntity.setCreationDate(dto.getCreationDate());
        filmEntity.setQualification(dto.getQualification());
        filmEntity.setDeleted(dto.isDeleted());
        GenreEntity genre = genreForMovie(dto.getGenreID());
        filmEntity.setGenre(genre);
        filmEntity.setGenreID(dto.getGenreID());
        if (loadActors) {
            List<ActorEntity> actorsEntity = this.actorMapper.actorsDTOList2Entity(dto.getActors(),false);
            filmEntity.setActors(actorsEntity.stream().collect(Collectors.toSet()));
        }
        return filmEntity;
    }

    private GenreEntity genreForMovie(Long genreID) {
        GenreEntity genre = genreRepository.getReferenceById(genreID);
        genre.setId(genreID);
        genre.setName(genre.getName());
        genre.setImage(genre.getImage());
        return genre;
    }

    private GenreDTO genreForMovieDTO (Long genreID) {
        GenreEntity genreEntity = genreForMovie(genreID);
        GenreDTO genre = genreMapper.genreEntity2DTO(genreEntity);
        return genre;
    }


    private void basicEntity2DTO(FilmEntity entity, FilmDTO dto) {
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate());
    }


    public FilmDTO filmEntity2DTO (FilmEntity entity, boolean loadActors) {
        FilmDTO filmDTO = new FilmDTO();
        basicEntity2DTO(entity, filmDTO);
        filmDTO.setQualification(entity.getQualification());
        filmDTO.setDeleted(entity.isDeleted());
        GenreDTO genre = genreForMovieDTO(entity.getGenreID());
        filmDTO.setGenre(genre);
        filmDTO.setGenreID(entity.getGenreID());
        if (loadActors) {
            Set<ActorEntity> actors = entity.getActors();
            List<ActorEntity> actorEntities = new ArrayList<>(actors);
            filmDTO.setActors(this.actorMapper.actorEntityList2DTOList(actorEntities, false));
        }
        return filmDTO;
    }


    public List<FilmDTO> filmEntityList2DTOList(List<FilmEntity> entities, boolean loadActors) {
        List <FilmDTO> dtos = new ArrayList<>();
        for (FilmEntity entity : entities) {
            if (!entity.isDeleted()){
                dtos.add(this.filmEntity2DTO(entity, loadActors));
            }
        }
        return dtos;
    }


    public FilmEntity entityUpdate(FilmDTO film, FilmEntity entity, boolean loadActors) {
        if (film.getImage() != null) { entity.setImage(film.getImage()); }
        if (film.getTitle() != null) { entity.setTitle(film.getTitle()); }
        if (film.getCreationDate() != null) { entity.setCreationDate(film.getCreationDate()); }
        if (film.getQualification() != null) { entity.setQualification(film.getQualification()); }
        if (film.getGenreID() != null) { entity.setGenreID(film.getGenreID()); }
        if (film.isDeleted() != false) { entity.setDeleted(film.isDeleted()); }
        return entity;

    }


    public List<FilmBasicDTO> filmEntityFilterList2DTOList(List<FilmEntity> entities) {
        List<FilmBasicDTO> dtos = new ArrayList<>();
        for (FilmEntity entity : entities) {
            if (!entity.isDeleted()) {
                dtos.add(this.filmEntityFilter2DTO(entity));
            }
        }
        return dtos;
    }

    private FilmBasicDTO filmEntityFilter2DTO(FilmEntity entity) {
        FilmBasicDTO filmDTO = new FilmBasicDTO();
        filmDTO.setImage(entity.getImage());
        filmDTO.setTitle(entity.getTitle());
        filmDTO.setCreationDate(entity.getCreationDate().toString());
        return filmDTO;
    }

}
