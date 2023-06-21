package com.casaculturatungurahua.api.controllers;

import com.casaculturatungurahua.api.entities.Artwork;
import com.casaculturatungurahua.api.services.ArtworkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/artworks")
public class ArtworkController {

    private final ArtworkService artworkService;


    public ArtworkController(ArtworkService artworkService) {
        this.artworkService = artworkService;
    }

    @PostMapping
    public ResponseEntity<Artwork> save(@RequestBody final Artwork artwork){
        return ResponseEntity.ok(artworkService.save(artwork));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Artwork> update(@PathVariable final Long id, @RequestBody final Artwork artwork){
        return ResponseEntity.ok(artworkService.update(id, artwork));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Boolean> delete(@PathVariable final Long id){
        return ResponseEntity.ok(artworkService.delete(id));
    }

    @GetMapping
    public ResponseEntity<List<Artwork>> findAll(){
        return ResponseEntity.ok(artworkService.findAll());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Artwork> findById(@PathVariable final Long id){
        return ResponseEntity.ok(artworkService.findById(id));
    }
}
