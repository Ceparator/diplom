/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validators;

import DAO.MyuserDAO;
import DAO.MyuserDAOInterface;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Ceparator
 */
@RequestScoped
@FacesValidator(value = "oldPasswordValidator")
public class OldPasswordValidator implements Validator {

    @EJB
    private MyuserDAOInterface myuserDAO;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String usernm = ec.getRequestParameterMap().get("formId:usernm");

        String oldPassword = (String) value;
        try {
            if (!myuserDAO.correctOldPassword(usernm, oldPassword)) {           
                throw new ValidatorException(new FacesMessage("Wrong old password"));
            }
        } catch (Exception ex) {
            throw new ValidatorException(new FacesMessage("Wrong old password"));
        }

    }

}
