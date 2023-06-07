package com.casaculturatungurahua.api.services;

import com.casaculturatungurahua.api.entities.Author;
import com.casaculturatungurahua.api.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author save(Author author){
        if(authorRepository.existsByFullName(author.getFullName())){
            return null;
        }
        return authorRepository.save(author);
    }
}
