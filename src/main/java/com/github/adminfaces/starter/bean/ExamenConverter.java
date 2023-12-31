/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.model.Examen;
import com.github.adminfaces.starter.service.ExamenService;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author calviniloki
 */
@Named
@SessionScoped
public class ExamenConverter implements Serializable, Converter {

    @Inject
    private ExamenService examenService;
    @Inject
    AnneeAcademiqueBean anneeAcademiqueBean;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<Examen> examens = examenService.liste(anneeAcademiqueBean.getAnneeEnCours());
        for (Examen examen : examens) {
            if (examen.toString().equals(value)) {
                return examen;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Examen) value).toString();
    }
}
