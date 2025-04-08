package com.github.adminfaces.starter.repository;

import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.Bulletin;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.model.Eleve;
import com.github.adminfaces.starter.model.Examen;
import com.github.adminfaces.starter.model.Note;
import com.github.adminfaces.starter.model.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EleveRepository extends JpaRepository<Eleve, Integer> {
    @Query("SELECT e FROM Eleve e where e.anneeAcademique=:annee and e.nom =:nomComplet")
    Optional<Eleve> eleveParAnneeEtNomComplet(AnneeAcademique anneeAcademique, String nomComplet);

    @Query("SELECT ce FROM Eleve ce where ce.classe =:classe order by ce.nom, ce.prenom ")
    List<Eleve> listeParClasse(Classe classe);

    @Query("SELECT n.eleve FROM Note n WHERE n.examen =: examen ")
    List<Eleve> listeParExamen(Examen examen);

    @Query("SELECT count(e) FROM Eleve e where e.anneeAcademique=:annee")
    Long nombreEleve(AnneeAcademique anneeAcademique);
    
    @Query("SELECT count(e) FROM Eleve e where e.anneeAcademique=:annee and e.sexe =:sexe")
    Long nombreEleveParSexe(AnneeAcademique anneeAcademique, String sexe);
    
    @Query("SELECT count(e) FROM Eleve e where e.anneeAcademique=?1 and e.classe.niveau.code =?2")
    Long compterElevesParNiveau(AnneeAcademique anneeAcademique, String niveau);
    
    @Query("SELECT count(e) FROM Eleve e where e.anneeAcademique=?1 and e.classe.niveau.code =?2 and e.sexe=?3")
    Long nombreEleveParNiveauSexe(AnneeAcademique anneeAcademique, String niveau, String sexe);
    
    @Query("SELECT e FROM Eleve e")
    List<Eleve> liste();

    @Query("SELECT n FROM Note n WHERE n.eleve not in (SELECT e FROM Eleve e)")
    List<Note> notesAyantEleveSupprime();

    @Query("SELECT r FROM Resultat r WHERE r.eleve not in (SELECT e FROM Eleve e)")
    List<Resultat> resultatsAyantEleveSupprime();

    Optional<Eleve> findEleveByCode(String code);
}
