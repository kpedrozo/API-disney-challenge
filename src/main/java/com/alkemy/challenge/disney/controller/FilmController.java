package com.alkemy.challenge.disney.controller;

import com.alkemy.challenge.disney.dto.FilmBasicDTO;
import com.alkemy.challenge.disney.dto.FilmDTO;
import com.alkemy.challenge.disney.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("movies")
public class FilmController {

    @Autowired
    private FilmService filmService;


    @PostMapping
    public ResponseEntity<FilmDTO> save(@Valid @RequestBody FilmDTO film) {
        FilmDTO filmCreated = filmService.save(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getById (@PathVariable Long id) {
        FilmDTO film = filmService.getDetailsById(id);
        return ResponseEntity.ok(film);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FilmDTO> update(@Valid @RequestBody FilmDTO film, @PathVariable ("id") Long id) {
        FilmDTO filmUpdated = filmService.update(film, id);
        return ResponseEntity.ok(filmUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        filmService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping()
    public ResponseEntity<List<FilmBasicDTO>> getDetailsByFilters (
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String creationDate,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<FilmBasicDTO> films = filmService.getByFilters(name, genre, creationDate, order);
        return ResponseEntity.ok(films);
    }

    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<FilmDTO> addActor (
            @PathVariable ("idMovie") Long idFilm,
            @PathVariable ("idCharacter") Long idActor) {
        FilmDTO film = filmService.addActor(idFilm, idActor);
        return ResponseEntity.ok(film);
    }

    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<FilmDTO> deleteActor (
            @PathVariable ("idMovie") Long idFilm,
            @PathVariable ("idCharacter") Long idActor) {
        FilmDTO film = filmService.deleteActor(idFilm, idActor);
        return ResponseEntity.ok(film);
    }

}
