/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import com.github.adminfaces.persistence.bean.BeanService;
import com.github.adminfaces.persistence.bean.CrudMB;
import static com.github.adminfaces.persistence.bean.CrudMB.addDetailMsg;
import com.github.adminfaces.persistence.service.CrudService;
import com.github.adminfaces.persistence.service.Service;
import static com.github.adminfaces.persistence.util.Messages.addDetailMessage;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.model.Discipline;
import com.github.adminfaces.starter.model.Discipline_;
import com.github.adminfaces.starter.model.Matiere;
import com.github.adminfaces.starter.model.Matiere_;
import com.github.adminfaces.starter.service.DisciplineService;
import com.github.adminfaces.template.exception.BusinessException;
import static com.github.adminfaces.template.util.Assert.has;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;

import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import org.apache.deltaspike.data.impl.criteria.selection.SingularAttributeSelection;
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

    private List<Discipline> listeDiscipline;

    
    @PostConstruct
    public void liste() {
        listeDiscipline = disciplineService.liste().stream().sorted().collect(Collectors.toList());
    }
    
    public List<Discipline> findByLibelle(String query) {
        return disciplineService.getLibelle(query).stream()
                .sorted((o1, o2) -> {
                    String d1 = o1.getClasse().getNiveau().getId() + o1.getClasse().getCode() + o1.getMatiere().getIndex() ;
                    String d2 = o2.getClasse().getNiveau().getId() + o2.getClasse().getCode() + o2.getMatiere().getIndex() ;
                    return d1.compareTo(d2); //To change body of generated lambdas, choose Tools | Templates.
                })
                .collect(Collectors.toList());
    }

    public List<Discipline> getListeDiscipline() {
        return listeDiscipline;
    }

    public void setListeDiscipline(List<Discipline> listeDiscipline) {
        this.listeDiscipline = listeDiscipline;
    }


    public void findDisciplineById(Integer id) {
        if (id == null) {
            throw new BusinessException("Provide Discipline ID to load");
        }
        Discipline disciplineFound = disciplineService.findById(id);
        if (disciplineFound == null) {
            throw new BusinessException(String.format("No discipline found with id %s", id));
        }
        selectionList.add(disciplineFound);
        getFilter().addParam("id", id);
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

    public String getSearchCriteria() {
        StringBuilder sb = new StringBuilder(21);

        Matiere matiereParam = null;
        String enseignantParam = null;
        Classe classeParam = null;
        Discipline disciplineFilter = filter.getEntity();

        Integer idParam = null;
        if (filter.hasParam("id")) {
            idParam = filter.getIntParam("id");
        }

        if (has(idParam)) {
            sb.append("<b>id</b>: ").append(idParam).append(", ");
        }

        if (filter.hasParam("classe")) {
            classeParam = (Classe) filter.getParam("classe");
        } else if (has(disciplineFilter) && disciplineFilter.getClasse() != null) {
            classeParam = disciplineFilter.getClasse();
        }

        if (has(classeParam)) {
            sb.append("<b>classe</b>: ").append(classeParam.getCode()).append(", ");
        }

        if (filter.hasParam("enseignant")) {
            enseignantParam = filter.getStringParam("enseignant");
        } else if (has(disciplineFilter) && disciplineFilter.getEnseignant() != null) {
            enseignantParam = disciplineFilter.getEnseignant();
        }

        if (has(enseignantParam)) {
            sb.append("<b>enseignant</b>: ").append(enseignantParam).append(", ");
        }

        if (filter.hasParam("matiere")) {
            matiereParam = (Matiere) filter.getParam("matiere");
        } else if (has(disciplineFilter) && disciplineFilter.getMatiere() != null) {
            matiereParam = disciplineFilter.getMatiere();
        }

        if (has(matiereParam)) {
            sb.append("<b>matiere</b>: ").append(matiereParam.getCode()).append(", ");
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
        addDetailMsg("Discipline " + entity.getClasse().getCode() + "-" + entity.getMatiere().getCode() + " created successfully");
    }

    @Override
    public void afterUpdate() {
        addDetailMsg("Discipline " + entity.getClasse().getCode() + "-" + entity.getMatiere().getCode() + " updated successfully");
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
