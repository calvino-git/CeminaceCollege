package com.github.adminfaces.starter.repository;

import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.BilanParSpecialiteCultureCollege;
import com.github.adminfaces.starter.model.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BscCollegeRepository extends JpaRepository<BilanParSpecialiteCultureCollege, Integer> {

    Optional<BilanParSpecialiteCultureCollege> findBilanParSpecialiteCultureCollegeByEleveAndTrimestreAndAnneeAcademique(Eleve eleve, Integer trimestre, AnneeAcademique anneeAcademique);

}
