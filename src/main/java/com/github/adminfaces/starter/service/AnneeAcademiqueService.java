/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.persistence.model.Filter;
import com.github.adminfaces.persistence.service.CrudService;
import static com.github.adminfaces.persistence.util.Messages.addDetailMessage;
import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.AnneeAcademique_;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.model.Discipline;
import com.github.adminfaces.template.exception.BusinessException;
import static com.github.adminfaces.template.util.Assert.has;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.inject.Inject;
import org.apache.deltaspike.data.api.criteria.Criteria;
import org.apache.log4j.Logger;

/**
 * @author rmpestano
 */
@Stateless
public class AnneeAcademiqueService extends CrudService<AnneeAcademique, Integer> implements Serializable {

    @Inject
    ClasseService classeService;
    @Inject
    DisciplineService disciplineService;
    private AnneeAcademique an;
    private List<Classe> classes;
    private List<Discipline> disciplines;

    public AnneeAcademique anneeEnCours() {
        return criteria().eq(AnneeAcademique_.statut, true).getSingleResult();
    }

    public List<AnneeAcademique> liste() {
        return criteria().orderDesc(AnneeAcademique_.id).getResultList();
    }

    @Override
    protected Criteria<AnneeAcademique, AnneeAcademique> configRestrictions(Filter<AnneeAcademique> filter) {

        Criteria<AnneeAcademique, AnneeAcademique> criteria = criteria();

        //create restrictions based on parameters map
        if (filter.hasParam("id")) {
            criteria.eq(AnneeAcademique_.id, filter.getIntParam("id"));
        }

        if (filter.hasParam("annee")) {
            criteria.eq(AnneeAcademique_.annee, filter.getStringParam("annee"));
        }
        if (filter.hasParam("statut")) {
            criteria.eq(AnneeAcademique_.statut, filter.getBooleanParam("statut"));
        }

        //create restrictions based on filter entity
        if (has(filter.getEntity())) {
            AnneeAcademique filterEntity = filter.getEntity();
            if (has(filterEntity.getAnnee())) {
                criteria.likeIgnoreCase(AnneeAcademique_.annee, "%" + filterEntity.getAnnee());
            }
        }
        return criteria;
    }

    @Override
    public void beforeRemove(AnneeAcademique anneeAcademique){
        BusinessException be = new BusinessException();

        
        if(!anneeAcademique.getStatut()){
            BusinessException be1 = be.addException(new BusinessException("Année scolaire " + anneeAcademique, "Vous ne pouvez pas supprimer une année en status cloturé", FacesMessage.SEVERITY_WARN));
//            Logger.getLogger(this.getClass()).info("Vous ne pouvez pas supprimer un année en status cloturé", be1);
//            addDetailMessage("Vous ne pouvez pas supprimer un année en status cloturé ");
        }
        if(has(be.getExceptionList())) {
            throw be;
        }
    }
    @Override
    public void beforeInsert(AnneeAcademique anneeAcademique) {
        validate(anneeAcademique);
        anneeAcademique.setId(0);
        an = anneeEnCours();
        liste().forEach((t) -> {
            t.setStatut(false);
            if (t.getId() > anneeAcademique.getId()) {
                anneeAcademique.setId(t.getId());
            }

            saveOrUpdate(t);

        });
        int id = anneeAcademique.getId();
        anneeAcademique.setId(id+1);
    }

    @Override
    public void afterInsert(AnneeAcademique anneeAcademique) {
        classes = an.getClasseList();
        disciplines = an.getDisciplineList();
        for (Classe c : classes) {
            Classe newClasse = new Classe();
            newClasse.setAnneeAcademique(anneeAcademique);
            newClasse.setCode(c.getCode());
            newClasse.setCycle(c.getCycle());
            newClasse.setDescription(c.getDescription());
            newClasse.setLibelle(c.getLibelle());
            newClasse.setNiveau(c.getNiveau());
            classeService.insert(newClasse);
            Logger.getRootLogger().info(newClasse.getAnneeAcademique() + ": classe " + newClasse.getCode());
            List<Discipline> list = disciplines.stream().filter(d -> d.getClasse().getCode().equals(newClasse.getCode())).collect(Collectors.toList());

            for (Discipline d : list) {
                Discipline newDiscipline = new Discipline();
//                newDiscipline.setAnneeAcademique(classe.getAnneeAcademique());
                newDiscipline.setClasse(newClasse);
                newDiscipline.setCoefficient(d.getCoefficient());
                newDiscipline.setMatiere(d.getMatiere());
                newDiscipline.setEnseignant(d.getEnseignant());
                disciplineService.insert(newDiscipline);
                Logger.getRootLogger().info(newDiscipline.getAnneeAcademique() + ": classe " + newDiscipline.getClasse().getCode()
                        + "/" + newDiscipline.getMatiere().getCode() + "-" + newDiscipline.getCoefficient());
            }
        }

    }

    @Override
    public void beforeUpdate(AnneeAcademique anneeAcademique) {
//        validate(anneeAcademique);
    }

    @Override
    public void afterRemove(AnneeAcademique anneeAcademique) {
        addDetailMessage("Annee : " + anneeAcademique + " supprimée avec succès ");
    }

    public void validate(AnneeAcademique anneeAcademique) {
        BusinessException be = new BusinessException();
        if (!anneeAcademique.hasAnnee()) {
            BusinessException be1 = be.addException(new BusinessException("Le champ <b>Annee Scolaire*</b> ne doit pas être vide"));
            Logger.getLogger(this.getClass()).info("L'année scolaire est nulle", be1);
        }

        if (count(criteria().eq(AnneeAcademique_.annee, anneeAcademique.getAnnee())
                .notEq(AnneeAcademique_.id, anneeAcademique.getId())) > 0) {

            BusinessException be2 = be.addException(new BusinessException("L'année académique " + anneeAcademique.getAnnee() + " existe déjà"));
            Logger.getLogger(this.getClass()).info("L'année scolaire est déjà dans la base", be2);
        }

        if (has(be.getExceptionList())) {
            throw be;
        }
    }
}
