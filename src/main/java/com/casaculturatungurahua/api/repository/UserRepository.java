package com.casaculturatungurahua.api.repository;

import com.casaculturatungurahua.api.entities.MainUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MainUser, Long> {
    Optional<MainUser> findByUsername(String username);
    boolean existsByUsername(String username);
}
