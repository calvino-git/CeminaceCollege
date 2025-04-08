package com.github.adminfaces.starter.repository;

import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.Eleve;
import com.github.adminfaces.starter.model.Examen;
import com.github.adminfaces.starter.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

    @Query("SELECT n FROM Note n")
    List<Note> liste();

    @Query("SELECT n FROM Note n WHERE n.examen =:examen ")
    List<Note> notesParExamen(Examen examen);

    @Query("SELECT count(n) FROM Note n WHERE n.examen =:examen ")
    Long nombreEleveParExamen(Examen examen);

    @Query("SELECT count(n) FROM Note n WHERE n.examen =:examen and n.presence='PRESENT'")
    Long nombreElevePresentParExamen(Examen examen);

    @Query("SELECT count(n) FROM Note n WHERE n.examen =:examen and n.presence='ABSENT'")
    Long nombreEleveAbsentParExamen(Examen examen);

    @Query("SELECT count(n) FROM Note n WHERE n.examen =:examen and n.presence='MALADE'")
    Long nombreEleveMaladeParExamen(Examen examen);

    @Query("SELECT n FROM Note n WHERE n.examen =:examen and n.anneeAcademique =:anneeAcademique")
    List<Note> listeParExamen(Examen examen, AnneeAcademique anneeAcademique);

    @Query("SELECT n FROM Note n WHERE n.examen =:examen and n.eleve=:eleve")
    List<Note> listeParExamenEleve(Examen examen, Eleve eleve);

    @Query("SELECT n FROM Note n WHERE n.examen =:examen and n.eleve=:eleve")
    Optional<Note> noteParExamenEleve(Examen examen, Eleve eleve);

}
