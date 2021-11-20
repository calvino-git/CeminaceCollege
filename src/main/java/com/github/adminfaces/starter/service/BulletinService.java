/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.persistence.model.Filter;
import com.github.adminfaces.persistence.service.CrudService;
import com.github.adminfaces.starter.model.Bulletin;
import com.github.adminfaces.starter.model.Bulletin_;
import com.github.adminfaces.starter.model.Discipline;
import com.github.adminfaces.starter.model.Eleve;
import com.github.adminfaces.template.exception.BusinessException;
import org.apache.deltaspike.data.api.criteria.Criteria;

import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.List;

import static com.github.adminfaces.template.util.Assert.has;
import java.util.Optional;

/**
 * @author rmpestano
 */
@Stateless
public class BulletinService extends CrudService<Bulletin, Integer> implements Serializable {

    @Override
    protected Criteria<Bulletin, Bulletin> configRestrictions(Filter<Bulletin> filter) {

        Criteria<Bulletin, Bulletin> criteria = criteria();

        //create restrictions based on parameters map
//        if (filter.hasParam("id")) {
//            criteria.eq(Bulletin_.id, filter.getIntParam("id"));
//        }
//
//        if (filter.hasParam("minPrice") && filter.hasParam("maxPrice")) {
//            criteria.between(Bulletin_.price, filter.getDoubleParam("minPrice"), filter.getDoubleParam("maxPrice"));
//        } else if (filter.hasParam("minPrice")) {
//            criteria.gtOrEq(Bulletin_.price, filter.getDoubleParam("minPrice"));
//        } else if (filter.hasParam("maxPrice")) {
//            criteria.ltOrEq(Bulletin_.price, filter.getDoubleParam("maxPrice"));
//        }
//
//        //create restrictions based on filter entity
//        if (has(filter.getEntity())) {
//            Bulletin filterEntity = filter.getEntity();
//            if (has(filterEntity.getModel())) {
//                criteria.likeIgnoreCase(Bulletin_.model, "%" + filterEntity.getModel());
//            }
//
//            if (has(filterEntity.getPrice())) {
//                criteria.eq(Bulletin_.price, filterEntity.getPrice());
//            }
//
//            if (has(filterEntity.getName())) {
//                criteria.likeIgnoreCase(Bulletin_.name, "%" + filterEntity.getName() + "%");
//            }
//        }
        return criteria;
    }

    @Override
    public void beforeInsert(Bulletin bulletin) {
        validate(bulletin);
    }

    @Override
    public void beforeUpdate(Bulletin bulletin) {
        validate(bulletin);
    }

    public void validate(Bulletin bulletin) {
        BusinessException be = new BusinessException();
//        if (!bulletin.hasModel()) {
//            be.addException(new BusinessException("Bulletin model cannot be empty"));
//        }
//        if (!bulletin.hasName()) {
//            be.addException(new BusinessException("Bulletin name cannot be empty"));
//        }
//
//        if (!has(bulletin.getPrice())) {
//            be.addException(new BusinessException("Bulletin price cannot be empty"));
//        }
//
//        if (count(criteria()
//                .eqIgnoreCase(Bulletin_.name, bulletin.getName())
//                .notEq(Bulletin_.id, bulletin.getId())) > 0) {
//
//            be.addException(new BusinessException("Bulletin name must be unique"));
//        }

        if (has(be.getExceptionList())) {
            throw be;
        }
    }

    public List<Bulletin> listByEleve(Eleve eleve) {
        return criteria()
                .eq(Bulletin_.eleve, eleve)
                .getResultList();
    }

    public List<Bulletin> bulletinParEleveEtClasse(Eleve eleve, Integer trimestre, Integer annee) {
        return criteria()
                .eq(Bulletin_.eleve, eleve)
                .eq(Bulletin_.trimestre, trimestre)
                .eq(Bulletin_.annee, annee)
                .orderAsc(Bulletin_.eleve)
                .getResultList();
    }
    
    public Bulletin exists(Eleve eleve, Integer trimestre, Integer annee,Discipline discipline) {
        Bulletin bulletin = criteria()
                .eq(Bulletin_.eleve, eleve)
                .eq(Bulletin_.trimestre, trimestre)
                .eq(Bulletin_.annee, annee)
                .eq(Bulletin_.discipline, discipline)
                .getOptionalResult();
        return bulletin ;
    }

}
