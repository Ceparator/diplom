/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import DAO.MyuserDAO;
import DAO.MyuserDAOInterface;
import Model.Myuser;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ceparator
 */
@Named
@RequestScoped
public class AddUserBean {

    @EJB
    private MyuserDAOInterface userDAO;

    private String username;
    private String password;
    private String role;
    private int routeNumber;

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

    public int getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(int routeNumber) {
        this.routeNumber = routeNumber;
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
        if ("guest".equals(role)){
            routeNumber = 0;
        }
        userDAO.addUser(username, password, role);
        return "/index.xhtml";
    }
}
