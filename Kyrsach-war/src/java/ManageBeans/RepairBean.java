/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import DAO.RepairDAO;
import Model.Repair;
import Model.Transport;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ceparator
 */
public class RepairBean {

    @EJB
    private RepairDAO repairDAO;

    private Repair repair;

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    @PostConstruct
    private void initializeBean() {
        repair = new Repair();
    }

    public void addNewRepair(Transport tr) throws SQLException, Exception {
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        repair.setDriver(username);
        repair.setIdTransport(tr);
        repairDAO.addRepair(repair);
        //return "/index.xhtml";
    }

    public void deleteRepair(Repair selectedRepair) throws Exception {
        repairDAO.deleteRepair(selectedRepair);
    }

    public List<Repair> getAllRepairs() throws Exception {
        return repairDAO.getAllRepairs();
    }

    public List<Repair> getPendingRepairs() throws Exception {
        return repairDAO.getPendingRepairs();
    }

    public String ToEditRepair(Repair repair) {
        this.repair = repair;
        return "/editRepair.xhtml";
    }

    public String editRepair() throws SQLException, Exception {
        if (repair.getPrice() != null) {
            if (repair.getPrice() > 0) {
                repair.setActualFixDate(Calendar.getInstance().getTime());
            }
        }
        repairDAO.editRepair(repair);
        this.repair = new Repair();
        return "/allRepairs.xhtml";
    }

    public String assignRepair(Repair rp) throws SQLException, Exception {
        rp.setMechanic(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
        repairDAO.editRepair(rp);
        repair = rp;
        return "/editRepair.xhtml";
    }

    public List<Repair> getMyRepairs() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        return repairDAO.getMyRepairs(username);
    }
}
