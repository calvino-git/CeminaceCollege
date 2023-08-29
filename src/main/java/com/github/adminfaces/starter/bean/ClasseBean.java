package com.github.adminfaces.starter.bean;

import com.github.adminfaces.persistence.bean.BeanService;
import com.github.adminfaces.persistence.bean.CrudMB;
import static com.github.adminfaces.persistence.bean.CrudMB.addDetailMsg;
import static com.github.adminfaces.persistence.util.Messages.addDetailMessage;
import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.model.Niveau;
import com.github.adminfaces.starter.service.ClasseService;
import com.github.adminfaces.template.exception.BusinessException;
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
@BeanService(ClasseService.class)//use annotation instead of setter
public class ClasseBean extends CrudMB<Classe> implements Serializable {

    @Inject
    ClasseService classeService;
    @Inject
    AnneeAcademiqueBean anneeAcademiqueBean;

    public void initBean() {
//        this.anneeAcademique =  anneeAcademique;
        filter.getEntity().setAnneeAcademique(anneeAcademiqueBean.getAnneeEnCours());
//        classes = classeService.liste(anneeAcademique);
//        log.log(Level.INFO, "Nombre de classes : {0}", classes.size());
    }
    
    public void onRowSelect(SelectEvent event) {
        this.entity = this.selection;
//        this.init();
        log.log(Level.INFO, "Classe : <b>" + this.selection.getLibelle() + "</b>, Nombre de classes : <b>{0}</b>", this.selection.getEleveCollection().size());
    }

    public List<Classe> autoCompletion(String query) {
        Classe c = new Classe();
        c.setAnneeAcademique(anneeAcademiqueBean.getAnneeEnCours());
        c.setLibelle("%" + query + "%");
        List<Classe> result = classeService.listeParAnnee(c);
        return result;
    }
    
    public void insert() {
        entity.setId(null);
        save();
    }

    public void onSelect(SelectEvent event) {
        selection = (Classe) event.getObject();
    }
    
    public void supprimer(Classe c) {
        classeService.remove(c);
    }

    public void delete() {
        int numClasse = 0;
        for (Classe selectedClasse : selectionList) {
            numClasse++;
            classeService.remove(selectedClasse);
        }
        selectionList.clear();
        addDetailMessage("<b>" + numClasse + "</b> classes suprimées!");
        clear();
    }
    @Override
    public void afterRemove() {
        try {
            addDetailMsg("Classe <b>" + entity.getLibelle() + "</b> supprimée");
            clear();
            sessionFilter.clear(ClasseBean.class.getName());//removes filter saved in session for CarListMB.
        } catch (Exception e) {
            log.log(Level.WARNING, "", e);
        }
    }

    @Override
    public void afterInsert() {
        addDetailMsg("Classe <b>" + entity.getLibelle() + "</b> créée");
    }

    @Override
    public void afterUpdate() {
        addDetailMsg("Classe <b>" + entity.getLibelle() + "</b> mise à jour");
    }
}