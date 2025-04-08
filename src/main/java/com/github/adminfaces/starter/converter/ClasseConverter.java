/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.converter;

import com.github.adminfaces.starter.bean.AnneeAcademiqueBean;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.service.ClasseService;
import static com.github.adminfaces.template.util.Assert.has;
import java.io.Serializable;
import java.util.Optional;
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
public class ClasseConverter implements Serializable, Converter {

    @Inject
    private ClasseService classeService;
    @Inject
    AnneeAcademiqueBean anneeAcademiqueBean;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Optional<Classe> optonalClasse = classeService.findClasseByAnneeAcademiqueAndLibelle(anneeAcademiqueBean.getAnneeEnCours(), value);
        return optonalClasse.orElse(null);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return has(value) ? ((Classe) value).getLibelle() : null;
    }
}
