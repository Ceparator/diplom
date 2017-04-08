/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validators;

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
@FacesValidator(value = "newPasswordValidator")
public class NewPasswordValidator implements Validator {

    @EJB
    private MyuserDAOInterface myuserDAO;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String oldPassword = ec.getRequestParameterMap().get("formId:newpass");

        String newPassword = (String) value;
        if (!oldPassword.equals(newPassword)) {
            throw new ValidatorException(new FacesMessage("New passwords are not matching"));
        }
    }
}
