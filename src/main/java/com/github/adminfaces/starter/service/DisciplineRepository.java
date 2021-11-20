package com.github.adminfaces.starter.service;

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
    @Query("SELECT d FROM Discipline d")
    List<Discipline> liste();
    
    @Query("SELECT d FROM Discipline d where d.classe =:classe")
    List<Discipline> listeByClasse(@QueryParam("classe") Classe classe);
    
    @Query("SELECT d FROM Discipline d where d.matiere =:matiere")
    List<Discipline> listeByMatiere(@QueryParam("matiere") Matiere matiere);

    @Query("SELECT d FROM Discipline d where d.enseignant =:enseignant")
    List<Discipline> listeByEnseignant(@QueryParam("Enseignant") String enseignant);
    
    @Query("SELECT d FROM Discipline d where d.matiere =:matiere and d.classe =:classe")
    Optional<Discipline> disciplineParMatiereEtClasse(@QueryParam("matiere") Matiere matiere,@QueryParam("classe") Classe classe);

}
