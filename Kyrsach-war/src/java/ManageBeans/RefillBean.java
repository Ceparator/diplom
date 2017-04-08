/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import DAO.RefillDAO;
import Model.Refill;
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
public class RefillBean {

    @EJB
    private RefillDAO refillDAO;

    private Refill refill;
    private Transport transport;

    public Refill getRefill() {
        return refill;
    }

    public void setRefill(Refill refill) {
        this.refill = refill;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }
    
    

    @PostConstruct
    private void initializeBean() {
        refill = new Refill();
    }

    public String addNewRefill() throws SQLException, Exception {
        refill.setIdFuel(transport.getIdFuel());
        refill.setIdTransport(transport);
        refill.setMechanic(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
        refill.setRefillDate(Calendar.getInstance().getTime());
        refill.setCost(refill.getVolume()*transport.getIdFuel().getFuelPrice());
        refillDAO.addRefill(refill);
        return "/allRefills.xhtml";
    }

    public void deleteRefill(Refill selectedRefill) throws Exception {
        refillDAO.deleteRefill(selectedRefill);
    }

    public List<Refill> getAllRefills() throws Exception {
        return refillDAO.getAllRefills();
    }

    public String ToEditRefill(Refill refill) {
        this.refill = refill;
        return "/editRefill.xhtml";
    }

    public String editRefill() throws SQLException, Exception {
        refillDAO.editRefill(refill);
        this.refill = new Refill();
        return "/allRefills.xhtml";
    }

    public String ToRefillTransport(Transport transp) {
        transport = transp;
        return "/refillTransport.xhtml";
    }

}
