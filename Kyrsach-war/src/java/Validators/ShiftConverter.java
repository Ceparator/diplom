/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validators;

import DAO.ShiftDAO;
import Model.Shift;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author Ceparator
 */
public class ShiftConverter implements Converter {

    @EJB
    ShiftDAO shiftDAO;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return shiftDAO.findShiftById(Integer.valueOf(value));
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid shift ID", value)), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        if (value instanceof Shift) {
            return String.valueOf(((Shift) value).getIdShift());
        } else {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid shift", value)));
        }
    }

}
