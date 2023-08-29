/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.persistence.model.Filter;
import com.github.adminfaces.persistence.service.CrudService;
import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.BilanParSpecialiteScience;
import com.github.adminfaces.starter.model.BilanParSpecialiteScience_;
import com.github.adminfaces.starter.model.Eleve;
import com.github.adminfaces.template.exception.BusinessException;
import org.apache.deltaspike.data.api.criteria.Criteria;

import javax.ejb.Stateless;
import java.io.Serializable;

import static com.github.adminfaces.template.util.Assert.has;

/**
 * @author rmpestano
 */
@Stateless
public class BssService extends CrudService<BilanParSpecialiteScience, Integer> implements Serializable {

    @Override
    protected Criteria<BilanParSpecialiteScience, BilanParSpecialiteScience> configRestrictions(Filter<BilanParSpecialiteScience> filter) {

        Criteria<BilanParSpecialiteScience, BilanParSpecialiteScience> criteria = criteria();

        //create restrictions based on parameters map
//        if (filter.hasParam("id")) {
//            criteria.eq(BilanParSpecialiteScience_.id, filter.getIntParam("id"));
//        }
//
//        if (filter.hasParam("minPrice") && filter.hasParam("maxPrice")) {
//            criteria.between(BilanParSpecialiteScience_.price, filter.getDoubleParam("minPrice"), filter.getDoubleParam("maxPrice"));
//        } else if (filter.hasParam("minPrice")) {
//            criteria.gtOrEq(BilanParSpecialiteScience_.price, filter.getDoubleParam("minPrice"));
//        } else if (filter.hasParam("maxPrice")) {
//            criteria.ltOrEq(BilanParSpecialiteScience_.price, filter.getDoubleParam("maxPrice"));
//        }
//
//        //create restrictions based on filter entity
//        if (has(filter.getEntity())) {
//            BilanParSpecialiteScience filterEntity = filter.getEntity();
//            if (has(filterEntity.getModel())) {
//                criteria.likeIgnoreCase(BilanParSpecialiteScience_.model, "%" + filterEntity.getModel());
//            }
//
//            if (has(filterEntity.getPrice())) {
//                criteria.eq(BilanParSpecialiteScience_.price, filterEntity.getPrice());
//            }
//
//            if (has(filterEntity.getName())) {
//                criteria.likeIgnoreCase(BilanParSpecialiteScience_.name, "%" + filterEntity.getName() + "%");
//            }
//        }
        return criteria;
    }

    @Override
    public void beforeInsert(BilanParSpecialiteScience bss) {
        validate(bss);
    }

    @Override
    public void beforeUpdate(BilanParSpecialiteScience bss) {
        validate(bss);
    }

    public void validate(BilanParSpecialiteScience bss) {
        BusinessException be = new BusinessException();
//        if (!bss.hasModel()) {
//            be.addException(new BusinessException("BilanParSpecialiteScience model cannot be empty"));
//        }
//        if (!bss.hasName()) {
//            be.addException(new BusinessException("BilanParSpecialiteScience name cannot be empty"));
//        }
//
//        if (!has(bss.getPrice())) {
//            be.addException(new BusinessException("BilanParSpecialiteScience price cannot be empty"));
//        }
//
//        if (count(criteria()
//                .eqIgnoreCase(BilanParSpecialiteScience_.name, bss.getName())
//                .notEq(BilanParSpecialiteScience_.id, bss.getId())) > 0) {
//
//            be.addException(new BusinessException("BilanParSpecialiteScience name must be unique"));
//        }

        if (has(be.getExceptionList())) {
            throw be;
        }
    }
    
    public BilanParSpecialiteScience exists(Eleve eleve, Integer trimestre, AnneeAcademique annee) {
        BilanParSpecialiteScience bss = criteria()
                .eq(BilanParSpecialiteScience_.eleve, eleve)
                .eq(BilanParSpecialiteScience_.trimestre, trimestre)
                .eq(BilanParSpecialiteScience_.anneeAcademique, annee)
                .getOptionalResult();
        return bss ;
    }
}
