/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.model.Discipline;
import com.github.adminfaces.starter.model.Examen;
import com.github.adminfaces.starter.repository.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.github.adminfaces.template.util.Assert.has;

import java.util.List;
import java.util.Optional;

/**
 * @author rmpestano
 */
@Service
public class ExamenService{

    private final ExamenRepository examenRepository;

    public ExamenService(ExamenRepository examenRepository) {
        this.examenRepository = examenRepository;
    }

    public Optional<Examen> findByCode(String code) {
        return examenRepository.findExamenByCode(code);
    }

    public List<Examen> findByDisciplineAndTrimestreAndAnneeAcademiqueAndType(Discipline discipline, Integer trimestre, AnneeAcademique anneeAcademique, String type) {
        return examenRepository.findExamensByDisciplineAndTrimestreAndAnneeAcademiqueAndType(discipline, trimestre, anneeAcademique, type);
    }

//    @Override
//    protected Criteria<Examen, Examen> configRestrictions(Filter<Examen> filter) {
//        Criteria<Examen, Examen> criteria = criteria();
//        Criteria<Discipline, Discipline> criteriaDiscipline = criteria(Discipline.class);
//
//        if (has(filter.hasParam("type"))) {
//            criteria.eq(Examen_.type, filter.getStringParam("type"));
//        }
//        if (has(filter.hasParam("trimestre"))) {
//            criteria.eq(Examen_.trimestre, filter.getIntParam("trimestre"));
//        }
//        if (has(filter.hasParam("discipline"))) {
//            criteria.eq(Examen_.discipline, filter.getParam("discipline", Discipline.class));
//        }
//        if (filter.hasParam("classe")) {
//            criteriaDiscipline.eq(Discipline_.classe, filter.getParam("classe", Classe.class));
//            criteria.join(Examen_.discipline, criteriaDiscipline);
//        }
//
//        if (has(filter.getEntity())) {
//            if (has(filter.getEntity().getAnneeAcademique())) {
//                criteria.eq(Examen_.anneeAcademique, filter.getEntity().getAnneeAcademique());
//            }
//        }
//        return criteria;
//    }
//
//    @Override
//    public void beforeInsert(Examen examen) {
////        examen.setAnneeAcademique(examen.getDiscipline().getAnneeAcademique());
//        validate(examen);
//    }
//
//    @Override
//    public void beforeUpdate(Examen examen) {
//        validate(examen);
//    }
//
//    public void validate(Examen examen) {
//        BusinessException be = new BusinessException();
//        if (!examen.hasDiscipline()) {
//            be.addException(new BusinessException("La matière est obligatoire."));
//        }
//        if (!examen.hasDate()) {
//            be.addException(new BusinessException("La date est obligatoire."));
//        }
//        if (!examen.hasTrimestre()) {
//            be.addException(new BusinessException("Trimestre est obligatoire."));
//        }
//        if (!examen.hasType()) {
//            be.addException(new BusinessException("type est obligatoire."));
//        }
//        if (count(criteria()
//                .eq(Examen_.discipline, examen.getDiscipline())
//                .eq(Examen_.trimestre, examen.getTrimestre())
//                .eq(Examen_.type, examen.getType())
//                .eq(Examen_.anneeAcademique, examen.getAnneeAcademique())
//                .notEq(Examen_.id, examen.getId())) > 0) {
//
//
//            be.addException(new BusinessException("Doublon","L'examen de " + examen.getType()
//                    + " de la classe " + examen.getDiscipline().getClasse().getLibelle() + " enseigné par  "
//                    + examen.getDiscipline().getEnseignant() + "  existe déjà."));
//        }
//
//        if (has(be.getExceptionList())) {
//            throw be;
//        }
//    }
//
//    public List<Examen> liste(AnneeAcademique anneeAcademique) {
//        return criteria().eq(Examen_.anneeAcademique, anneeAcademique)
//                .getResultList();
//    }
//
//    public List<Examen> liste() {
//        return repo.liste();
//    }
//
//    public Optional<Examen> examensParClasseMatiereTrimestreType(Classe classe, Matiere matiere, int trimestre, String type) {
//        return repo.listeByClasseByMatiereTrimestreType(classe, matiere, trimestre, type);
//    }
//
//    public List<Examen> examensParClasseTrimestre(Classe classe, Integer trimestre, AnneeAcademique anneeAcademiquee) {
//        return repo.listeByClasseByTrimestre(classe, trimestre);
//    }
//    public List<Examen> examensParClasseMatiereTrimestre(Classe classe, Matiere matiere, int trimestre) {
//        return repo.listeByClasseByMatiereTrimestre(classe, matiere, trimestre);
//    }
//
//    public List<Examen> listeParClasseType(Classe classe, Integer trimestre, AnneeAcademique anneeAcademique, String type) {
//        List<Examen> examens = criteria().eq(Examen_.trimestre, trimestre)
//                .eq(Examen_.anneeAcademique, anneeAcademique)
//                .eq(Examen_.type, type)
//                .getResultList();
//        return examens.stream().filter(e -> e.getDiscipline().getClasse().equals(classe)).collect(Collectors.toList());
//    }
//
//    public List<Examen> listeParDisciplineTrimestreType(Discipline discipline, Integer trimestre, String type) {
//        return criteria().eq(Examen_.discipline, discipline)
//                .eq(Examen_.trimestre, trimestre)
//                .eq(Examen_.type, type)
//                .getResultList();
//    }

}
