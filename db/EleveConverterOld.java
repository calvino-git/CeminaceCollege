/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.model.Eleve;
import com.github.adminfaces.starter.service.EleveService;
import java.io.Serializable;
import java.util.List;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 *
 * @author calviniloki
 */
@Named
@SessionScoped
public class EleveConverter implements Serializable, Converter {

    @Inject
    private EleveService eleveService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<Eleve> eleves = eleveService.liste();
        for (Eleve eleve : eleves) {
            if (eleve.toString().equals(value)) {
                return eleve;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Eleve) value).toString();
    }
}
