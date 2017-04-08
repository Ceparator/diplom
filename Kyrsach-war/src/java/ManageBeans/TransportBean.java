/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import DAO.FuelDAO;
import DAO.TransportDAO;
import Model.Refill;
import Model.Repair;
import Model.Transport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ceparator
 */
@SessionScoped
@ManagedBean(name = "transportBean")
public class TransportBean {

    @EJB
    private TransportDAO transportDAO;

    @EJB
    private FuelDAO fuelDAO;

    private Transport transport;
    private int idFuel;
    private List<String> ids;
    private String stringValue;

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
    

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public int getIdFuel() {
        return idFuel;
    }

    public void setIdFuel(int idFuel) {
        this.idFuel = idFuel;
    }

    @PostConstruct
    private void initializeBean() {
        transport = new Transport();
        transport.setTransportValid(true);
        ids = idss();
    }

    public List<String> idss(){
        List<String> somelist = new ArrayList<>();
        somelist.add("1");
        somelist.add("2");
        return somelist;
    }

    public String addNewTransport() throws SQLException, Exception {
        transportDAO.addTransport(transport);
        return "/allTransports.xhtml";
    }

    public void deleteTransport(Transport selectedTransport) throws Exception {
        transportDAO.deleteTransport(selectedTransport);
    }

    public List<Transport> getAllTransports() throws Exception {
        return transportDAO.getAllTransports();
    }

    public String ToEditTransport(Transport transport) {
        this.transport = transport;
        return "/editTransport.xhtml";
    }

    public String editTransport() throws SQLException, Exception {
        //fuelDAO.findFuelById(idFuel);
        transportDAO.editTransport(transport);
        this.transport = new Transport();
        return "/allTransports.xhtml";
    }

    public List<Refill> getTransportRefills(Transport transport) throws Exception {
        return transportDAO.getTransportRefills(transport);
    }

    public List<Repair> getTransportRepairs(Transport transport) throws Exception {
        return transportDAO.getTransportRepairs(transport);
    }
}
