package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.model.Discipline;
import com.github.adminfaces.starter.model.Matiere;
import com.github.adminfaces.starter.model.Examen;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import java.util.List;

@Repository
public interface ExamenRepository extends EntityRepository<Examen, Integer> {

    @Query("SELECT e FROM Examen e")
    List<Examen> liste();

    @Query("SELECT e FROM Examen e where e.discipline.classe =:classe")
    List<Examen> listeByClasse(@QueryParam("classe") Classe classe);

    @Query("SELECT e FROM Examen e where e.discipline.classe =:classe and e.discipline.matiere =:matiere and e.trimestre=:trimestre and e.annee=:annee")
    List<Examen> listeByClasseByMatiereTrimestre(@QueryParam("classe") Classe classe, @QueryParam("matiere") Matiere matiere, @QueryParam("trimestre") Integer trimestre, @QueryParam("annee") Integer annee);

    @Query("SELECT e FROM Examen e where e.discipline.classe =:classe and e.trimestre=:trimestre and e.annee=:annee")
    public List<Examen> listeByClasseByTrimestre(@QueryParam("classe") Classe classe, @QueryParam("trimestre") Integer trimestre, @QueryParam("annee")Integer annee);
 
    @Query("SELECT e FROM Examen e where e.discipline.classe =:classe and e.trimestre=:trimestre and e.annee=:annee and e.type=:type")
    public List<Examen> listeByClasseType(@QueryParam("classe") Classe classe, @QueryParam("trimestre") Integer trimestre, @QueryParam("annee")Integer annee, @QueryParam("type") String type);
}
