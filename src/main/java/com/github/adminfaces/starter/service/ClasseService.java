/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.persistence.model.Filter;
import com.github.adminfaces.persistence.service.CrudService;
import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.model.Classe_;
import com.github.adminfaces.starter.model.Niveau;
import com.github.adminfaces.template.exception.BusinessException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

import static com.github.adminfaces.template.util.Assert.has;
import java.util.Optional;
import org.apache.deltaspike.data.api.criteria.Criteria;

/**
 * @author rmpestano
 */
@Stateless
public class ClasseService extends CrudService<Classe, Integer> implements Serializable {

    @Inject
    protected ClasseRepository repo;//you can create repositories to extract complex queries from your service

    @Override
    protected Criteria<Classe, Classe> configRestrictions(Filter<Classe> filter) {
        Criteria<Classe, Classe> criteria = criteria();
        if (has(filter.getEntity())) {
            if (filter.getEntity().hasNiveau()) {
                criteria.eq(Classe_.niveau, filter.getEntity().getNiveau());
            }
            if (has(filter.getEntity().getAnneeAcademique())) {
                criteria.eq(Classe_.anneeAcademique, filter.getEntity().getAnneeAcademique());
            }
        }
        return criteria;
    }

    @Override
    public void beforeInsert(Classe classe) {
        validate(classe);
    }

    @Override
    public void beforeUpdate(Classe classe) {
        validate(classe);
    }

    public void validate(Classe classe) {
        BusinessException be = new BusinessException();
        if (!classe.hasCode()) {
            be.addException(new BusinessException("Le code est obligatoire."));
        }
        if (!classe.hasLibelle()) {
            be.addException(new BusinessException("Le libellé est obligatoire."));
        }
        if (!classe.hasCycle()) {
            be.addException(new BusinessException("Le cycle est obligatoire."));
        }
        if (!classe.hasNiveau()) {
            be.addException(new BusinessException("Le niveau d'étude est obligatoire."));
        }

        if (count(criteria()
                .eqIgnoreCase(Classe_.code, classe.getCode())
                .eqIgnoreCase(Classe_.libelle, classe.getLibelle())
                .eq(Classe_.anneeAcademique, classe.getAnneeAcademique())
                .notEq(Classe_.id, classe.getId())) > 0) {

            be.addException(new BusinessException("La classe " + classe.getCode()
                    + ": " + classe.getLibelle() + " au " + classe.getCycle() + "  existe déjà."));
        }

        if (has(be.getExceptionList())) {
            throw be;
        }
    }

    public List<Classe> listeParAnnee(Classe classe) {
        List<Classe> list = repo.findByLike(classe, Classe_.anneeAcademique, Classe_.libelle);
        return list;
    }

    public List<Classe> liste() {
        Filter<Classe> filter = new Filter<>();
        filter.setFirst(0).setPageSize(count().intValue());
        return paginate(filter);
    }

    public List<Classe> listeParCycle(String cycle) {
        return criteria()
                .likeIgnoreCase(Classe_.cycle, cycle)
                .getResultList();
    }

    public List<Classe> listeParNiveau(Niveau niveau) {
        return criteria()
                .eq(Classe_.niveau, niveau)
                .getResultList();
    }

    public Optional<Classe> classeParAnneeEtLibelle(AnneeAcademique anneeAcademique, String query) {
        return repo.classeParAnneeEtLibelle(anneeAcademique, query);
    }
    public Optional<Classe> classeParAnneeEtCode(AnneeAcademique anneeAcademique, String code) {
        return repo.classeParAnneeEtCode(anneeAcademique, code);
    }

    public List<Niveau> listeNiveau() {
        return criteria()
                .select(Niveau.class, attribute(Classe_.niveau))
                .distinct()
                .getResultList();
    }

}
