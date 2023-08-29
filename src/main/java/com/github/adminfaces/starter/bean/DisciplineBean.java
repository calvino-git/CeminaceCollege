/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import com.github.adminfaces.persistence.bean.BeanService;
import com.github.adminfaces.persistence.bean.CrudMB;
import static com.github.adminfaces.persistence.bean.CrudMB.addDetailMsg;
import static com.github.adminfaces.persistence.util.Messages.addDetailMessage;
import com.github.adminfaces.starter.model.Discipline;
import com.github.adminfaces.starter.model.Discipline_;
import com.github.adminfaces.starter.service.DisciplineService;
import org.omnifaces.cdi.ViewScoped;

import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;

/**
 * @author rmpestano
 */
@Named
@ViewScoped
@BeanService(DisciplineService.class)//use annotation instead of setter
public class DisciplineBean extends CrudMB<Discipline> implements Serializable {

    @Inject
    DisciplineService disciplineService;
    @Inject
    AnneeAcademiqueBean anneeAcademiqueBean;

//    @PostConstruct
    public void initBean() {
        filter.getEntity().setAnneeAcademique(anneeAcademiqueBean.getAnneeEnCours());
    }

    public List<Discipline> autoCompletion(String query) {
        List<Discipline> result = disciplineService.disciplinesParMatiereOuClasse(anneeAcademiqueBean.getAnneeEnCours(), query);

        result.sort((o1, o2) -> {
            String d1 = o1.getClasse().getNiveau().getId() + o1.getClasse().getCode() + o1.getMatiere().getIndex();
            String d2 = o2.getClasse().getNiveau().getId() + o2.getClasse().getCode() + o2.getMatiere().getIndex();
            return d1.compareTo(d2); //To change body of generated lambdas, choose Tools | Templates.
        });
        return result;
    }

    public void insert() {
        entity.setId(null);
        entity.setAnneeAcademique(entity.getClasse().getAnneeAcademique());
        save();
    }

    public void delete() {
        int numDiscipline = 0;
        for (Discipline selectedDiscipline : selectionList) {
            numDiscipline++;
            disciplineService.remove(selectedDiscipline);
        }
        selectionList.clear();
        addDetailMessage(numDiscipline + " disciplinex deleted successfully!");
        clear();
    }

    @Override
    public void afterRemove() {
        try {
            addDetailMsg("Discipline " + entity.getClasse().getCode() + "-" + entity.getMatiere().getCode()
                    + " removed successfully");
            clear();
            sessionFilter.clear(DisciplineBean.class.getName());//removes filter saved in session for CarListMB.
        } catch (Exception e) {
            log.log(Level.WARNING, "", e);
        }
    }

    @Override
    public void afterInsert() {
        selection = entity;
        addDetailMsg("Discipline <b>" + entity.getClasse().getCode() + "-" + entity.getMatiere().getCode() + "</b> ajoutée avec succes");
    }

    @Override
    public void afterUpdate() {
        addDetailMsg("Discipline <b>" + entity.getClasse().getCode() + "-" + entity.getMatiere().getCode() + " </b> modifiée avec succes");
    }

    public void onRowSelect(SelectEvent event) {
        this.entity = this.selection;
//        this.init();
        System.out.println("Discipline : " + entity.getClasse().getCode() + "-" + entity.getMatiere().getCode());
    }

    public List<String> getEnseignants(String txt) {
        return disciplineService.criteria()
                .select(String.class, disciplineService.attribute(Discipline_.enseignant))
                .likeIgnoreCase(Discipline_.enseignant, txt)
                .distinct()
                .getResultList();
    }
}