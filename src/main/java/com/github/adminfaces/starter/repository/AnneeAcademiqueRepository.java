package com.github.adminfaces.starter.repository;

import com.github.adminfaces.starter.model.AnneeAcademique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnneeAcademiqueRepository extends JpaRepository<AnneeAcademique, Integer> {
    Optional<AnneeAcademique> findAnneeAcademiqueByStatutIsTrue();
}
