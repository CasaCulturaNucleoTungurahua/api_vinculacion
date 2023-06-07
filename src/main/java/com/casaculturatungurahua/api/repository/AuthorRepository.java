package com.casaculturatungurahua.api.repository;

import com.casaculturatungurahua.api.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    boolean existsByFullName(String fullName);

}
