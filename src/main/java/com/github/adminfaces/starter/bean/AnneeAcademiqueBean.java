/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.service.AnneeAcademiqueService;
import com.github.adminfaces.template.exception.BusinessException;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * @author rmpestano
 */
@Controller//use annotation instead of setter
public class AnneeAcademiqueBean implements Serializable {
    private final AnneeAcademiqueService anneeAcademiqueService;
    Logger log = LoggerFactory.getLogger(AnneeAcademiqueBean.class);

    private AnneeAcademique anneeEnCours;
    private List<AnneeAcademique> liste;

    public AnneeAcademiqueBean(AnneeAcademiqueService anneeAcademiqueService) {
        this.anneeAcademiqueService = anneeAcademiqueService;
    }

    @PostConstruct
    public void initBean() {
        Optional<AnneeAcademique> annee = anneeAcademiqueService.getCurrentAnneeAcademaique();
        log.info("Année académique en cours :" + annee.get());
    }
//    public void onRowSelect(SelectEvent event) {
////        this.entity = this.selection;
////        this.init();
////        log.info( "Classe : <b>" + this.selection + "</b>, Nombre de classes : <b>{0}</b>", this.selection);
//    }

    public void supprimer(AnneeAcademique aa) throws BusinessException {
//        anneeAcademiqueService.remove(aa);
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
