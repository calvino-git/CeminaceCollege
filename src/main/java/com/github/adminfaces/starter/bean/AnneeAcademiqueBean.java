/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import com.github.adminfaces.persistence.bean.BeanService;
import com.github.adminfaces.persistence.bean.CrudMB;
import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.service.AnneeAcademiqueService;
import com.github.adminfaces.template.exception.BusinessException;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;

/**
 * @author rmpestano
 */
@Named
@SessionScoped
@BeanService(AnneeAcademiqueService.class)//use annotation instead of setter
public class AnneeAcademiqueBean extends CrudMB<AnneeAcademique> implements Serializable {

    @Inject
    AnneeAcademiqueService anneeAcademiqueService;

    private AnneeAcademique anneeEnCours;
    private List<AnneeAcademique> liste;

    @PostConstruct
    public void initBean() {
        init();
        anneeEnCours = anneeAcademiqueService.anneeEnCours();

        log.log(Level.INFO, "Année académique en cours : {0}", anneeEnCours);
    }
    public void onRowSelect(SelectEvent event) {
        this.entity = this.selection;
//        this.init();
        log.log(Level.INFO, "Classe : <b>" + this.selection + "</b>, Nombre de classes : <b>{0}</b>", this.selection);
    }

    public void insert() {
        entity.setId(null);
        save();
    }

    public void supprimer(AnneeAcademique aa) throws BusinessException {
        anneeAcademiqueService.remove(aa);
    }

    public String onChangeSelect(AnneeAcademique anneeAcademique) {
        this.anneeEnCours = anneeAcademique;
        return "index?faces-redirect=true";
    }

    public AnneeAcademique getAnneeEnCours() {
        return anneeEnCours;
    }

    public void setAnneeEnCours(AnneeAcademique anneeEnCours) {
        this.anneeEnCours = anneeEnCours;
    }

    public List<AnneeAcademique> getListe() {
        liste = anneeAcademiqueService.liste();
        return liste;
    }

    public void setListe(List<AnneeAcademique> liste) {
        this.liste = liste;
    }

}
