package com.github.adminfaces.starter.repository;

import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.BilanParSpecialiteLettreCollege;
import com.github.adminfaces.starter.model.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BslCollegeRepository extends JpaRepository<BilanParSpecialiteLettreCollege, Integer> {

    Optional<BilanParSpecialiteLettreCollege> findBilanParSpecialiteLettreCollegesByEleveAndTrimestreAndAnneeAcademique(Eleve eleve, Integer trimestre, AnneeAcademique anneeAcademique);

}
