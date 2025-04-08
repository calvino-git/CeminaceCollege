/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.repository.ClasseRepository;

import java.util.List;

import static com.github.adminfaces.template.util.Assert.has;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author rmpestano
 */
@Service
public class ClasseService {

    @Autowired
    protected ClasseRepository classeRepository;//you can create repositories to extract complex queries from your service

    public Optional<Classe> findClasseByAnneeAcademiqueAndLibelle(AnneeAcademique anneeAcademique, String query) {
        return classeRepository.findClasseByAnneeAcademiqueAndLibelle(anneeAcademique, query);
    }

    public List<Classe> findClassseByAnneeAcademiqueAndLibelleLike(AnneeAcademique anneeAcademique, String query) {
        return classeRepository.findClassesByAnneeAcademiqueAndLibelleLike(anneeAcademique, query);
    }

    public List<Classe> findClasseByAnneeAcademiqueAndCode(AnneeAcademique anneeAcademique, String code) {
        return classeRepository.findClasseByAnneeAcademiqueAndCode(anneeAcademique, code);
    }

//    @Override
//    protected Criteria<Classe, Classe> configRestrictions(Filter<Classe> filter) {
//        Criteria<Classe, Classe> criteria = criteria();
//        if (has(filter.getEntity())) {
//            if (filter.getEntity().hasNiveau()) {
//                criteria.eq(Classe_.niveau, filter.getEntity().getNiveau());
//            }
//            if (has(filter.getEntity().getAnneeAcademique())) {
//                criteria.eq(Classe_.anneeAcademique, filter.getEntity().getAnneeAcademique());
//            }
//        }
//        return criteria;
//    }

    //    @Override
//    public void beforeInsert(Classe classe) {
//        validate(classe);
//    }
//
//    //    @Override
//    public void beforeUpdate(Classe classe) {
//        validate(classe);
//    }
//
//    public void validate(Classe classe) {
//        BusinessException be = new BusinessException();
//        if (!has(classe.getCode())) {
//            be.addException(new BusinessException("Le code est obligatoire."));
//        }
//        if (!has(classe.getLibelle())) {
//            be.addException(new BusinessException("Le libellé est obligatoire."));
//        }
//        if (!has(classe.getCycle())) {
//            be.addException(new BusinessException("Le cycle est obligatoire."));
//        }
//        if (!has(classe.getNiveau())) {
//            be.addException(new BusinessException("Le niveau d'étude est obligatoire."));
//        }

//        if (count(criteria()
//                .eqIgnoreCase(Classe_.code, classe.getCode())
//                .eqIgnoreCase(Classe_.libelle, classe.getLibelle())
//                .eq(Classe_.anneeAcademique, classe.getAnneeAcademique())
//                .notEq(Classe_.id, classe.getId())) > 0) {
//
//            be.addException(new BusinessException("La classe " + classe.getCode()
//                    + ": " + classe.getLibelle() + " au " + classe.getCycle() + "  existe déjà."));
//        }

//        if (has(be.getExceptionList())) {
//            throw be;
//        }
//    }

//    public List<Classe> listeParAnnee(Classe classe) {
//        List<Classe> list = classeRepository.findByLike(classe, Classe_.anneeAcademique, Classe_.libelle);
//        return list;
//    }

//    public List<Classe> liste() {
//        Filter<Classe> filter = new Filter<>();
//        filter.setFirst(0).setPageSize(count().intValue());
//        return paginate(filter);
//    }

//    public List<Classe> listeParCycle(String cycle) {
//        return criteria()
//                .likeIgnoreCase(Classe_.cycle, cycle)
//                .getResultList();
//    }

//    public List<Classe> listeParNiveau(Niveau niveau) {
//        return criteria()
//                .eq(Classe_.niveau, niveau)
//                .getResultList();
//    }

//    public List<Niveau> listeNiveau() {
//        return criteria()
//                .select(Niveau.class, attribute(Classe_.niveau))
//                .distinct()
//                .getResultList();
//    }

}
