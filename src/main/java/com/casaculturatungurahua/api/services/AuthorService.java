package com.casaculturatungurahua.api.services;

import com.casaculturatungurahua.api.entities.Author;
import com.casaculturatungurahua.api.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Author update(Long id, Author author){
        Author authorToUpdate = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
        authorToUpdate.setAddress(author.getAddress());
        authorToUpdate.setBibliography(author.getBibliography());
        authorToUpdate.setFullName(author.getFullName());
        return authorRepository.save(authorToUpdate);
    }

    public boolean delete(Long id){
        if(authorRepository.existsById(id)){
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public Author findById(Long id){
        return authorRepository.findById(id).orElseThrow(()-> new RuntimeException("Author not found"));
    }
}
