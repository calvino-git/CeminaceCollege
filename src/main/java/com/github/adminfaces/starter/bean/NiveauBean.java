/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.service.NiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rmpestano
 */
@Controller
@RequestMapping("/niveau")//use annotation instead of setter
public class NiveauBean {

    @Autowired
    NiveauService niveauService;

//    private List<Niveau> listeNiveaux;
//
//    @PostConstruct
//    public void initBean() {
//        listeNiveaux = niveauService.liste();
//    }
//
//    public List<String> completeCycle(String query) {
//        List<String> result = niveauService.getCycles(query);
//        return result;
//    }
//
//    public void findNiveauById(Integer id) {
//        if (id == null) {
//            throw new BusinessException("Provide Niveau ID to load");
//        }
//        Niveau niveauFound = niveauService.findById(id);
//        if (niveauFound == null) {
//            throw new BusinessException(String.format("No niveau found with id %s", id));
//        }
//        selectionList.add(niveauFound);
//        getFilter().addParam("id", id);
//    }
//
//    public String getSearchCriteria() {
//        StringBuilder sb = new StringBuilder(21);
//
//        String titreParam = null;
//        Niveau niveauFilter = filter.getEntity();
//
//        Integer idParam = null;
//        if (filter.hasParam("id")) {
//            idParam = filter.getIntParam("id");
//        }
//
//        if (has(idParam)) {
//            sb.append("<b>id</b>: ").append(idParam).append(", ");
//        }
//
//        if (filter.hasParam("titre")) {
//            titreParam = filter.getStringParam("titre");
//        } else if (has(niveauFilter) && niveauFilter.getTitre() != null) {
//            titreParam = niveauFilter.getTitre();
//        }
//
//        if (has(titreParam)) {
//            sb.append("<b>titre</b>: ").append(titreParam).append(", ");
//        }
//
//        String cycleParam = null;
//        if (filter.hasParam("cycle")) {
//            cycleParam = filter.getStringParam("cycle");
//        } else if (has(niveauFilter) && niveauFilter.getCycle() != null) {
//            cycleParam = niveauFilter.getCycle();
//        }
//
//        if (has(cycleParam)) {
//            sb.append("<b>cycle</b>: ").append(cycleParam).append(", ");
//        }
//        int commaIndex = sb.lastIndexOf(",");
//
//        if (commaIndex != -1) {
//            sb.deleteCharAt(commaIndex);
//        }
//
//        if (sb.toString().trim().isEmpty()) {
//            return "No search criteria";
//        }
//
//        return sb.toString();
//    }
//
//    @Override
//    public void afterRemove() {
//        try {
//            addDetailMsg("Niveau " + entity.getTitre()
//                    + " removed successfully");
//            clear();
//            sessionFilter.clear(NiveauBean.class.getName());//removes filter saved in session for CarListMB.
//        } catch (Exception e) {
//            log.log(Level.WARNING, "", e);
//        }
//    }
//
//    @Override
//    public void afterInsert() {
//        addDetailMsg("Niveau " + entity.getTitre() + " created successfully");
//    }
//
//    @Override
//    public void afterUpdate() {
//        addDetailMsg("Niveau " + entity.getTitre() + " updated successfully");
//    }
//
//    public void onRowSelect(SelectEvent event) {
//        this.entity = this.selection;
////        this.init();
//
//        System.out.println("Niveau : " + this.selection.getTitre());
//    }

}
