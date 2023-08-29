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
import com.github.adminfaces.starter.service.DisciplineService;
import com.github.adminfaces.starter.service.ExamenService;
import com.github.adminfaces.starter.service.NoteService;
import com.github.adminfaces.template.exception.BusinessException;
import static com.github.adminfaces.template.util.Assert.has;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import java.io.IOException;

import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.SortOrder;

/**
 * @author rmpestano
 */
@Named
@ViewScoped
@BeanService(NoteService.class)//use annotation instead of setter
public class NoteBean extends CrudMB<Note> implements Serializable {

    @Inject
    NoteService noteService;
    @Inject
    ExamenService examenService;
    @Inject
    DisciplineService disciplineService;

    private List<Note> listeNote;
    private List<Examen> examenClasse;
    private List<Examen> listExamen;

    private Integer trimestre;
    private Integer annee;
    private int currentExamenId;
    private String type;

    private Discipline discipline;
    private Classe classe;
    private Examen examen;

    @PostConstruct
    public void initNoteBean() {
        if (FacesContext.getCurrentInstance().isPostback()) {
            return;
        }
//        examen = currentExamenId != 0 ? examenService.findById(currentExamenId) : null;
        log.log(Level.CONFIG, "Examen ID : {0}", currentExamenId);
        update();
        listeNote = noteService.listeParExamen(examen);
    }

    @Override
    public Note save() {
        Note note = super.save();
        update();
        return note;
    }

    public void onClasseSelection(SelectEvent event){
        log.info(event.getObject().toString());
    }
    
    public void refreshExam(SelectEvent event) {
        Integer trim = (Integer) event.getObject();
        examenClasse = examenService.listeParClasseTrimestre(classe, trim, annee);
    }

    public void arrangerParNote() {
//        entity.setExamen(exa);
//        filter.setEntity(entity);
//        list.setFilter(filter);
//        List<Note> liste = list.load(0, list.getRowCount(), "rang", SortOrder.ASCENDING, null);

        Comparator<Note> comp = (o1, o2) -> {
            return o2.getNote().compareTo(o1.getNote());
        };

        int i = 0;
        listeNote.sort(comp);
        for (Note n : listeNote) {
            n.setRang(++i);
            noteService.update(n);
        }

    }

    public Collection<Eleve> listEleveParClasse() {
        return entity.getExamen().getDiscipline().getClasse().getEleveCollection();
    }

    public int nbrNoteParExamen(Examen examen) {
        return noteService.listeParExamen(examen).size();
    }

    public void findExamen(int examenId) {
        examen = examenService.findById(examenId);
    }

    public void findOrCreateExamen() {
        listExamen = examenService.listeParDisciplineTrimestreAnnee(discipline, trimestre, annee, type);
        if (listExamen.isEmpty()) {
            if (!has(examen)) {
                examen = new Examen();
            }
            if (examen.hasId()) {
                examen.setId(null);
                examen.setDate(null);
                examen.setNoteCollection(null);
            }

            examen.setAnnee(annee);
            examen.setType(type);
            examen.setTrimestre(trimestre);
            examen.setDiscipline(discipline);

            if (examen.hasDate()) {
                examenService.saveOrUpdate(examen);
                listExamen.add(examen);
            } else {
                PrimeFaces.current().executeScript("PF('examenDialog').show()");
            }
        } else if (listExamen.size() == 1) {
            examen = listExamen.get(0);
            update();
        } else {
            PrimeFaces.current().executeScript("PF('examenDialog').show()");
        }
    }

    public List<Discipline> findDisciplineClasse(String query) {
        List<Discipline> disciplines = disciplineService.getLibelle(query);
        return disciplines.stream()
                .filter(d -> d.getClasse().equals(classe))
                .collect(Collectors.toList());
    }

