package com.casaculturatungurahua.api.repository;

import com.casaculturatungurahua.api.entities.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtworkRepository extends JpaRepository<Artwork, String> {

    @Query("select a from  Artwork a join Author  au where a.name like %:keyword% or au.fullName like %:keyword%")
    List<Artwork> findAllByNameOrAuthorName(@Param("keyword") String keyword);
}
