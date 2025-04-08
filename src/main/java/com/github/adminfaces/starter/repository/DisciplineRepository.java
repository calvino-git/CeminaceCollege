package com.github.adminfaces.starter.repository;

import com.github.adminfaces.starter.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {
    List<Discipline> findDisciplinesByAnneeAcademique(AnneeAcademique anneeAcademique);
    
    List<Discipline> findDisciplinesByClasse(Classe classe);
    
    List<Discipline> findDisciplinesByAnneeAcademiqueAndMatiere(AnneeAcademique anneeAcademique, Matiere matiere);

    List<Discipline> findDisciplinesByAnneeAcademiqueAndEnseignant(AnneeAcademique anneeAcademique, String enseignant);
    
    Optional<Discipline> findDisciplineByMatiereAndClasse(Matiere matiere, Classe classe);
    
    List<Discipline> findDisciplinesByAnneeAcademiqueAndMatiere_LibelleOrClasse_Libelle(AnneeAcademique anneeAcademique, String matiere, String classe);

    @Query("SELECT b FROM Bulletin b WHERE b.discipline not in (SELECT d FROM Discipline d)")
    List<Bulletin> bulletinAyantDisciplineOrphelin();

    Optional<Discipline> findDisciplineByCode(String code);
}