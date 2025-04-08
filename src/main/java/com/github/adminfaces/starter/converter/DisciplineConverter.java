/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.converter;

import com.github.adminfaces.starter.model.Discipline;
import com.github.adminfaces.starter.service.DisciplineService;

import java.io.Serializable;
import java.util.Optional;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * @author calviniloki
 */
@Component
@SessionScope
public class DisciplineConverter implements Serializable, Converter<Discipline> {

    @Autowired
    private DisciplineService disciplineService;

    @Override
    public Discipline getAsObject(FacesContext context, UIComponent component, String code) {
        Optional<Discipline> discipline = disciplineService.findByCode(code);
        return discipline.orElse(null);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Discipline discipline) {
        return discipline != null ? discipline.getCode() : "";
    }
}
