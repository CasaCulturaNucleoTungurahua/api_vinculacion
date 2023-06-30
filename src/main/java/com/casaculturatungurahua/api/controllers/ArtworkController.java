package com.casaculturatungurahua.api.controllers;

import com.casaculturatungurahua.api.entities.Artwork;
import com.casaculturatungurahua.api.services.ArtworkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/artworks")
public class ArtworkController {

    private final ArtworkService artworkService;


    public ArtworkController(ArtworkService artworkService) {
        this.artworkService = artworkService;

    }

    @PostMapping
    public ResponseEntity<Artwork> save(@RequestPart("artwork") final Artwork artwork, @RequestPart("image") MultipartFile image) throws IOException {
        return ResponseEntity.ok(artworkService.save(artwork, image));
    }

    @PutMapping(value = "/{code}")
    public ResponseEntity<Artwork> update(@PathVariable final String code, @RequestPart("artwork") final Artwork artwork, @RequestPart("image") MultipartFile image) throws IOException {
        return ResponseEntity.ok(artworkService.update(code, artwork, image));
    }

    @DeleteMapping(value = "/{code}")
    public ResponseEntity<Boolean> delete(@PathVariable final String code) throws IOException {
        return ResponseEntity.ok(artworkService.delete(code));
    }

    @GetMapping
    public ResponseEntity<List<Artwork>> findAll(){
        return ResponseEntity.ok(artworkService.findAll());
    }

    @GetMapping(value = "/{code}")
    public ResponseEntity<Artwork> findById(@PathVariable final String code){
        return ResponseEntity.ok(artworkService.findById(code));
    }

    @GetMapping(value = "/search/{keyword}")
    public ResponseEntity<List<Artwork>> findByKeyword(@PathVariable final String keyword){
        return ResponseEntity.ok(artworkService.findByKeyword(keyword));
    }

    @PostMapping(value = "/featured")
    public ResponseEntity<List<Artwork>> saveSelected(@RequestBody List<Artwork> artworks){
        return ResponseEntity.ok(artworkService.saveFavouritesArtworks(artworks));
    }

    @GetMapping(value = "/featured")
    public ResponseEntity<List<Artwork>> getSelected(){
        return ResponseEntity.ok(artworkService.findAllFavourites());
    }
}
