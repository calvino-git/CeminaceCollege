package com.github.adminfaces.starter.repository;

import com.github.adminfaces.starter.model.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NiveauRepository extends JpaRepository<Niveau,Integer> {
    Optional<Niveau> findNiveauByCode(String code);
}
