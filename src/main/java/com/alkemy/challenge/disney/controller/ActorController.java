package com.alkemy.challenge.disney.controller;

import com.alkemy.challenge.disney.dto.ActorDTO;
import com.alkemy.challenge.disney.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping
    public ResponseEntity<List<ActorDTO>> getAll() {
        List<ActorDTO> actors = actorService.getAllActors();
        return ResponseEntity.ok().body(actors);
    }

    @PostMapping
    public ResponseEntity<ActorDTO> save(@RequestBody ActorDTO actor) {
        ActorDTO actorCreated = actorService.save(actor);
        return ResponseEntity.status(HttpStatus.CREATED).body(actorCreated);
    }

}
