/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.model.Discipline;
import com.github.adminfaces.starter.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.github.adminfaces.template.util.Assert.has;

import java.util.Optional;

@Service
public class DisciplineService {

    @Autowired
    protected DisciplineRepository disciplineRepository;//you can create repositories to extract complex queries from your service

    public Optional<Discipline> findByCode(String code) {
        return disciplineRepository.findDisciplineByCode(code);
    }


//    @Override
//    protected Criteria<Discipline, Discipline> configRestrictions(Filter<Discipline> filter) {
//        Criteria<Discipline, Discipline> criteria = criteria();
//        if (has(filter.getEntity())) {
//            if (filter.getEntity().hasMatiere()) {
//                criteria.eq(Discipline_.matiere, filter.getEntity().getMatiere());
//            }
//            if (filter.getEntity().hasClasse()) {
//                criteria.eq(Discipline_.classe, filter.getEntity().getClasse());
//            }
//            if (has(filter.getEntity().getAnneeAcademique())) {
//                criteria.eq(Discipline_.anneeAcademique, filter.getEntity().getAnneeAcademique());
//            }
//        }
//        return criteria;
//    }
//
//    @Override
//    public void beforeInsert(Discipline discipline) {
//        discipline.setAnneeAcademique(discipline.getClasse().getAnneeAcademique());
//        validate(discipline);
//    }
//
//    @Override
//    public void beforeUpdate(Discipline discipline) {
//        validate(discipline);
//    }
//
//    public void validate(Discipline discipline) {
//        BusinessException be = new BusinessException();
//        if (!discipline.hasMatiere()) {
//            be.addException(new BusinessException("Matière est obligatoire."));
//        }
//        if (!discipline.hasEnseignant()) {
//            be.addException(new BusinessException("Enseignant est obligatoire."));
//        }
//        if (!discipline.hasClasse()) {
//            be.addException(new BusinessException("Classe est obligatoire."));
//        }
//        if (count(criteria()
//                .eq(Discipline_.matiere, discipline.getMatiere())
//                .eq(Discipline_.classe, discipline.getClasse())
//                .notEq(Discipline_.id, discipline.getId())) > 0) {
//
//            be.addException(new BusinessException("La discipline " + discipline.getMatiere().getLibelle()
//                    + " de la classe " + discipline.getClasse().getLibelle() + "  existe déjà."));
//        }
//
//        if (has(be.getExceptionList())) {
//            throw be;
//        }
//    }

//    public List<Discipline> liste(AnneeAcademique anneeAcademique) {
//        List<Discipline> list = criteria()
//                .eq(Discipline_.anneeAcademique, anneeAcademique)
//                .distinct()
//                .getResultList();
//        return list;
//    }
//
//    public Optional<Discipline> disciplineParMatiereEtClasse(Classe classe, Matiere matiere) {
//        return repo.disciplineParMatiereEtClasse(matiere, classe);
//    }
//
//    public List<Discipline> disciplinesParMatiereOuClasse(AnneeAcademique anneeAcademique, String query) {
//        List<Discipline> result = repo.disciplinesParMatiereOuClasse(anneeAcademique, "%" + query + "%");
//        return result;
//    }
//
//    public List<Discipline> disciplinesParClasse(Classe c) {
//        List<Discipline> result = repo.disciplinesParClasse(c);
//        return result;
//    }
}
