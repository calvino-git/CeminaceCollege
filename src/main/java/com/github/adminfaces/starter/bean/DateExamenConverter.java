package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.service.Util;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author calviniloki
 */
@Named
@SessionScoped
public class DateExamenConverter implements Serializable, Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Date date0 = Util.date2string.parse(value);
            String date = Util.string2date.format(date0);
            return date;
        } catch (ParseException ex) {
            return value;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            if (value == null) {
                return null;
            }
            Date date0 = Util.string2date.parse(value.toString());
            String date = Util.date2string.format(date0);
            return date;
        } catch (ParseException ex) {
            return value.toString();
        }
    }
}
