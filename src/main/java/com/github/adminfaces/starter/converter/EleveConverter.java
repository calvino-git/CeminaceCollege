/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.converter;

import com.github.adminfaces.starter.bean.AnneeAcademiqueBean;
import com.github.adminfaces.starter.model.Discipline;
import com.github.adminfaces.starter.model.Eleve;
import com.github.adminfaces.starter.service.EleveService;
import java.io.Serializable;
import java.util.Optional;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 *
 * @author calviniloki
 */
@Component
@SessionScope
public class EleveConverter implements Converter<Eleve> {

    @Autowired
    private EleveService eleveService;

    @Override
    public Eleve getAsObject(FacesContext context, UIComponent component, String code) {
        Optional<Eleve> optional = eleveService.findByCode(code);
        return optional.orElse(null);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Eleve eleve) {
        return eleve != null ? eleve.getCode() : null;
    }
}
