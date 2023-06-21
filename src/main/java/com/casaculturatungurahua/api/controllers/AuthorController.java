package com.casaculturatungurahua.api.controllers;

import com.casaculturatungurahua.api.entities.Author;
import com.casaculturatungurahua.api.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;

@RestController
@RequestMapping(value="/author")

public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<Author> save(@RequestBody Author author){
        Author authorFromDB = authorService.save(author);
        if(authorFromDB != null){
            return ResponseEntity.ok(authorFromDB);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Author> update(@PathVariable final Long id, @RequestBody final Author author){
        return ResponseEntity.ok(authorService.update(id, author));
    }

    @DeleteMapping
    public ResponseEntity<Map<String,String>> delete(@PathVariable final Long id){
        Map<String, String> response = new HashMap<>();
        String message = authorService.delete(id) ? "Autor eliminado exitosamente" : "Error al eliminar el autor";
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Author>> findAll(){
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping
    public ResponseEntity<Author> findById(Long id){
        return ResponseEntity.ok(authorService.findById(id));
    }

}
