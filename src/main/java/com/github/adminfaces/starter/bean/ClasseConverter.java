/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.service.ClasseService;
import static com.github.adminfaces.template.util.Assert.has;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
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
public class ClasseConverter implements Serializable, Converter {

    @Inject
    private ClasseService classeService;
    @Inject
    AnneeAcademiqueBean anneeAcademiqueBean;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Optional<Classe> optonalClasse = classeService.classeParAnneeEtLibelle(anneeAcademiqueBean.getAnneeEnCours(), value);
        return optonalClasse.orElse(null);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return has(value) ? ((Classe) value).getLibelle() : null;
    }
}
