/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.model.Examen;
import jakarta.el.ELContext;
import jakarta.el.ValueExpression;
import jakarta.faces.application.Application;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.event.ActionListener;

/**
 *
 * @author calviniloki
 */
public class ExamenAction implements ActionListener {
    @Override
    public void processAction(ActionEvent ev) throws AbortProcessingException {
        FacesContext context = FacesContext.getCurrentInstance();
        Application app = context.getApplication();
        Examen ex = (Examen) app.evaluateExpressionGet(context, "#{ex}",
                Examen.class);
        ELContext elContext = context.getELContext();
        ValueExpression ve = app.getExpressionFactory().createValueExpression(
                elContext, "#{noteBean.exa}", Examen.class);
        ve.setValue(elContext, ex);
    }
}
