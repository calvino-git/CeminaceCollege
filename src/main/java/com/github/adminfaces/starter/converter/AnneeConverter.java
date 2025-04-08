/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.converter;

import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.service.AnneeAcademiqueService;

import java.io.Serializable;
import java.util.List;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author calviniloki
 */
@Named
@SessionScoped
public class AnneeConverter implements Serializable, Converter {

    @Autowired
    private AnneeAcademiqueService anneeAcademiqueService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<AnneeAcademique> annees = anneeAcademiqueService.liste();
        for (AnneeAcademique annee : annees) {
            if (annee.getAnnee().equals(value)) {
                return annee;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((AnneeAcademique) value).getAnnee();
    }
}
