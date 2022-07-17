package com.alkemy.challenge.disney.controller;

import com.alkemy.challenge.disney.dto.GenderDTO;
import com.alkemy.challenge.disney.service.GenderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genders")
public class GenderController {

    @Autowired
    private GenderService genderService;

    @GetMapping
    public ResponseEntity<List<GenderDTO>> getAll() {
        List<GenderDTO> genders = this.genderService.getAllGenders();
        return ResponseEntity.ok().body(genders);
    }

    @PostMapping
    public ResponseEntity<GenderDTO> save(@RequestBody GenderDTO gender) {
        GenderDTO genderCreated = genderService.save(gender);
        return ResponseEntity.status(HttpStatus.CREATED).body(genderCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenderDTO> getById(@PathVariable Long id) {
        GenderDTO genderDTO = genderService.getById(id);
        return ResponseEntity.ok().body(genderDTO);
    }
}

