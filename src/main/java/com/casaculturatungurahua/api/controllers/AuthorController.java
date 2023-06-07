package com.casaculturatungurahua.api.controllers;

import com.casaculturatungurahua.api.entities.Author;
import com.casaculturatungurahua.api.services.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
