/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.converter;

import com.github.adminfaces.starter.model.Niveau;
import com.github.adminfaces.starter.service.NiveauService;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import static com.github.adminfaces.template.util.Assert.has;

/**
 *
 * @author calviniloki
 */
@Component
@SessionScope
public class NiveauConverter implements Serializable, Converter<Niveau> {

    @Inject
    private NiveauService niveauService;

    @Override
    public Niveau getAsObject(FacesContext context, UIComponent component, String code) {
        Optional<Niveau> optional = niveauService.findByCode(code);
        return optional.orElse(null);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Niveau niveau) {
        return has(niveau) ? niveau.getCode() : "";
    }
}
