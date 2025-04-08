/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import com.github.adminfaces.persistence.bean.BeanService;
import com.github.adminfaces.persistence.bean.CrudMB;
import static com.github.adminfaces.persistence.bean.CrudMB.addDetailMsg;
import static com.github.adminfaces.persistence.util.Messages.addDetailMessage;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.model.Discipline;
import com.github.adminfaces.starter.model.Eleve;
import com.github.adminfaces.starter.model.Examen;
import com.github.adminfaces.starter.model.Note;
import com.github.adminfaces.starter.service.EleveService;
import com.github.adminfaces.starter.service.ExamenService;
import com.github.adminfaces.starter.service.NoteService;
import com.github.adminfaces.template.exception.BusinessException;
import static com.github.adminfaces.template.util.Assert.has;
import org.omnifaces.util.Faces;

import jakarta.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import org.primefaces.event.SelectEvent;

/**
 * @author rmpestano
 */
@Named
@SessionScoped
@BeanService(ExamenService.class)//use annotation instead of setter
public class ExamenBean extends CrudMB<Examen> implements Serializable {

    @Inject
    ExamenService examenService;

    @Inject
    EleveService eleveService;

    @Inject
    NoteService noteService;

    private List<Examen> listeExamen;
    private Date date;

    public void createNewExam() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            entity.setAnnee(Integer.valueOf(entity.getDate().substring(0, 4)));
            this.save();
            Classe classe = this.entity.getDiscipline().getClasse();
            List<Eleve> eleves = eleveService.listeParClasse(classe);
            
            eleves.forEach(e -> {
                Note note = new Note();
                note.setNote(0.0);
                note.setObservation(" ");
                note.setPresence("PRESENT");
                note.setRang(0);
                note.setEleve(e);
                note.setExamen(entity);
                noteService.saveOrUpdate(note);
                System.out.println("Note : " + note.getId());
            });
            
        } catch (BusinessException be) {
            throw be;
        }
    }

    public void toNote() {
        Faces.redirect("note.xhtml");
    }

    public List<Examen> getListeExamen() {
        listeExamen = examenService.liste();
        return listeExamen;
    }

    public void setListeExamen(List<Examen> listeExamen) {
        this.listeExamen = listeExamen;
    }

    public Examen findExamenById(Integer id) {
        if (id == null) {
            throw new BusinessException("Provide Examen ID to load");
        }
        return examenService.findById(id);
    }

    public void delete() {
        int numExamen = 0;
        for (Examen selectedExamen : selectionList) {
            numExamen++;
            examenService.remove(selectedExamen);
        }
        selectionList.clear();
        addDetailMessage(numExamen + " examen deleted successfully!");
        clear();
    }

    public String getSearchCriteria() {
        StringBuilder sb = new StringBuilder(21);

        Discipline disciplineParam = null;
        Examen examenFilter = filter.getEntity();

        Integer idParam = null;
        if (filter.hasParam("id")) {
            idParam = filter.getIntParam("id");
        }

        if (has(idParam)) {
            sb.append("<b>id</b>: ").append(idParam).append(", ");
        }

        if (filter.hasParam("discipline")) {
            disciplineParam = (Discipline) filter.getParam("discipline");
        } else if (has(examenFilter) && examenFilter.getDiscipline() != null) {
            disciplineParam = examenFilter.getDiscipline();
        }

        if (has(disciplineParam)) {
            sb.append("<b>discipline</b>: ").append(disciplineParam.getMatiere().getLibelle()).append(", ");
        }

        int commaIndex = sb.lastIndexOf(",");

        if (commaIndex != -1) {
            sb.deleteCharAt(commaIndex);
        }

        if (sb.toString().trim().isEmpty()) {
            return "No search criteria";
        }

        return sb.toString();
    }

    @Override
    public void afterRemove() {
        try {
            addDetailMsg("Examen " + entity.getType()
                    + " removed successfully");
            clear();
            sessionFilter.clear(ExamenBean.class
                    .getName());//removes filter saved in session for CarListMB.
        } catch (Exception e) {
            log.log(Level.WARNING, "", e);
        }
    }

    @Override
    public void afterInsert() {
        addDetailMsg("Examen " + entity.getType() + " Créé avec succès");
    }

    @Override
    public void afterUpdate() {
        addDetailMsg("Examen " + entity.getType() + " mis à jour");
    }

    public void onRowSelect(SelectEvent event) {
        this.entity = this.selection;
//        this.init();

        System.out.println("Examen : " + entity.getType());
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
