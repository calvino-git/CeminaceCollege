/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.BilanParSpecialiteLettreCollege;

import com.github.adminfaces.starter.model.Eleve;
import com.github.adminfaces.starter.repository.BslCollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.github.adminfaces.template.util.Assert.has;

/**
 * @author rmpestano
 */
@Service
public class BslCollegeService{
    @Autowired
    BslCollegeRepository bslCollegeRepository;

    public BilanParSpecialiteLettreCollege findByEleveAndTrimestreAndAnneeAcademique(Eleve eleve, Integer trimestre, AnneeAcademique annee) {
        Optional<BilanParSpecialiteLettreCollege> bsl = bslCollegeRepository.findBilanParSpecialiteLettreCollegesByEleveAndTrimestreAndAnneeAcademique(eleve, trimestre, annee);
        return bsl.orElse(null);
    }

    public BilanParSpecialiteLettreCollege save(BilanParSpecialiteLettreCollege bilanParSpecialiteLettreCollege){
        return bslCollegeRepository.save(bilanParSpecialiteLettreCollege);
    }

//    @Override
//    protected Criteria<BilanParSpecialiteLettreCollege, BilanParSpecialiteLettreCollege> configRestrictions(Filter<BilanParSpecialiteLettreCollege> filter) {
//
//        Criteria<BilanParSpecialiteLettreCollege, BilanParSpecialiteLettreCollege> criteria = criteria();
//
//        //create restrictions based on parameters map
////        if (filter.hasParam("id")) {
////            criteria.eq(BilanParSpecialiteLettre_.id, filter.getIntParam("id"));
////        }
////
////        if (filter.hasParam("minPrice") && filter.hasParam("maxPrice")) {
////            criteria.between(BilanParSpecialiteLettre_.price, filter.getDoubleParam("minPrice"), filter.getDoubleParam("maxPrice"));
////        } else if (filter.hasParam("minPrice")) {
////            criteria.gtOrEq(BilanParSpecialiteLettre_.price, filter.getDoubleParam("minPrice"));
////        } else if (filter.hasParam("maxPrice")) {
////            criteria.ltOrEq(BilanParSpecialiteLettre_.price, filter.getDoubleParam("maxPrice"));
////        }
////
////        //create restrictions based on filter entity
////        if (has(filter.getEntity())) {
////            BilanParSpecialiteLettreCollege filterEntity = filter.getEntity();
////            if (has(filterEntity.getModel())) {
////                criteria.likeIgnoreCase(BilanParSpecialiteLettre_.model, "%" + filterEntity.getModel());
////            }
////
////            if (has(filterEntity.getPrice())) {
////                criteria.eq(BilanParSpecialiteLettre_.price, filterEntity.getPrice());
////            }
////
////            if (has(filterEntity.getName())) {
////                criteria.likeIgnoreCase(BilanParSpecialiteLettre_.name, "%" + filterEntity.getName() + "%");
////            }
////        }
//        return criteria;
//    }
//
//    @Override
//    public void beforeInsert(BilanParSpecialiteLettreCollege bsl) {
//        validate(bsl);
//    }
//
//    @Override
//    public void beforeUpdate(BilanParSpecialiteLettreCollege bsl) {
//        validate(bsl);
//    }
//
//    public void validate(BilanParSpecialiteLettreCollege bsl) {
//        BusinessException be = new BusinessException();
////        if (!bsl.hasModel()) {
////            be.addException(new BusinessException("BilanParSpecialiteLettreCollege model cannot be empty"));
////        }
////        if (!bsl.hasName()) {
////            be.addException(new BusinessException("BilanParSpecialiteLettreCollege name cannot be empty"));
////        }
////
////        if (!has(bsl.getPrice())) {
////            be.addException(new BusinessException("BilanParSpecialiteLettreCollege price cannot be empty"));
////        }
////
////        if (count(criteria()
////                .eqIgnoreCase(BilanParSpecialiteLettre_.name, bsl.getName())
////                .notEq(BilanParSpecialiteLettre_.id, bsl.getId())) > 0) {
////
////            be.addException(new BusinessException("BilanParSpecialiteLettreCollege name must be unique"));
////        }
//
//        if (has(be.getExceptionList())) {
//            throw be;
//        }
//    }

}
