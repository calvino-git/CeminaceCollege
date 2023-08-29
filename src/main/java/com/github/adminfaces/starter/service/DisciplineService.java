/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.persistence.model.Filter;
import com.github.adminfaces.persistence.service.CrudService;
import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.model.Discipline;
import com.github.adminfaces.starter.model.Discipline_;
import com.github.adminfaces.starter.model.Matiere;
import com.github.adminfaces.template.exception.BusinessException;
import static com.github.adminfaces.template.util.Assert.has;
import org.apache.deltaspike.data.api.criteria.Criteria;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

import java.util.Optional;

@Stateless
public class DisciplineService extends CrudService<Discipline, Integer> implements Serializable {

    @Inject
    protected DisciplineRepository repo;//you can create repositories to extract complex queries from your service

    @Override
    protected Criteria<Discipline, Discipline> configRestrictions(Filter<Discipline> filter) {
        Criteria<Discipline, Discipline> criteria = criteria();
        if (has(filter.getEntity())) {
            if (filter.getEntity().hasMatiere()) {
                criteria.eq(Discipline_.matiere, filter.getEntity().getMatiere());
            }
            if (filter.getEntity().hasClasse()) {
                criteria.eq(Discipline_.classe, filter.getEntity().getClasse());
            }
            if (has(filter.getEntity().getAnneeAcademique())) {
                criteria.eq(Discipline_.anneeAcademique, filter.getEntity().getAnneeAcademique());
            }
        }
        return criteria;
    }

    @Override
    public void beforeInsert(Discipline discipline) {
        discipline.setAnneeAcademique(discipline.getClasse().getAnneeAcademique());
        validate(discipline);
    }

    @Override
    public void beforeUpdate(Discipline discipline) {
        validate(discipline);
    }

    public void validate(Discipline discipline) {
        BusinessException be = new BusinessException();
        if (!discipline.hasMatiere()) {
            be.addException(new BusinessException("Matière est obligatoire."));
        }
        if (!discipline.hasEnseignant()) {
            be.addException(new BusinessException("Enseignant est obligatoire."));
        }
        if (!discipline.hasClasse()) {
            be.addException(new BusinessException("Classe est obligatoire."));
        }
        if (count(criteria()
                .eq(Discipline_.matiere, discipline.getMatiere())
                .eq(Discipline_.classe, discipline.getClasse())
                .notEq(Discipline_.id, discipline.getId())) > 0) {

            be.addException(new BusinessException("La discipline " + discipline.getMatiere().getLibelle()
                    + " de la classe " + discipline.getClasse().getLibelle() + "  existe déjà."));
        }

        if (has(be.getExceptionList())) {
            throw be;
        }
    }

    public List<Discipline> liste(AnneeAcademique anneeAcademique) {
        List<Discipline> list = criteria()
                .eq(Discipline_.anneeAcademique, anneeAcademique)
                .distinct()
                .getResultList();
        return list;
    }

    public Optional<Discipline> disciplineParMatiereEtClasse(Classe classe, Matiere matiere) {
        return repo.disciplineParMatiereEtClasse(matiere, classe);
    }

    public List<Discipline> disciplinesParMatiereOuClasse(AnneeAcademique anneeAcademique, String query) {
        List<Discipline> result = repo.disciplinesParMatiereOuClasse(anneeAcademique, "%" + query + "%");
        return result;
    }

    public List<Discipline> disciplinesParClasse(Classe c) {
        List<Discipline> result = repo.disciplinesParClasse(c);
        return result;
    }
}
