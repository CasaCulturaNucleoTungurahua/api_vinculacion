package com.casaculturatungurahua.api.repository;

import com.casaculturatungurahua.api.entities.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
}
