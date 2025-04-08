package com.github.adminfaces.starter.repository;

import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.model.Niveau;
import com.github.adminfaces.starter.model.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Integer> {
    List<Classe> findClasseByAnneeAcademique(AnneeAcademique anneeAcademique);
    
    List<Classe> findClasseByNiveau(Niveau niveau);

    List<Classe> findClasseByLibelleContainingIgnoreCase(String query);
    List<Classe> findClassesByAnneeAcademiqueAndLibelleLike(AnneeAcademique anneeAcademique, String query);

    Optional<Classe> findClasseByAnneeAcademiqueAndLibelle(AnneeAcademique anneeAcademique, String query);

    List<Classe> findClasseByAnneeAcademiqueAndCode(AnneeAcademique anneeAcademique, String code);

    @Query("SELECT r FROM Resultat r where r.classe not in (select c from Classe c)")
    List<Resultat> resultatsAyantClasseOrphelin();
}
