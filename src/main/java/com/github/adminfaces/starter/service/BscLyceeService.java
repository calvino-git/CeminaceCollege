/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.persistence.model.Filter;
import com.github.adminfaces.persistence.service.CrudService;
import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.BilanParSpecialiteCultureLycee;
import com.github.adminfaces.starter.model.BilanParSpecialiteCultureLycee_;
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
public class BscLyceeService extends CrudService<BilanParSpecialiteCultureLycee, Integer> implements Serializable {

    @Override
    protected Criteria<BilanParSpecialiteCultureLycee, BilanParSpecialiteCultureLycee> configRestrictions(Filter<BilanParSpecialiteCultureLycee> filter) {

        Criteria<BilanParSpecialiteCultureLycee, BilanParSpecialiteCultureLycee> criteria = criteria();

        //create restrictions based on parameters map
//        if (filter.hasParam("id")) {
//            criteria.eq(BilanParSpecialiteCulture_.id, filter.getIntParam("id"));
//        }
//
//        if (filter.hasParam("minPrice") && filter.hasParam("maxPrice")) {
//            criteria.between(BilanParSpecialiteCulture_.price, filter.getDoubleParam("minPrice"), filter.getDoubleParam("maxPrice"));
//        } else if (filter.hasParam("minPrice")) {
//            criteria.gtOrEq(BilanParSpecialiteCulture_.price, filter.getDoubleParam("minPrice"));
//        } else if (filter.hasParam("maxPrice")) {
//            criteria.ltOrEq(BilanParSpecialiteCulture_.price, filter.getDoubleParam("maxPrice"));
//        }
//
//        //create restrictions based on filter entity
//        if (has(filter.getEntity())) {
//            BilanParSpecialiteCultureLycee filterEntity = filter.getEntity();
//            if (has(filterEntity.getModel())) {
//                criteria.likeIgnoreCase(BilanParSpecialiteCulture_.model, "%" + filterEntity.getModel());
//            }
//
//            if (has(filterEntity.getPrice())) {
//                criteria.eq(BilanParSpecialiteCulture_.price, filterEntity.getPrice());
//            }
//
//            if (has(filterEntity.getName())) {
//                criteria.likeIgnoreCase(BilanParSpecialiteCulture_.name, "%" + filterEntity.getName() + "%");
//            }
//        }
        return criteria;
    }

    @Override
    public void beforeInsert(BilanParSpecialiteCultureLycee bsc) {
        validate(bsc);
    }

    @Override
    public void beforeUpdate(BilanParSpecialiteCultureLycee bsc) {
        validate(bsc);
    }

    public void validate(BilanParSpecialiteCultureLycee bsc) {
        BusinessException be = new BusinessException();
//        if (!bsc.hasModel()) {
//            be.addException(new BusinessException("BilanParSpecialiteCultureLycee model cannot be empty"));
//        }
//        if (!bsc.hasName()) {
//            be.addException(new BusinessException("BilanParSpecialiteCultureLycee name cannot be empty"));
//        }
//
//        if (!has(bsc.getPrice())) {
//            be.addException(new BusinessException("BilanParSpecialiteCultureLycee price cannot be empty"));
//        }
//
//        if (count(criteria()
//                .eqIgnoreCase(BilanParSpecialiteCulture_.name, bsc.getName())
//                .notEq(BilanParSpecialiteCulture_.id, bsc.getId())) > 0) {
//
//            be.addException(new BusinessException("BilanParSpecialiteCultureLycee name must be unique"));
//        }

        if (has(be.getExceptionList())) {
            throw be;
        }
    }
    
    public BilanParSpecialiteCultureLycee exists(Eleve eleve, Integer trimestre, AnneeAcademique annee) {
        BilanParSpecialiteCultureLycee bsc = criteria()
                .eq(BilanParSpecialiteCultureLycee_.eleve, eleve)
                .eq(BilanParSpecialiteCultureLycee_.trimestre, trimestre)
                .eq(BilanParSpecialiteCultureLycee_.anneeAcademique, annee)
                .getOptionalResult();
        return bsc ;
    }
}
