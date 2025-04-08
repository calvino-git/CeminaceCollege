/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.converter;

import com.github.adminfaces.starter.bean.AnneeAcademiqueBean;
import com.github.adminfaces.starter.model.Examen;
import com.github.adminfaces.starter.service.ExamenService;

import java.io.Serializable;
import java.util.List;
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

import static com.github.adminfaces.template.util.Assert.has;

/**
 * @author calviniloki
 */
@Component
@SessionScope
public class ExamenConverter implements Serializable, Converter<Examen> {

    @Autowired
    private ExamenService examenService;

    @Override
    public Examen getAsObject(FacesContext context, UIComponent component, String code) {
        Optional<Examen> optional = examenService.findByCode(code);
        return optional.orElse(null);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Examen examen) {
        return has(examen) ? examen.getCode() : null;
    }
}
