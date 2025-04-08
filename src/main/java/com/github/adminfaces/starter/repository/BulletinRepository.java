package com.github.adminfaces.starter.repository;

import com.github.adminfaces.starter.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BulletinRepository extends JpaRepository<Bulletin, Integer> {
    Long countBulletinsByAnneeAcademiqueAndTrimestreAndEleveAndDiscipline(AnneeAcademique anneeAcademique, int trimestre, Eleve eleve, Discipline discipline);

    List<Bulletin> findBulletinsByEleve(Eleve eleve);

    List<Bulletin> findBulletinsByEleveAndTrimestre(Eleve eleve, Integer trimestre);

    @Query("SELECT b FROM Bulletin b WHERE b.eleve not in (SELECT e FROM Eleve e)")
    List<Bulletin> bulletinsAyantEleveSupprime();

    List<Bulletin> findBulletinsByAnneeAcademique(AnneeAcademique anneeAcademique);

    List<Bulletin> findBulletinsByEleveAndTrimestreAndDiscipline(Eleve eleve, Integer trimestre, Discipline discipline);

    List<Bulletin> findBulletinsByEleveAndTrimestreAndAnneeAcademique(Eleve eleve, Integer trimestre, AnneeAcademique anneeAcademique);
}
