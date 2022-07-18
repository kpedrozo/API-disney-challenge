package com.alkemy.challenge.disney.controller;

import com.alkemy.challenge.disney.dto.FilmBasicDTO;
import com.alkemy.challenge.disney.dto.FilmDTO;
import com.alkemy.challenge.disney.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping
    public ResponseEntity<List<FilmDTO>> getAll() {
        List<FilmDTO> films = filmService.getAllFilms();
        return ResponseEntity.ok().body(films);
    }

    @PostMapping
    public ResponseEntity<FilmDTO> save(@RequestBody FilmDTO film) {
        FilmDTO filmCreated = filmService.save(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getById (@PathVariable Long id) {
        FilmDTO film = filmService.getDetailsById(id);
        return ResponseEntity.ok(film);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FilmDTO> update(@RequestBody FilmBasicDTO film, @PathVariable ("id") Long id) {
        FilmDTO filmUpdated = filmService.update(film, id);
        return ResponseEntity.ok(filmUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        filmService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("/movies")
    public ResponseEntity<List<FilmDTO>> getDetailsByFilters (
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String creationDate,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<FilmDTO> films = filmService.getByFilters(name, genre, creationDate, order);
        return ResponseEntity.ok(films);
    }

    @PostMapping("/movies/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<FilmDTO> addActor (
            @PathVariable ("idMovie") Long idFilm,
            @PathVariable ("idCharacter") Long idActor) {
        FilmDTO film = filmService.addActor(idFilm, idActor);
        return ResponseEntity.ok(film);
    }

    @DeleteMapping("/movies/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<FilmDTO> deleteActor (
            @PathVariable ("idMovie") Long idFilm,
            @PathVariable ("idCharacter") Long idActor) {
        FilmDTO film = filmService.deleteActor(idFilm, idActor);
        return ResponseEntity.ok(film);
    }

}
