/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.converter;

import com.github.adminfaces.starter.model.Matiere;
import com.github.adminfaces.starter.service.MatiereService;
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
 *
 * @author calviniloki
 */
@Component
@SessionScope
public class MatiereConverter implements Serializable, Converter<Matiere> {

    @Autowired
    private MatiereService matiereService;

    @Override
    public Matiere getAsObject(FacesContext context, UIComponent component, String code) {
        Optional<Matiere> optional = matiereService.findByCode(code);
        return optional.orElse(null);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Matiere matiere) {
        return has(matiere)?matiere.getCode():"";
    }
}
