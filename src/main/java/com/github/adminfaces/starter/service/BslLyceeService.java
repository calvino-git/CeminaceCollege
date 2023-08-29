/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.persistence.model.Filter;
import com.github.adminfaces.persistence.service.CrudService;
import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.BilanParSpecialiteLettreLycee;
import com.github.adminfaces.starter.model.BilanParSpecialiteLettreLycee_;
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
public class BslLyceeService extends CrudService<BilanParSpecialiteLettreLycee, Integer> implements Serializable {

    @Override
    protected Criteria<BilanParSpecialiteLettreLycee, BilanParSpecialiteLettreLycee> configRestrictions(Filter<BilanParSpecialiteLettreLycee> filter) {

        Criteria<BilanParSpecialiteLettreLycee, BilanParSpecialiteLettreLycee> criteria = criteria();

        //create restrictions based on parameters map
//        if (filter.hasParam("id")) {
//            criteria.eq(BilanParSpecialiteLettre_.id, filter.getIntParam("id"));
//        }
//
//        if (filter.hasParam("minPrice") && filter.hasParam("maxPrice")) {
//            criteria.between(BilanParSpecialiteLettre_.price, filter.getDoubleParam("minPrice"), filter.getDoubleParam("maxPrice"));
//        } else if (filter.hasParam("minPrice")) {
//            criteria.gtOrEq(BilanParSpecialiteLettre_.price, filter.getDoubleParam("minPrice"));
//        } else if (filter.hasParam("maxPrice")) {
//            criteria.ltOrEq(BilanParSpecialiteLettre_.price, filter.getDoubleParam("maxPrice"));
//        }
//
//        //create restrictions based on filter entity
//        if (has(filter.getEntity())) {
//            BilanParSpecialiteLettreLycee filterEntity = filter.getEntity();
//            if (has(filterEntity.getModel())) {
//                criteria.likeIgnoreCase(BilanParSpecialiteLettre_.model, "%" + filterEntity.getModel());
//            }
//
//            if (has(filterEntity.getPrice())) {
//                criteria.eq(BilanParSpecialiteLettre_.price, filterEntity.getPrice());
//            }
//
//            if (has(filterEntity.getName())) {
//                criteria.likeIgnoreCase(BilanParSpecialiteLettre_.name, "%" + filterEntity.getName() + "%");
//            }
//        }
        return criteria;
    }

    @Override
    public void beforeInsert(BilanParSpecialiteLettreLycee bsl) {
        validate(bsl);
    }

    @Override
    public void beforeUpdate(BilanParSpecialiteLettreLycee bsl) {
        validate(bsl);
    }

    public void validate(BilanParSpecialiteLettreLycee bsl) {
        BusinessException be = new BusinessException();
//        if (!bsl.hasModel()) {
//            be.addException(new BusinessException("BilanParSpecialiteLettreLycee model cannot be empty"));
//        }
//        if (!bsl.hasName()) {
//            be.addException(new BusinessException("BilanParSpecialiteLettreLycee name cannot be empty"));
//        }
//
//        if (!has(bsl.getPrice())) {
//            be.addException(new BusinessException("BilanParSpecialiteLettreLycee price cannot be empty"));
//        }
//
//        if (count(criteria()
//                .eqIgnoreCase(BilanParSpecialiteLettre_.name, bsl.getName())
//                .notEq(BilanParSpecialiteLettre_.id, bsl.getId())) > 0) {
//
//            be.addException(new BusinessException("BilanParSpecialiteLettreLycee name must be unique"));
//        }

        if (has(be.getExceptionList())) {
            throw be;
        }
    }
    
    public BilanParSpecialiteLettreLycee exists(Eleve eleve, Integer trimestre, AnneeAcademique annee) {
        BilanParSpecialiteLettreLycee bsl = criteria()
                .eq(BilanParSpecialiteLettreLycee_.eleve, eleve)
                .eq(BilanParSpecialiteLettreLycee_.trimestre, trimestre)
                .eq(BilanParSpecialiteLettreLycee_.anneeAcademique, annee)
                .getOptionalResult();
        return bsl ;
    }
}
