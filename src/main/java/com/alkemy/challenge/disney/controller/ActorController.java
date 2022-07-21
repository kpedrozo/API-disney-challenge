package com.alkemy.challenge.disney.controller;

import com.alkemy.challenge.disney.dto.ActorDTO;
import com.alkemy.challenge.disney.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("characters")
public class ActorController {

    @Autowired
    private ActorService actorService;


    @GetMapping("/{id}")
    public ResponseEntity<ActorDTO> getDetailsByID(@PathVariable Long id) {
        ActorDTO actor = actorService.getDetailsByID(id);
        return ResponseEntity.ok(actor);
    }


    @PostMapping
    public ResponseEntity<ActorDTO> save(@RequestBody ActorDTO actor) {
        ActorDTO actorCreated = actorService.save(actor);
        return ResponseEntity.status(HttpStatus.CREATED).body(actorCreated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ActorDTO> update(@RequestBody ActorDTO actor, @PathVariable ("id") Long id) {
        ActorDTO actorUpdated = actorService.update(actor, id);
        return ResponseEntity.ok(actorUpdated);
    }

    @GetMapping()
    public ResponseEntity<List<ActorDTO>> getDetailsByFilters (
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String age,
            @RequestParam(required = false) String weight,
            @RequestParam(required = false) Set<Long> movies,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<ActorDTO> actors = actorService.getByFilters(name, age, weight, movies, order);
        return ResponseEntity.ok(actors);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        actorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
