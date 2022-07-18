package com.alkemy.challenge.disney.controller;

import com.alkemy.challenge.disney.dto.GenreDTO;
import com.alkemy.challenge.disney.service.GenreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAll() {
        List<GenreDTO> genres = this.genreService.getAllGenres();
        return ResponseEntity.ok().body(genres);
    }

    @PostMapping
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO gender) {
        GenreDTO genreCreated = genreService.save(gender);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getById(@PathVariable Long id) {
        GenreDTO genreDTO = genreService.getById(id);
        return ResponseEntity.ok().body(genreDTO);
    }
}

