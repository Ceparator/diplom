/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validators;

import DAO.TransportDAO;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Ceparator
 */
public class TransportValidator implements Validator{

    @EJB
    private TransportDAO transportDAO;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        String serialNumber = (String) value;
        if (transportDAO.findTransportBySerialNumber(serialNumber) == null) {
            throw new ValidatorException(new FacesMessage("There is no transport with this serial number"));
        }
    }
}
