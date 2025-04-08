package com.github.adminfaces.starter.repository;

import com.github.adminfaces.starter.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BscLyceeRepository extends JpaRepository<BilanParSpecialiteCultureLycee, Integer> {

    Optional<BilanParSpecialiteCultureLycee> findBilanParSpecialiteCultureLyceeByEleveAndTrimestreAndAnneeAcademique(Eleve eleve, Integer trimestre, AnneeAcademique anneeAcademique);

}
