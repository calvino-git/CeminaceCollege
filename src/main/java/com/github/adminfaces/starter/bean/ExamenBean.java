/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import com.github.adminfaces.persistence.bean.BeanService;
import com.github.adminfaces.persistence.bean.CrudMB;
import static com.github.adminfaces.persistence.bean.CrudMB.addDetailMsg;
import static com.github.adminfaces.persistence.util.Messages.addDetailMessage;
import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.model.Discipline;
import com.github.adminfaces.starter.model.Eleve;
import com.github.adminfaces.starter.model.Examen;
import com.github.adminfaces.starter.model.Note;
import com.github.adminfaces.starter.service.EleveService;
import com.github.adminfaces.starter.service.ExamenService;
import com.github.adminfaces.starter.service.NoteService;
import com.github.adminfaces.template.exception.BusinessException;
import org.omnifaces.util.Faces;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 * @author rmpestano
 */
@Named
@ViewScoped
@BeanService(ExamenService.class)//use annotation instead of setter
public class ExamenBean extends CrudMB<Examen> implements Serializable {

    @Inject
    AnneeAcademiqueBean anneeAcademiqueBean;
    @Inject
    ExamenService examenService;
    @Inject
    EleveService eleveService;
    @Inject
    NoteService noteService;

    private AnneeAcademique anneeAcademique;
    private Integer trimestre;
    private String date;
    private String type;

    @Override
    public void init() {
        super.init();
//        filter.getEntity().setAnneeAcademique(anneeAcademiqueBean.getAnneeEnCours());
        filter.getEntity().setAnneeAcademique(anneeAcademiqueBean.getAnneeEnCours());

    }

    public void autogenere() {
        int nbr = 0;
        for (Classe classe : anneeAcademique.getClasseList()) {
            for (Discipline discipline : classe.getDisciplineCollection()) {
                //EPS n'est pas en INTERRO
                if (type.startsWith("INTERRO") && discipline.getMatiere().getCode().equals("EPS")) {
                    continue;
                }

                //CON n'est pas en INTERRO & EVALUATION
                if (type.startsWith("INTERRO") && discipline.getMatiere().getCode().equals("CON")) {
                    continue;
                }
                if (type.startsWith("EVALUATION") && discipline.getMatiere().getCode().equals("CON")) {
                    continue;
                }

                //IC n'existe pas en 3eme
                if (discipline.getMatiere().getCode().equals("IC") && discipline.getClasse().getCode().startsWith("3e")) {
                    continue;
                }
//                    if(type.startsWith("INTERRO") && discipline.getMatiere().getCode().equals("IC")) continue;
//                    if(type.startsWith("EVALUATION") && discipline.getMatiere().getCode().equals("IC")) continue;

                //ART n'est pas 6e et 3e et n'est pas en INTERRO & EVALUATION
                if (discipline.getMatiere().getCode().equals("ART") && discipline.getClasse().getCode().startsWith("6e")) {
                    continue;
                }
                if (discipline.getMatiere().getCode().equals("ART") && discipline.getClasse().getCode().startsWith("3e")) {
                    continue;
                }
                if (type.startsWith("INTERRO") && discipline.getMatiere().getCode().equals("ART")) {
                    continue;
                }
                if (type.startsWith("EVALUATION") && discipline.getMatiere().getCode().equals("ART")) {
                    continue;
                }

                entity = new Examen(trimestre, date, type);
                entity.setDiscipline(discipline);
                entity.setAnneeAcademique(anneeAcademique);
                try {
                    save();
                } catch (BusinessException be) {
                    if (be.getExceptionList().get(0).getSummary().equals("Doublon")) {
                        continue;
                    }else{
                        throw be;
                    }
                }
                nbr++;
                List<Eleve> eleves = eleveService.listeParClasse(classe);

                eleves.forEach(ce -> {
                    Eleve e = ce;
                    Note note = new Note();
                    note.setNote(0.0);
                    note.setObservation(" ");
                    note.setPresence("PRESENT");
                    note.setRang(0);
                    note.setEleve(ce);
                    note.setExamen(entity);
                    noteService.saveOrUpdate(note);
                    System.out.println("Note : " + note.getId());
                });
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", nbr + " épreuves créées avec succès"));

    }

    public void onComplete() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Importation terminé avec succès"));
    }

    public void createNewExam() {
        try {
            entity.setAnneeAcademique(anneeAcademiqueBean.getAnneeEnCours());
            save();
            Classe classe = entity.getDiscipline().getClasse();
            List<Eleve> eleves = eleveService.listeParClasse(classe);

            eleves.forEach(ce -> {
                Eleve e = ce;
                Note note = new Note();
                note.setNote(0.0);
                note.setObservation(" ");
                note.setPresence("PRESENT");
                note.setRang(0);
                note.setEleve(ce);
                note.setExamen(entity);
                noteService.saveOrUpdate(note);
                System.out.println("Note : " + note.getId());
            });
            setSelection(entity);

        } catch (BusinessException be) {
            throw be;
        }
    }

    public void toNote() {
        Faces.redirect("note.xhtml");
    }

    public void delete() {
        int numExamen = 0;
        for (Examen selectedExamen : selectionList) {
            numExamen++;
            examenService.remove(selectedExamen);
        }
        selectionList.clear();
        addDetailMessage(numExamen + " examen supprimé!");
        clear();
    }

    @Override
    public void afterRemove() {
        try {
            addDetailMsg("Examen " + entity + " supprimé.");
            clear();
            sessionFilter.clear(ExamenBean.class
                    .getName());//removes filter saved in session for CarListMB.
        } catch (Exception e) {
            log.log(Level.WARNING, "", e);
        }
    }

    @Override
    public void afterInsert() {
        addDetailMsg("Examen " + entity + " créé.");
    }

    @Override
    public void afterUpdate() {
        addDetailMsg("Examen " + entity + " modifié.");
    }

    public void onRowSelect(SelectEvent event) {
        this.entity = this.selection;
//        this.init();
        System.out.println("Examen : " + entity + " selectionné.");
    }

    public AnneeAcademique getAnneeAcademique() {
        return anneeAcademique;
    }

    public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
        this.anneeAcademique = anneeAcademique;
    }

    public Integer getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Integer trimestre) {
        this.trimestre = trimestre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String Type) {
        this.type = Type;
    }

}
