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
import com.github.adminfaces.starter.model.Eleve;
import com.github.adminfaces.starter.service.EleveService;
import com.github.adminfaces.template.exception.BusinessException;
import static com.github.adminfaces.template.util.Assert.has;

import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 * @author rmpestano
 */
@Named
@ViewScoped
@BeanService(EleveService.class)//use annotation instead of setter
public class EleveBean extends CrudMB<Eleve> implements Serializable {

    @Inject
    EleveService eleveService;
    @Inject
    AnneeAcademiqueBean anneeAcademiqueBean;

    @PostConstruct
    public void initBean() {
//        init();
        filter.getEntity().setAnneeAcademique(anneeAcademiqueBean.getAnneeEnCours());
        log.log(Level.INFO, "Nombre d''\u00e9l\u00e8ves : {0}",filter.getEntity().getAnneeAcademique() );
    }

    public int nbrEleveParClasse(Classe classe) {
        return eleveService.listeParClasse(classe).size();
    }

    public List<Eleve> elevesParClasse(Classe classe) {
        return eleveService.listeParClasse(classe);
    }
    
    public void supprimer(Eleve e) {
        eleveService.remove(e);
    }

    public void delete() {
        String txt = entity.toString();
        eleveService.remove(entity);
        addDetailMessage(txt + " supprimée avec succès!");
        clear();
    }

    public void insert() {
        entity.setId(null);
        entity.setAnneeAcademique(anneeAcademiqueBean.getAnneeEnCours());
        save();
    }

    @Override
    public void afterRemove() {
        try {
            addDetailMsg("Eleve <b>" + entity.getNom()
                    + "</b> supprimé avec succès");
            clear();
            sessionFilter.clear(EleveBean.class.getName());//removes filter saved in session for CarListMB.
//            classe = classeService.findById(classe.getId());
        } catch (Exception e) {
            log.log(Level.WARNING, "", e);
        }
    }

    @Override
    public void afterInsert() {
        addDetailMsg("Eleve <b>" + entity.getNom()
                    + "</b> créé ");
    }

    @Override
    public void afterUpdate() {
        addDetailMsg("Eleve <b>" + entity.getNom()
                    + "</b> modifié");
//        classe = classeService.findById(classe.getId());
    }

    public void onRowSelect(SelectEvent event) {
        this.entity = this.selection;
//        this.init();

        System.out.println("Eleve : <b>" + entity.getNom()
                    + "</b>");
    }
}