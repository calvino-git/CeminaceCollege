package com.github.adminfaces.starter.repository;

import com.github.adminfaces.starter.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Integer> {
    @Query("SELECT m FROM Matiere m where m.specialite =:specialite")
    List<Matiere> liste(String specialite);

    @Query("SELECT m FROM Matiere m")
    List<Matiere> liste();

    Optional<Matiere> findMatiereByCode(String code);
}
