package com.github.adminfaces.starter.repository;

import com.github.adminfaces.starter.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Integer> {

    @Query("SELECT e FROM Examen e")
    List<Examen> liste();

    @Query("SELECT e FROM Examen e where e.discipline.classe =:classe")
    List<Examen> listeByClasse(Classe classe);

    @Query("SELECT e FROM Examen e where e.discipline.classe =:classe and e.discipline.matiere =:matiere and e.trimestre=:trimestre and e.type=:type")
    Optional<Examen> listeByClasseByMatiereTrimestreType(Classe classe, Matiere matiere, Integer trimestre, String type);

    @Query("SELECT e FROM Examen e where e.discipline.classe =:classe and e.discipline.matiere =:matiere and e.trimestre=:trimestre")
    List<Examen> listeByClasseByMatiereTrimestre(Classe classe, Matiere matiere, Integer trimestre);

    
    @Query("SELECT e FROM Examen e where e.discipline.classe =:classe and e.trimestre=:trimestre")
    public List<Examen> listeByClasseByTrimestre(Classe classe, Integer trimestre);
 
    @Query("SELECT e FROM Examen e where e.discipline.classe =:classe and e.trimestre=:trimestre and e.type=:type")
    public List<Examen> listeByClasseType(Classe classe, Integer trimestre, String type);

    Optional<Examen> findExamenByCode(String code);

    List<Examen> findExamensByDisciplineAndTrimestreAndAnneeAcademiqueAndType(Discipline discipline, Integer trimestre, AnneeAcademique anneeAcademique, String type);
}
