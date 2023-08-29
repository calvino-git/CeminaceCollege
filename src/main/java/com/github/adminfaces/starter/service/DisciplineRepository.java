package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.model.Discipline;
import com.github.adminfaces.starter.model.Matiere;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplineRepository extends EntityRepository<Discipline,Integer> {
    @Query("SELECT d FROM Discipline d where d.anneeAcademique =:annee")
    List<Discipline> disciplines(@QueryParam("annee") AnneeAcademique anneeAcademique);
    
    @Query("SELECT d FROM Discipline d where d.classe =:classe")
    List<Discipline> disciplinesParClasse(@QueryParam("classe") Classe classe);
    
    @Query("SELECT d FROM Discipline d where d.anneeAcademique =:annee and d.matiere =:matiere")
    List<Discipline> disciplinesParMatiere(@QueryParam("annee") AnneeAcademique anneeAcademique, @QueryParam("matiere") Matiere matiere);

    @Query("SELECT d FROM Discipline d where d.anneeAcademique =:annee and d.enseignant =:enseignant")
    List<Discipline> disciplinesParEnseignant(@QueryParam("annee") AnneeAcademique anneeAcademique, @QueryParam("Enseignant") String enseignant);
    
    @Query("SELECT d FROM Discipline d where d.matiere =:matiere and d.classe =:classe")
    Optional<Discipline> disciplineParMatiereEtClasse(@QueryParam("matiere") Matiere matiere,@QueryParam("classe") Classe classe);
    
    @Query("SELECT d FROM Discipline d where d.anneeAcademique =:annee and (d.matiere.libelle like :query or d.classe.libelle like :query)")
    List<Discipline> disciplinesParMatiereOuClasse(@QueryParam("annee") AnneeAcademique anneeAcademique, @QueryParam("query") String query);

}