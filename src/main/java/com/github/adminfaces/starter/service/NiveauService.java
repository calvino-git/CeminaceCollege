/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.model.Niveau;
import com.github.adminfaces.starter.repository.NiveauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.github.adminfaces.template.util.Assert.has;

/**
 * @author rmpestano
 */
@Service
public class NiveauService implements Serializable {

    @Autowired
    protected NiveauRepository niveauRepository;//you can create repositories to extract complex queries from your service

    public List<String> findAllCode(){
        return niveauRepository.findAll().stream().map(Niveau::getCode).sorted().collect(Collectors.toList());
    }

    public Optional<Niveau> findByCode(String code) {
        return niveauRepository.findNiveauByCode(code);
    }

//    @Override
//    public void beforeInsert(Niveau niveau) {
//        validate(niveau);
//    }
//
//    @Override
//    public void beforeUpdate(Niveau niveau) {
//        validate(niveau);
//    }
//
//    public void validate(Niveau niveau) {
//        BusinessException be = new BusinessException();
//        if (!niveau.hasCycle()) {
//            be.addException(new BusinessException("Le cycle est obligatoire."));
//        }
//        if (!niveau.hasTitre()) {
//            be.addException(new BusinessException("le titre est obligatoire."));
//        }
//
//        if (count(criteria()
//                .eqIgnoreCase(Niveau_.titre, niveau.getTitre())
//                .eqIgnoreCase(Niveau_.cycle, niveau.getCycle())
//                .notEq(Niveau_.id, niveau.getId())) > 0) {
//
//            be.addException(new BusinessException("Le niveau " + niveau.getTitre()
//                    + " au " + niveau.getCycle() + "  existe déjà."));
//        }
//
//        if (has(be.getExceptionList())) {
//            throw be;
//        }
//    }
//
//
//    public List<Niveau> listeParCycle(String cycle) {
//        return criteria()
//                .likeIgnoreCase(Niveau_.cycle, cycle)
//                .getResultList();
//    }
//
//    public List<String> getCycles(String query) {
//        return criteria()
//                .select(String.class, attribute(Niveau_.cycle))
//                .likeIgnoreCase(Niveau_.cycle, "%" + query + "%")
//                .distinct()
//                .getResultList();
//    }
//
//    public List<Niveau> liste(){
//        return niveauRepository.liste();
//    }


}
