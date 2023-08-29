package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.Bulletin;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.model.Eleve;
import com.github.adminfaces.starter.model.Examen;
import com.github.adminfaces.starter.model.Note;
import com.github.adminfaces.starter.model.Resultat;
import java.util.List;
import java.util.Optional;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface EleveRepository extends EntityRepository<Eleve, Integer> {
    @Query("SELECT e FROM Eleve e where e.anneeAcademique=:annee and e.nom =:nomComplet")
    Optional<Eleve> eleveParAnneeEtNomComplet(@QueryParam("annee") AnneeAcademique anneeAcademique, @QueryParam("nomComplet") String nomComplet);

    @Query("SELECT ce FROM Eleve ce where ce.classe =:classe order by ce.nom, ce.prenom ")
    List<Eleve> listeParClasse(@QueryParam("classe") Classe classe);

    @Query("SELECT n.eleve FROM Note n WHERE n.examen =: examen ")
    List<Eleve> listeParExamen(@QueryParam("examen") Examen examen);

    @Query("SELECT count(e) FROM Eleve e where e.anneeAcademique=:annee")
    Long nombreEleve(@QueryParam("annee") AnneeAcademique anneeAcademique);
    
    @Query("SELECT count(e) FROM Eleve e where e.anneeAcademique=:annee and e.sexe =:sexe")
    Long nombreEleveParSexe(@QueryParam("annee") AnneeAcademique anneeAcademique, @QueryParam("sexe") String sexe);
    
    @Query("SELECT count(e) FROM Eleve e where e.anneeAcademique=:annee and e.classe.niveau.titre =:niveau")
    Long nombreEleveParNiveau(@QueryParam("annee") AnneeAcademique anneeAcademique,@QueryParam("niveau") String niveau);
    
    @Query("SELECT count(e) FROM Eleve e where e.anneeAcademique=:annee and e.classe.niveau.titre =:niveau and e.sexe=:sexe")
    Long nombreEleveParNiveauSexe(@QueryParam("annee") AnneeAcademique anneeAcademique, @QueryParam("niveau") String niveau, @QueryParam("sexe") String sexe);
    
    @Query("SELECT e FROM Eleve e")
    List<Eleve> liste();

    @Query("SELECT b FROM Bulletin b WHERE b.eleve not in (SELECT e FROM Eleve e)")
    List<Bulletin> bulletinsAyantEleveSupprime();

    @Query("SELECT n FROM Note n WHERE n.eleve not in (SELECT e FROM Eleve e)")
    List<Note> notesAyantEleveSupprime();

    @Query("SELECT r FROM Resultat r WHERE r.eleve not in (SELECT e FROM Eleve e)")
    List<Resultat> resultatsAyantEleveSupprime();
}
