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
import com.github.adminfaces.starter.model.Examen_;
import com.github.adminfaces.starter.model.Matiere;
import com.github.adminfaces.starter.model.Note;
import com.github.adminfaces.starter.service.DisciplineService;
import com.github.adminfaces.starter.service.EleveService;
import com.github.adminfaces.starter.service.ExamenService;
import com.github.adminfaces.starter.service.NoteService;
import com.github.adminfaces.template.exception.BusinessException;
import static com.github.adminfaces.template.util.Assert.has;

import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

@Named
@SessionScoped
@BeanService(NoteService.class)//use annotation instead of setter
public class NoteBean extends CrudMB<Note> implements Serializable {

    @Inject
    AnneeAcademiqueBean anneeAcademiqueBean;

    @Inject
    NoteService noteService;
    @Inject
    EleveService eleveService;
    @Inject
    ExamenService examenService;
    @Inject
    DisciplineService disciplineService;

    private Integer trimestre;
    private AnneeAcademique anneeAcademique;
    private String type;

    private Discipline discipline;
    private Classe classe;
    private Examen examen;
    private Matiere matiere;
    private String date;

    public void initNoteBean() {
        if (FacesContext.getCurrentInstance().isPostback()) {
            return;
        }
//        examen = examenBean.getSelection();
        update();
//        listeNote = noteService.notesParExamen(examen);
    }

    public List<Discipline> autoCompletion(String query) {
        List<Discipline> disciplines = disciplineService.disciplinesParClasse(classe);
        return disciplines;
    }

    public void findOrCreateExamen() {
        Optional<Discipline> disciplineOptional = disciplineService.disciplineParMatiereEtClasse(classe, matiere);
        if (disciplineOptional.isPresent()) {
            discipline = disciplineOptional.get();
            examen = examenService.criteria()
                    .eq(Examen_.discipline, discipline)
                    .eq(Examen_.trimestre, trimestre)
                    .eq(Examen_.type, type)
                    .getOptionalResult();
            if (!has(examen)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                examen = new Examen();
                examen.setAnneeAcademique(classe.getAnneeAcademique());
                examen.setType(type);
                examen.setDate(sdf.format(new Date()));
                examen.setTrimestre(trimestre);
                examen.setDiscipline(discipline);
                examenService.saveOrUpdate(examen);
                addDetailMessage("Epreuve <b>" + type + "</b> Trim <b>" + trimestre + " " + discipline + "</b> n'existait pas mais a été créé.", FacesMessage.SEVERITY_WARN);
            }
            update();
        }else{
            addDetailMessage("Matiere <b>" + matiere + "</b> n'existait pas en  " + classe, FacesMessage.SEVERITY_WARN);
        }
    }

    public void update() {
        if (!has(examen) && has(discipline) && has(trimestre) && has(type)) {
            examen = examenService.criteria()
                    .eq(Examen_.discipline, discipline)
                    .eq(Examen_.trimestre, trimestre)
                    .eq(Examen_.type, type)
                    .getOptionalResult();
        }
        if (has(classe) && has(examen) && examen.hasId()) {
            List<Eleve> eleves = eleveService.listeParClasse(classe);
            if (examen.getNoteCollection().size() != eleves.size()) {
                eleves.forEach(ce -> {
                    entity = new Note();
                    entity.setNote(0.0);
                    entity.setObservation("A MODIFIER");
                    entity.setPresence("PRESENT");
                    entity.setRang(0);
                    entity.setEleve(ce);
                    entity.setExamen(examen);
                    entity.setAnneeAcademique(examen.getAnneeAcademique());
                    try {
                        save();
                    } catch (BusinessException be) {
                        log.logp(Level.WARNING, be.getFieldId(), be.getSummary(), be.getDetail());
                    }
                });

            }
        }

//        if (has(classe)) {
//            if (examen.getNoteCollection().size() != classe.getEleveCollection().size()) {
////                Note notExistent = null;
//                for (Eleve ce : classe.getEleveCollection()) {
//                    Eleve e = ce;
//                    List<Note> notExistent = noteService.listeNoteParExamenEleve(examen, e);
//                    if (notExistent.isEmpty()) {
//                        Note note = new Note();
//                        note.setNote(0.0);
//                        note.setObservation("A MODIFIER");
//                        note.setPresence("PRESENT");
//                        note.setRang(0);
//                        note.setEleve(ce);
//                        note.setAnneeAcademique(examen.getAnneeAcademique());
//                        note.setExamen(examen);
//                        setEntity(note);
//                        super.save();
//                    }
//                }
//            }
//        }
        arrangerParNote();
        filter.getEntity().setExamen(examen);

        if (has(discipline) && has(trimestre) && has(type)) {
            examen = examenService.criteria()
                    .eq(Examen_.discipline, discipline)
                    .eq(Examen_.trimestre, trimestre)
                    .eq(Examen_.type, type)
                    .getOptionalResult();
        }
    }

