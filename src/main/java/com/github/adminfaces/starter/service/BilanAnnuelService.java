/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.repository.EleveRepository;

import static com.github.adminfaces.template.util.Assert.has;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author rmpestano
 */
@Service
public class BilanAnnuelService {

    @Autowired
    EleveRepository eleveRepository;
//
//    @Override
//    protected Criteria<BilanAnnuel, BilanAnnuel> configRestrictions(Filter<BilanAnnuel> filter) {
//
//        Criteria<BilanAnnuel, BilanAnnuel> criteria = criteria();
//
//        //create restrictions based on parameters map
////        if (filter.hasParam("id")) {
////            criteria.eq(BilanAnnuel_.id, filter.getIntParam("id"));
////        }
////
////        if (filter.hasParam("minPrice") && filter.hasParam("maxPrice")) {
////            criteria.between(BilanAnnuel_.price, filter.getDoubleParam("minPrice"), filter.getDoubleParam("maxPrice"));
////        } else if (filter.hasParam("minPrice")) {
////            criteria.gtOrEq(BilanAnnuel_.price, filter.getDoubleParam("minPrice"));
////        } else if (filter.hasParam("maxPrice")) {
////            criteria.ltOrEq(BilanAnnuel_.price, filter.getDoubleParam("maxPrice"));
////        }
////
////        //create restrictions based on filter entity
////        if (has(filter.getEntity())) {
////            BilanAnnuel filterEntity = filter.getEntity();
////            if (has(filterEntity.getModel())) {
////                criteria.likeIgnoreCase(BilanAnnuel_.model, "%" + filterEntity.getModel());
////            }
////
////            if (has(filterEntity.getPrice())) {
////                criteria.eq(BilanAnnuel_.price, filterEntity.getPrice());
////            }
////
////            if (has(filterEntity.getName())) {
////                criteria.likeIgnoreCase(BilanAnnuel_.name, "%" + filterEntity.getName() + "%");
////            }
////        }
//        return criteria;
//    }
//
//    @Override
//    public void beforeInsert(BilanAnnuel bilanAnnuel) {
//        validate(bilanAnnuel);
//    }
//
//    @Override
//    public void beforeUpdate(BilanAnnuel bilanAnnuel) {
//        validate(bilanAnnuel);
//    }
//
//    public void validate(BilanAnnuel bilanAnnuel) {
//        BusinessException be = new BusinessException();
////        if (!bilanAnnuel.hasModel()) {
////            be.addException(new BusinessException("BilanAnnuel model cannot be empty"));
////        }
////        if (!bilanAnnuel.hasName()) {
////            be.addException(new BusinessException("BilanAnnuel name cannot be empty"));
////        }
////
////        if (!has(bilanAnnuel.getPrice())) {
////            be.addException(new BusinessException("BilanAnnuel price cannot be empty"));
////        }
////
////        if (count(criteria()
////                .eqIgnoreCase(BilanAnnuel_.name, bilanAnnuel.getName())
////                .notEq(BilanAnnuel_.id, bilanAnnuel.getId())) > 0) {
////
////            be.addException(new BusinessException("BilanAnnuel name must be unique"));
////        }
//
//        if (has(be.getExceptionList())) {
//            throw be;
//        }
//    }
//
//    public BilanAnnuel exists(Classe classe, Eleve eleve) {
//        BilanAnnuel bilanAnnuel = criteria()
//                .eq(BilanAnnuel_.classe, classe)
//                .eq(BilanAnnuel_.eleve, eleve)
//                .getOptionalResult();
//        return bilanAnnuel;
//    }

}
