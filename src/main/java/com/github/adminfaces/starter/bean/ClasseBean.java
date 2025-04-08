package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.service.AnneeAcademiqueService;
import com.github.adminfaces.starter.service.ClasseService;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rmpestano
 */
@Controller
@RequestMapping("/classe")//use annotation instead of setter
public class ClasseBean implements Serializable {

    @Autowired
    ClasseService classeService;
    @Autowired
    AnneeAcademiqueService anneeAcademiqueService;

    public List<Classe> autoCompletion(String query) {
        AnneeAcademique anneeAcademique = anneeAcademiqueService.getCurrentAnneeAcademaique().get();
        List<Classe> result = classeService.findClassseByAnneeAcademiqueAndLibelleLike(anneeAcademique, query);
        return result;
    }

//    @Inject
//    AnneeAcademiqueBean anneeAcademiqueBean;

//    public void initBean() {
////        this.anneeAcademique =  anneeAcademique;
//        filter.getEntity().setAnneeAcademique(anneeAcademiqueBean.getAnneeEnCours());
////        classes = classeService.liste(anneeAcademique);
////        log.log(Level.INFO, "Nombre de classes : {0}", classes.size());
//    }
    
//    public void onRowSelect(SelectEvent event) {
//        this.entity = this.selection;
////        this.init();
//        log.log(Level.INFO, "Classe : <b>" + this.selection.getLibelle() + "</b>, Nombre de classes : <b>{0}</b>", this.selection.getEleveCollection().size());
//    }

    
//    public void insert() {
//        entity.setId(null);
//        save();
//    }
//
//    public void onSelect(SelectEvent event) {
//        selection = (Classe) event.getObject();
//    }
//
//    public void supprimer(Classe c) {
//        classeService.remove(c);
//    }
//
//    public void delete() {
//        int numClasse = 0;
//        for (Classe selectedClasse : selectionList) {
//            numClasse++;
//            classeService.remove(selectedClasse);
//        }
//        selectionList.clear();
//        addDetailMessage("<b>" + numClasse + "</b> classes suprimées!");
//        clear();
//    }
//    @Override
//    public void afterRemove() {
//        try {
//            addDetailMsg("Classe <b>" + entity.getLibelle() + "</b> supprimée");
//            clear();
//            sessionFilter.clear(ClasseBean.class.getName());//removes filter saved in session for CarListMB.
//        } catch (Exception e) {
//            log.log(Level.WARNING, "", e);
//        }
//    }
//
//    @Override
//    public void afterInsert() {
//        addDetailMsg("Classe <b>" + entity.getLibelle() + "</b> créée");
//    }
//
//    @Override
//    public void afterUpdate() {
//        addDetailMsg("Classe <b>" + entity.getLibelle() + "</b> mise à jour");
//    }
}