package com.alkemy.challenge.disney.controller;

import com.alkemy.challenge.disney.dto.ActorDTO;
import com.alkemy.challenge.disney.dto.FilmBasicDTO;
import com.alkemy.challenge.disney.dto.FilmDTO;
import com.alkemy.challenge.disney.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


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

    @GetMapping("/id")
    public ResponseEntity<FilmDTO> getById (@RequestParam (value = "id") Long id) {
        FilmDTO film = filmService.getDetailsById(id);
        return ResponseEntity.ok(film);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FilmDTO> update(@RequestBody FilmBasicDTO film, @PathVariable ("id") Long id) {
        FilmDTO filmUpdated = filmService.update(film, id);
        return ResponseEntity.ok(filmUpdated);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam (value = "id") Long id) {
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

}