    public void onExamenClick(Examen e) {
        log.log(Level.INFO, "{0} charg\u00e9 aves succ\u00e8s", e.toString());
        examen = e;
        classe = e.getDiscipline().getClasse();
        trimestre = e.getTrimestre();
        discipline = e.getDiscipline();
        type = e.getType();
        update();
    }

    @Produces
    @Named("nombreEleve")
    public Long nombreEleve() {
        Long nombre = noteService.nombreEleveParExamen(examen);
        return nombre;
    }

    @Produces
    @Named("nombreElevePresent")
    public Long nombreElevePresent() {
        Long nombre = noteService.nombreElevePresentParExamen(examen);
        return nombre;
    }

    @Produces
    @Named("nombreEleveAbsent")
    public Long nombreEleveAbsent() {
        Long nombre = noteService.nombreEleveAbsentParExamen(examen);
        return nombre;
    }

    @Produces
    @Named("nombreEleveMalade")
    public Long nombreEleveMalade() {
        Long nombre = noteService.nombreEleveMaladeParExamen(examen);
        return nombre;
    }

    public void onClasseSelection(SelectEvent event) {
        log.info(event.getObject().toString());
    }

    public void arrangerParNote() {
        Comparator<Note> comp = (o1, o2) -> {
            return o2.getNote().compareTo(o1.getNote());
        };

        int i = 0;
        List<Note> notes = noteService.notesParExamen(examen);

        notes.sort(comp);
        for (Note n : notes) {
            n.setRang(++i);
            noteService.update(n);
        }

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

    public void supprimer(Note note) {
        setEntity(note);
        remove();
        examen.getNoteCollection().remove(note);
//        entity.setNote(0.0);
//        entity.setPresence("ABSENT");
//        save();
        arrangerParNote();
    }

    @Override
    public void afterRemove() {
        try {
            addDetailMsg("Note " + entity
                    + " removed successfully");
            clear();
            sessionFilter.clear(NoteBean.class.getName());//removes filter saved in session for CarListMB.
        } catch (Exception e) {
            log.log(Level.WARNING, "", e);
        }
    }

    @Override
    public void afterInsert() {
        addDetailMsg("Note " + entity + " ajouté ");
    }

    @Override
    public void afterUpdate() {
        addDetailMsg("Note " + entity + " a été mise à jour.");
    }

    public void onRowEdited(RowEditEvent event) {
        Note note = (Note) event.getObject();
        System.out.println("Note : " + note.getId());
        noteService.update(note);
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

    public AnneeAcademique getAnneeAcademique() {
        return anneeAcademique;
    }

    public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
        this.anneeAcademique = anneeAcademique;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Collection<Eleve> listEleveParClasse() {
        return entity.getExamen().getDiscipline().getClasse().getEleveCollection();
    }

    public int nbrNoteParExamen(Examen examen) {
        return noteService.notesParExamen(examen).size();
    }

    public void findExamen(int examenId) {
        examen = examenService.findById(examenId);
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

}
