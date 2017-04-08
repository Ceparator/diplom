/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import DAO.CircuitDAOInterface;
import Model.Circuit;
import Model.Stop;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Ceparator
 */
@Named(value = "addCircuitBean")
@RequestScoped
public class AddCircuitBean {

    @EJB
    private CircuitDAOInterface circuitDAO;

    private Circuit circuit;
    private int number;
    private int newStopId;

    @PostConstruct
    private void initializeBean() {
        circuit = new Circuit();
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNewStopId() {
        return newStopId;
    }

    public void setNewStopId(int newStopId) {
        this.newStopId = newStopId;
    }

    public String addNewCircuit(int idRoute, boolean tyda) throws SQLException, Exception {
        circuitDAO.addOneCircuit(idRoute, newStopId, number, tyda);
        return "/routeCircuit.xhtml";
    }

    public String editCircuit(int idCircuit) throws SQLException, Exception {
        circuitDAO.editCircuit(idCircuit, number, newStopId);
        return "/routeCircuit.xhtml";
    }

}
