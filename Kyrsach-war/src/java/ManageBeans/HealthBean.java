/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import DAO.HealthDAO;
import Model.Health;
import Model.Routelist;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.icefaces.ace.model.table.RowStateMap;

/**
 *
 * @author Ceparator
 */
public class HealthBean {

    @EJB
    private HealthDAO healthDAO;

    private Health health;

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }
    private RowStateMap stateMap;

    public RowStateMap getStateMap() {
        return stateMap;
    }

    public void setStateMap(RowStateMap stateMap) {
        this.stateMap = stateMap;
    }

    public void actionListener(ActionEvent event) throws Exception {
        List<Health> selectedHealthList = (List<Health>) stateMap.getSelected();
        healthDAO.deleteHealth(selectedHealthList);
    }

    @PostConstruct
    private void initializeBean() {
        health = new Health();
    }

    public String addNewHealth(String driver, boolean valid) throws SQLException, Exception {
        health.setDoctor(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
        health.setDriver(driver);
        health.setQualified(valid);
        health.setVremya(Calendar.getInstance().getTime());
        healthDAO.addHealth(health);
        return "/allHealths.xhtml";
    }

    public List<Health> getAllHealths() throws Exception {
        return healthDAO.getAllHealths();
    }

    public List<String> getDoctorHealths() throws Exception {
        return healthDAO.getDoctorHealths();
    }

    public String ToEditHealth(Health health) {
        this.health = health;
        return "/editHealth.xhtml";
    }

    public String editHealth() throws SQLException, Exception {
        healthDAO.editHealth(health);
        this.health = new Health();
        return "/allHealths.xhtml";
    }

}
