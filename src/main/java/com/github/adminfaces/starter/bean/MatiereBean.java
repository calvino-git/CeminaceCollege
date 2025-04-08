/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.model.Matiere;
import com.github.adminfaces.starter.service.MatiereService;
import org.omnifaces.cdi.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author rmpestano
 */
@Controller//use annotation instead of setter
public class MatiereBean {

    @Autowired
    MatiereService matiereService;

//    @PostConstruct
//    public void initBean() {
//    }
//
//    public void insert() {
//        entity.setId(null);
//        save();
//    }
//
//    public List<String> getSpecialite(String query) {
//        List<String> result = matiereService.getSpecialite(query);
//        return result;
//    }
//
//    public List<Matiere> autoCompletion(String query) {
//        return matiereService.getLibelle(query);
//    }
//
//    public void delete() {
//        int numMatiere = 0;
//        for (Matiere selectedMatiere : selectionList) {
//            numMatiere++;
//            matiereService.remove(selectedMatiere);
//        }
//        selectionList.clear();
//        addDetailMessage(numMatiere + " matiere supprimée");
//        clear();
//    }
//
//    @Override
//    public void afterRemove() {
//        try {
//            addDetailMsg("Matiere " + entity.getLibelle()
//                    + " supprimée");
//            clear();
//            sessionFilter.clear(MatiereBean.class.getName());//removes filter saved in session for CarListMB.
//        } catch (Exception e) {
//            log.log(Level.WARNING, "", e);
//        }
//    }
//
//    @Override
//    public void afterInsert() {
//        addDetailMsg("Matiere " + entity.getLibelle() + " créée");
//    }
//
//    @Override
//    public void afterUpdate() {
//        addDetailMsg("Matiere " + entity.getLibelle() + " mise à jour");
//    }
//
//    public void onRowSelect(SelectEvent event) {
//        this.entity = this.selection;
////        this.init();
//
//        System.out.println("Matiere : " + this.selection.getLibelle());
//    }

}
