package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.model.Niveau;
import com.github.adminfaces.starter.model.Resultat;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClasseRepository extends EntityRepository<Classe,Integer> {
    @Query("SELECT c FROM Classe c")
    List<Classe> liste();
    
    @Query("SELECT c FROM Classe c where c.niveau :niveau")
    List<Classe> liste(@QueryParam("niveau") Niveau niveau);
    
    @Query("SELECT c FROM Classe c where c.anneeAcademique =:anneeAcademique and c.libelle =:query")
    Optional<Classe> classeParAnneeEtLibelle(@QueryParam("anneeAcademique") AnneeAcademique anneeAcademique, @QueryParam("query") String query);
    
    @Query("SELECT c FROM Classe c where c.anneeAcademique =:anneeAcademique and c.code =:code")
    Optional<Classe> classeParAnneeEtCode(@QueryParam("anneeAcademique") AnneeAcademique anneeAcademique, @QueryParam("code") String code);
    @Query("SELECT r FROM Resultat r where r.classe not in (select c from Classe c)")
    List<Resultat> resultatsAyantClasseOrphelin();
}
