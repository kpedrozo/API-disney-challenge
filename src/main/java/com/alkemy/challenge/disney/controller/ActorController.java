package com.alkemy.challenge.disney.controller;

import com.alkemy.challenge.disney.dto.ActorBasicDTO;
import com.alkemy.challenge.disney.dto.ActorDTO;
import com.alkemy.challenge.disney.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


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

    //HACER UPTADE UTILIZANDO PATCH !!! (LA DIFERENCIA CON PUT ES QUE :
    // PATCH permite actualizar algunos valores sin reemplazar el objeto.
    // put reemplaza el objeto.

    @PatchMapping("/{id}")
    public ResponseEntity<ActorDTO> update(@RequestBody ActorBasicDTO actor, @PathVariable ("id") Long id) {
        ActorDTO actorUpdated = actorService.update(actor, id);
        return ResponseEntity.ok(actorUpdated);
    }

    @GetMapping("/characters")
    public ResponseEntity<List<ActorDTO>> getDetailsByFilters (
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String age,
            @RequestParam(required = false) String weight,
            @RequestParam(required = false) Set<Long> films,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<ActorDTO> actors = actorService.getByFilters(name, age, weight, films, order);
        return ResponseEntity.ok(actors);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        actorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
