/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.BilanParSpecialiteCultureCollege;
import com.github.adminfaces.starter.model.Eleve;
import com.github.adminfaces.starter.repository.BscCollegeRepository;
import com.github.adminfaces.template.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;

import static com.github.adminfaces.template.util.Assert.has;

/**
 * @author rmpestano
 */
@Service
public class BscCollegeService {
    @Autowired
    BscCollegeRepository bscCollegeRepository;

    public BilanParSpecialiteCultureCollege save(BilanParSpecialiteCultureCollege bilanParSpecialiteCultureCollege) {
        return bscCollegeRepository.save(bilanParSpecialiteCultureCollege);
    }

    public BilanParSpecialiteCultureCollege findByEleveAndTrimestreAndAnneeAcademique(Eleve eleve, Integer trimestre, AnneeAcademique anneeAcademique) {
        Optional<BilanParSpecialiteCultureCollege> bsc = bscCollegeRepository.findBilanParSpecialiteCultureCollegeByEleveAndTrimestreAndAnneeAcademique(eleve, trimestre, anneeAcademique);
        return bsc.orElse(null);
    }
//
//    @Override
//    protected Criteria<BilanParSpecialiteCultureCollege, BilanParSpecialiteCultureCollege> configRestrictions(Filter<BilanParSpecialiteCultureCollege> filter) {
//
//        Criteria<BilanParSpecialiteCultureCollege, BilanParSpecialiteCultureCollege> criteria = criteria();
//
//        //create restrictions based on parameters map
////        if (filter.hasParam("id")) {
////            criteria.eq(BilanParSpecialiteCulture_.id, filter.getIntParam("id"));
////        }
////
////        if (filter.hasParam("minPrice") && filter.hasParam("maxPrice")) {
////            criteria.between(BilanParSpecialiteCulture_.price, filter.getDoubleParam("minPrice"), filter.getDoubleParam("maxPrice"));
////        } else if (filter.hasParam("minPrice")) {
////            criteria.gtOrEq(BilanParSpecialiteCulture_.price, filter.getDoubleParam("minPrice"));
////        } else if (filter.hasParam("maxPrice")) {
////            criteria.ltOrEq(BilanParSpecialiteCulture_.price, filter.getDoubleParam("maxPrice"));
////        }
////
////        //create restrictions based on filter entity
////        if (has(filter.getEntity())) {
////            BilanParSpecialiteCultureCollege filterEntity = filter.getEntity();
////            if (has(filterEntity.getModel())) {
////                criteria.likeIgnoreCase(BilanParSpecialiteCulture_.model, "%" + filterEntity.getModel());
////            }
////
////            if (has(filterEntity.getPrice())) {
////                criteria.eq(BilanParSpecialiteCulture_.price, filterEntity.getPrice());
////            }
////
////            if (has(filterEntity.getName())) {
////                criteria.likeIgnoreCase(BilanParSpecialiteCulture_.name, "%" + filterEntity.getName() + "%");
////            }
////        }
//        return criteria;
//    }
//
//    @Override
//    public void beforeInsert(BilanParSpecialiteCultureCollege bsc) {
//        validate(bsc);
//    }
//
//    @Override
//    public void beforeUpdate(BilanParSpecialiteCultureCollege bsc) {
//        validate(bsc);
//    }
//
//    public void validate(BilanParSpecialiteCultureCollege bsc) {
//        BusinessException be = new BusinessException();
////        if (!bsc.hasModel()) {
////            be.addException(new BusinessException("BilanParSpecialiteCultureCollege model cannot be empty"));
////        }
////        if (!bsc.hasName()) {
////            be.addException(new BusinessException("BilanParSpecialiteCultureCollege name cannot be empty"));
////        }
////
////        if (!has(bsc.getPrice())) {
////            be.addException(new BusinessException("BilanParSpecialiteCultureCollege price cannot be empty"));
////        }
////
////        if (count(criteria()
////                .eqIgnoreCase(BilanParSpecialiteCulture_.name, bsc.getName())
////                .notEq(BilanParSpecialiteCulture_.id, bsc.getId())) > 0) {
////
////            be.addException(new BusinessException("BilanParSpecialiteCultureCollege name must be unique"));
////        }
//
//        if (has(be.getExceptionList())) {
//            throw be;
//        }
//    }
}
