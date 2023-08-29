/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import static com.github.adminfaces.persistence.bean.CrudMB.addDetailMsg;
import com.github.adminfaces.persistence.model.Filter;
import com.github.adminfaces.persistence.service.CrudService;
import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.model.Eleve;
import com.github.adminfaces.starter.model.Eleve_;
import com.github.adminfaces.template.exception.BusinessException;
import static com.github.adminfaces.template.util.Assert.has;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.faces.application.FacesMessage;

import org.apache.deltaspike.data.api.criteria.Criteria;

/**
 * @author rmpestano
 */
@Stateless
public class EleveService extends CrudService<Eleve, Integer> implements Serializable {

    @Inject
    protected EleveRepository repo;//you can create repositories to extract complex queries from your service

    @Override
    protected Criteria<Eleve, Eleve> configRestrictions(Filter<Eleve> filter) {
        Criteria<Eleve, Eleve> criteria = criteria();
        
        if(has(filter.getParam("nom"))){
            criteria.like(Eleve_.nom, "%" + filter.getStringParam("nom")+ "%");
        }
        
        if (has(filter.getEntity())) {

            if (filter.getEntity().hasNom()) {
                criteria.like(Eleve_.nom, "%" + filter.getEntity().getNom() + "%");
            }
            if (has(filter.getEntity().getClasse())) {
                criteria.eq(Eleve_.classe, filter.getEntity().getClasse());
            }
            if (has(filter.getEntity().getAnneeAcademique())) {
                criteria.eq(Eleve_.anneeAcademique, filter.getEntity().getAnneeAcademique());
            }
        }
        return criteria;
    }

    @Override
    public void beforeInsert(Eleve eleve) {
        eleve.setAnneeAcademique(eleve.getClasse().getAnneeAcademique());
        validate(eleve);
    }

    @Override
    public void beforeUpdate(Eleve eleve) {
        validate(eleve);
    }

    @Override
    public void afterRemove(Eleve eleve) {
        addDetailMsg("Eleve <b>" + eleve.getNom() + "</b> supprimé avec succès", FacesMessage.SEVERITY_WARN);
    }

    public void validate(Eleve eleve) {
        BusinessException be = new BusinessException();
        if (!eleve.hasNom()) {
            be.addException(new BusinessException("Champ <b> nom </b> est obligatoire."));
        }
        if (!has(eleve.getClasse())) {
            be.addException(new BusinessException("Champ <b>Classe </b> est obligatoire."));
        }

        if (count(criteria()
                .eqIgnoreCase(Eleve_.nom, eleve.getNom())
                .eq(Eleve_.classe, eleve.getClasse())
                .eq(Eleve_.anneeAcademique, eleve.getAnneeAcademique())
                .notEq(Eleve_.id, eleve.getId())) > 0) {

            be.addException(new BusinessException("L'eleve " + eleve.getNom() + " de la classe existe déjà."));
        }

        if (has(be.getExceptionList())) {
            throw be;
        }
    }

    public List<Eleve> listeParClasse(Classe classe) {
        return repo.listeParClasse(classe);
    }

    public Optional<Eleve> eleveParAnneeEtNomComplet(AnneeAcademique anneeAcademique, String nomComplet) {
        return repo.eleveParAnneeEtNomComplet(anneeAcademique, nomComplet);
    }

    public Long nombreEleve(AnneeAcademique anneeAcademique) {
        return repo.nombreEleve(anneeAcademique);
    }

    public Long nombreEleveParSexe(AnneeAcademique anneeAcademique, String sexe) {
        return repo.nombreEleveParSexe(anneeAcademique, sexe);
    }

    public Long nombreEleveParNiveau(AnneeAcademique anneeAcademique, String niveau) {
        return repo.nombreEleveParNiveau(anneeAcademique, niveau);
    }

    public Long nombreEleveParNiveauSexe(AnneeAcademique anneeAcademique, String niveau, String sexe) {
        return repo.nombreEleveParNiveauSexe(anneeAcademique, niveau, sexe);
    }

    public List<Eleve> liste(AnneeAcademique anneeAcademique) {
        List<Eleve> list = criteria().eq(Eleve_.anneeAcademique, anneeAcademique)
                .getResultList();
        return list;
    }

}
