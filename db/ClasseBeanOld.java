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
import com.github.adminfaces.starter.model.Niveau;
import com.github.adminfaces.starter.service.ClasseService;
import com.github.adminfaces.template.exception.BusinessException;
import static com.github.adminfaces.template.util.Assert.has;
import org.omnifaces.cdi.ViewScoped;

import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;

/**
 * @author rmpestano
 */
@Named
@SessionScoped
@BeanService(ClasseService.class)//use annotation instead of setter
public class ClasseBean extends CrudMB<Classe> implements Serializable {

    @Inject
    ClasseService classeService;
    
    private List<Classe> classes;
    
    public void initBean(){
        classes = classeService.liste();
        log.log(Level.INFO, "Nombre de classes : {0}", classes.size());
    }

    public List<Classe> findByLibelle(String query) {
        List<Classe> result = classeService.getLibelle(query);
        return result;
    }
    
    public void onSelect(SelectEvent event) {
        selection = (Classe) event.getObject();
    }

    public List<Niveau> listeNiveau() {
        return classeService.listeNiveau();
    }

    public void findClasseById(Integer id) {
        if (id == null) {
            throw new BusinessException("Provide Classe ID to load");
        }
        Classe classeFound = classeService.findById(id);
        if (classeFound == null) {
            throw new BusinessException(String.format("No classe found with id %s", id));
        }
        selectionList.add(classeFound);
        getFilter().addParam("id", id);
    }

    public void delete() {
        int numClasse = 0;
        for (Classe selectedClasse : selectionList) {
            numClasse++;
            classeService.remove(selectedClasse);
        }
        selectionList.clear();
        addDetailMessage(numClasse + " classe deleted successfully!");
        clear();
    }

    public String getSearchCriteria() {
        StringBuilder sb = new StringBuilder(21);

        String codeParam = null;
        Classe classeFilter = filter.getEntity();

        Integer idParam = null;
        if (filter.hasParam("id")) {
            idParam = filter.getIntParam("id");
        }

        if (has(idParam)) {
            sb.append("<b>id</b>: ").append(idParam).append(", ");
        }

        if (filter.hasParam("code")) {
            codeParam = filter.getStringParam("code");
        } else if (has(classeFilter) && classeFilter.getCode() != null) {
            codeParam = classeFilter.getCode();
        }

        if (has(codeParam)) {
            sb.append("<b>code</b>: ").append(codeParam).append(", ");
        }

        String libelleParam = null;
        if (filter.hasParam("libelle")) {
            libelleParam = filter.getStringParam("libelle");
        } else if (has(classeFilter) && classeFilter.getLibelle() != null) {
            libelleParam = classeFilter.getLibelle();
        }

        if (has(libelleParam)) {
            sb.append("<b>libelle</b>: ").append(libelleParam).append(", ");
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
            addDetailMsg("Classe " + entity.getLibelle() + " supprimée");
            clear();
            sessionFilter.clear(ClasseBean.class.getName());//removes filter saved in session for CarListMB.
        } catch (Exception e) {
            log.log(Level.WARNING, "", e);
        }
    }

    @Override
    public void afterInsert() {
        addDetailMsg("Classe " + entity.getLibelle() + " créée");
    }

    @Override
    public void afterUpdate() {
        addDetailMsg("Classe " + entity.getLibelle() + " mise à jour");
    }

    public void onRowSelect(SelectEvent event) {
        this.entity = this.selection;
//        this.init();
        log.log(Level.INFO, "Classe : " + this.selection.getLibelle() + ", Nombre de classes : {0}",this.selection.getEleveCollection().size());
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }
    

    
    

}
