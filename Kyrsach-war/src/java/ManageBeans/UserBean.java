/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import DAO.MyuserDAOInterface;
import Model.Myuser;
import Model.MyuserRole;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Ceparator
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    @EJB
    private MyuserDAOInterface userDAO;

    private String username;
    private String oldUsername;
    private String oldPassword;
    private String password;
    private String role;
    private String newPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOldUsername() {
        return oldUsername;
    }

    public void setOldUsername(String oldUsername) {
        this.oldUsername = oldUsername;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @PostConstruct
    private void initializeBean() {
        this.role = "guest";
    }

    public String logout() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    public String addNewUser() throws SQLException, Exception {
        userDAO.addUser(username, password, role);
        username = "";
        password = "";
        role = "guest";
        if ("guest".equals(userDAO.getUserRole(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName()))) {
            return "/index.xhtml";
        } else {
            return "/allUsers.xhtml";
        }
    }

    public String toEditUser(Myuser user) throws SQLException, Exception {
        username = user.getUsername();
        oldUsername = username;
        role = userDAO.getUserRole(user.getUsername());
        return "/editUser.xhtml";
    }

    public String editUser() throws SQLException, Exception {
        userDAO.editUser(username, role, oldUsername);
        username = "";
        role = "guest";
        oldUsername = "";
        if ("guest".equals(userDAO.getUserRole(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName()))) {
            return "/index.xhtml";
        } else {
            return "/allUsers.xhtml";
        }
    }

    public String deleteUser(String usn) throws SQLException, Exception {
        userDAO.deleteUser(usn);
        return "/allUsers.xhtml";
    }

    public List<Myuser> getAllUsers() throws Exception {
        return userDAO.getAllUsers();
    }

    public List<MyuserRole> getUsersByRole(String userrole) throws Exception {
        return userDAO.getUsersByRole(userrole);
    }

    public String toChangePassword() throws SQLException, Exception {
        username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        return "/changePassword.xhtml";
    }

    public String changePassword(String usernm) throws SQLException, Exception {
        userDAO.changePassword(usernm, password);
        username = "";
        password = "";
        return "/index.xhtml";
    }
}
