/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.persistence.model.Filter;
import com.github.adminfaces.persistence.service.CrudService;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.model.Resultat;
import com.github.adminfaces.starter.model.Resultat_;
import com.github.adminfaces.starter.model.Eleve;
import com.github.adminfaces.template.exception.BusinessException;
import org.apache.deltaspike.data.api.criteria.Criteria;

import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.List;

import static com.github.adminfaces.template.util.Assert.has;
import javax.inject.Inject;

/**
 * @author rmpestano
 */
@Stateless
public class ResultatService extends CrudService<Resultat, Integer> implements Serializable {
    @Inject
    EleveRepository eleveRepository;
    @Inject
    ClasseRepository classeRepository;

    @Override
    protected Criteria<Resultat, Resultat> configRestrictions(Filter<Resultat> filter) {

        Criteria<Resultat, Resultat> criteria = criteria();

        //create restrictions based on parameters map
//        if (filter.hasParam("id")) {
//            criteria.eq(Resultat_.id, filter.getIntParam("id"));
//        }
//
//        if (filter.hasParam("minPrice") && filter.hasParam("maxPrice")) {
//            criteria.between(Resultat_.price, filter.getDoubleParam("minPrice"), filter.getDoubleParam("maxPrice"));
//        } else if (filter.hasParam("minPrice")) {
//            criteria.gtOrEq(Resultat_.price, filter.getDoubleParam("minPrice"));
//        } else if (filter.hasParam("maxPrice")) {
//            criteria.ltOrEq(Resultat_.price, filter.getDoubleParam("maxPrice"));
//        }
//
//        //create restrictions based on filter entity
//        if (has(filter.getEntity())) {
//            Resultat filterEntity = filter.getEntity();
//            if (has(filterEntity.getModel())) {
//                criteria.likeIgnoreCase(Resultat_.model, "%" + filterEntity.getModel());
//            }
//
//            if (has(filterEntity.getPrice())) {
//                criteria.eq(Resultat_.price, filterEntity.getPrice());
//            }
//
//            if (has(filterEntity.getName())) {
//                criteria.likeIgnoreCase(Resultat_.name, "%" + filterEntity.getName() + "%");
//            }
//        }
        return criteria;
    }

    @Override
    public void beforeInsert(Resultat resultat) {
        validate(resultat);
    }

    @Override
    public void beforeUpdate(Resultat resultat) {
        validate(resultat);
    }

    public void validate(Resultat resultat) {
        BusinessException be = new BusinessException();
//        if (!resultat.hasModel()) {
//            be.addException(new BusinessException("Resultat model cannot be empty"));
//        }
//        if (!resultat.hasName()) {
//            be.addException(new BusinessException("Resultat name cannot be empty"));
//        }
//
//        if (!has(resultat.getPrice())) {
//            be.addException(new BusinessException("Resultat price cannot be empty"));
//        }
//
//        if (count(criteria()
//                .eqIgnoreCase(Resultat_.name, resultat.getName())
//                .notEq(Resultat_.id, resultat.getId())) > 0) {
//
//            be.addException(new BusinessException("Resultat name must be unique"));
//        }

        if (has(be.getExceptionList())) {
            throw be;
        }
    }

    public List<Resultat> listByEleve(Eleve eleve) {
        return criteria()
                .eq(Resultat_.eleve, eleve)
                .getResultList();
    }

    public List<Resultat> resultatParClasseEtTrimestre(Classe classe, Integer trimestre) {
        return criteria()
                .eq(Resultat_.classe, classe)
                .eq(Resultat_.trimestre, trimestre)
//                .eq(Resultat_.anneeAcademique, anneeAcademique)
                .getResultList();
    }

    public Resultat exists(Classe classe, Integer trimestre, Eleve eleve) {
        Resultat resultat = criteria()
                .eq(Resultat_.classe, classe)
                .eq(Resultat_.trimestre, trimestre)
                .eq(Resultat_.eleve, eleve)
                .getOptionalResult();
        return resultat;
    }

    public List<Resultat> resultatsAyantEleveOrphelin() {
        return eleveRepository.resultatsAyantEleveSupprime();
    }

    public List<Resultat> resultatsAyantClasseOrphelin() {
        return classeRepository.resultatsAyantClasseOrphelin();
    }
}
