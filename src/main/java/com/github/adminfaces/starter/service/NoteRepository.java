package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.Eleve;
import com.github.adminfaces.starter.model.Examen;
import com.github.adminfaces.starter.model.Note;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends EntityRepository<Note, Integer> {

    @Query("SELECT n FROM Note n")
    List<Note> liste();

    @Query("SELECT n FROM Note n WHERE n.examen =:examen ")
    List<Note> notesParExamen(@QueryParam("examen") Examen examen);

    @Query("SELECT count(n) FROM Note n WHERE n.examen =:examen ")
    Long nombreEleveParExamen(@QueryParam("examen") Examen examen);

    @Query("SELECT count(n) FROM Note n WHERE n.examen =:examen and n.presence='PRESENT'")
    Long nombreElevePresentParExamen(@QueryParam("examen") Examen examen);

    @Query("SELECT count(n) FROM Note n WHERE n.examen =:examen and n.presence='ABSENT'")
    Long nombreEleveAbsentParExamen(@QueryParam("examen") Examen examen);

    @Query("SELECT count(n) FROM Note n WHERE n.examen =:examen and n.presence='MALADE'")
    Long nombreEleveMaladeParExamen(@QueryParam("examen") Examen examen);

    @Query("SELECT n FROM Note n WHERE n.examen =:examen and n.anneeAcademique =:anneeAcademique")
    List<Note> listeParExamen(@QueryParam("examen") Examen examen, @QueryParam("annee") AnneeAcademique anneeAcademique);

    @Query("SELECT n FROM Note n WHERE n.examen =:examen and n.eleve=:eleve")
    List<Note> listeParExamenEleve(@QueryParam("examen") Examen examen, @QueryParam("eleve") Eleve eleve);

    @Query("SELECT n FROM Note n WHERE n.examen =:examen and n.eleve=:eleve")
    Optional<Note> noteParExamenEleve(@QueryParam("examen") Examen examen, @QueryParam("eleve") Eleve eleve);

}