    public void update() {
        if (has(classe) && has(annee) && has(trimestre) && has(examen) && examen.hasId()) {
            if (examen.getNoteCollection().isEmpty()) {
                classe.getEleveCollection().forEach(e -> {
                    Note note = new Note();
                    note.setNote(0.0);
                    note.setObservation("A MODIFIER");
                    note.setPresence("PRESENT");
                    note.setRang(0);
                    note.setEleve(e);
                    note.setExamen(examen);
                    try {
                        noteService.insert(note);
                    } catch (BusinessException ex) {
                    }
                });

            }
        }
        if (examen != null && examen.getId() != null && examen.getId() != 0) {
            if ((examen.getId() == currentExamenId && currentExamenId != 0) || (examen.getId() != currentExamenId && currentExamenId == 0)) {
                entity.setExamen(examen);
                filter.setEntity(entity);
            }
            if (examen.getId() != currentExamenId && currentExamenId != 0) {
                examen = examenService.findById(currentExamenId);
                entity.setExamen(examen);
                filter.setEntity(entity);
            }
        } else {
            if (currentExamenId != 0) {
                examen = examenService.findById(currentExamenId);
                entity.setExamen(examen);
                filter.setEntity(entity);
                discipline = examen.getDiscipline();
                classe = discipline.getClasse();
                annee = examen.getAnnee();
                trimestre = examen.getTrimestre();
                type = examen.getType();

            }
        }

        if (has(classe)) {
            if (examen.getNoteCollection().size() != classe.getEleveCollection().size()) {
                Optional notExistent = null;
                for (Eleve e : classe.getEleveCollection()) {
                    notExistent = examen.getNoteCollection().stream()
                            .filter(n -> n.getEleve().equals(e))
                            .findAny();
                    if (!notExistent.isPresent()) {
                        Note note = new Note();
                        note.setNote(0.0);
                        note.setObservation("A MODIFIER");
                        note.setPresence("PRESENT");
                        note.setRang(0);
                        note.setEleve(e);
                        note.setExamen(examen);
                        try {
                            noteService.insert(note);
                        } catch (BusinessException ex) {
                        }
                    }
                }
            }
            filter.setFirst(0).setPageSize(classe.getEffectifTotal());
            listeNote = noteService.paginate(filter);
            arrangerParNote();
        }

    }

    public void findNoteById(Integer id) {
        if (id == null) {
            throw new BusinessException("Provide Note ID to load");
        }
        Note noteFound = noteService.findById(id);
        if (noteFound == null) {
            throw new BusinessException(String.format("No note found with id %s", id));
        }
        selectionList.add(noteFound);
        getFilter().addParam("id", id);
    }

    public void delete() {
        int numNote = 0;
        for (Note selectedNote : selectionList) {
            numNote++;
            noteService.remove(selectedNote);
        }
        selectionList.clear();
        addDetailMessage(numNote + " notex deleted successfully!");
        clear();
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        Rectangle rectangle = new Rectangle(PageSize.A4.getTop(), PageSize.A4.getRight());
        pdf.setPageSize(rectangle);

        pdf.open();
        String titre = entity.getExamen().getType() + "-"
                + entity.getExamen().getDiscipline().getMatiere().getLibelle()
                + " Trimestre " + entity.getExamen().getTrimestre();

        Phrase phrase = new Phrase(titre);
        Paragraph paragraph = new Paragraph(phrase);
        paragraph.setAlignment("center");
        pdf.add(paragraph);
        pdf.add(new Paragraph(" "));
        pdf.addTitle(titre);
    }

    public void supprimer(Note note) {
        setEntity(note);
        remove();
        examen.getNoteCollection().remove(note);
//        entity.setNote(0.0);
//        entity.setPresence("ABSENT");
//        save();
        arrangerParNote();
    }

    public String getSearchCriteria() {
        StringBuilder sb = new StringBuilder(21);

        Examen examenParam = null;
        Note noteFilter = filter.getEntity();

        Integer idParam = null;
        if (filter.hasParam("id")) {
            idParam = filter.getIntParam("id");
        }

        if (has(idParam)) {
            sb.append("<b>id</b>: ").append(idParam).append(", ");
        }

        if (filter.hasParam("examen")) {
            examenParam = (Examen) filter.getParam("examen");
        } else if (has(noteFilter) && noteFilter.getExamen() != null) {
            examenParam = noteFilter.getExamen();
        }

        if (has(examenParam)) {
            sb.append("<b>examen</b>: ").append(examenParam.getDiscipline().getMatiere().getLibelle()).append(", ");
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
            addDetailMsg("Note " + entity.getId()
                    + " removed successfully");
            clear();
            sessionFilter.clear(NoteBean.class.getName());//removes filter saved in session for CarListMB.
        } catch (Exception e) {
            log.log(Level.WARNING, "", e);
        }
    }

    @Override
    public void afterInsert() {
        addDetailMsg("Note " + entity.getId() + " created successfully");
    }

    @Override
    public void afterUpdate() {
        addDetailMsg("Note " + entity.getId() + " a été mise à jour.");
    }

    public void onRowEdited(RowEditEvent event) {
        Note note = (Note) event.getObject();
        System.out.println("Note : " + note.getId());
        noteService.update(note);
    }

    public int getCurrentExamenId() {
        return currentExamenId;
    }

    public void setCurrentExamenId(int currentExamenId) {
        this.currentExamenId = currentExamenId;
    }

    public List<Note> getListeNote() {
        return listeNote;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public Integer getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Integer trimestre) {
        this.trimestre = trimestre;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public List<Examen> getExamenClasse() {
        return examenClasse;
    }

    public void setExamenClasse(List<Examen> examenClasse) {
        this.examenClasse = examenClasse;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Examen> getListExamen() {
        return listExamen;
    }

    public void setListExamen(List<Examen> listExamen) {
        this.listExamen = listExamen;
    }

}
