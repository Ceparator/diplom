/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import DAO.CircuitDAOInterface;
import Model.Circuit;
import Model.Stop;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import org.icefaces.ace.model.table.RowStateMap;

/**
 *
 * @author Ceparator
 */
@Named(value = "circuitBean")
@SessionScoped
public class CircuitBean implements Serializable {

    @EJB
    private CircuitDAOInterface circuitDAO;

    private int editId;
    private boolean add;
    private int oldNumber;
    private Stop stop;
    private int routeNumber;
    private int size;

    @PostConstruct
    private void initializeBean() {
        editId = 0;
    }

    public int getEditId() {
        return editId;
    }

    public void setEditId(int editId) {
        this.editId = editId;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    private RowStateMap stateMap;

    public RowStateMap getStateMap() {
        return stateMap;
    }

    public void setStateMap(RowStateMap stateMap) {
        this.stateMap = stateMap;
    }

    public int getOldNumber() {
        return oldNumber;
    }

    public void setOldNumber(int oldNumber) {
        this.oldNumber = oldNumber;
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public int getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(int routeNumber) {
        this.routeNumber = routeNumber;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void DeleteCircuit(int idCircuit) throws Exception {
        circuitDAO.deleteCircuit(idCircuit);
    }

    public String ToEditCircuit(int idCircuit, int stopNumber, Stop oldStop, int razmer) {
        size = razmer;
        oldNumber = stopNumber;
        stop = oldStop;
        this.editId = idCircuit;
        return "/editCircuit.xhtml";
    }
    
    public String ToAddFirstCircuit(int idRoute, int razmer) {
        this.size = razmer;
        routeNumber = idRoute;
        this.add = true;
        return "/addCircuit.xhtml";
    }
    
    public String ToAddSecondCircuit(int idRoute, int razmer) {
        this.size = razmer;
        routeNumber = idRoute;
        this.add = false;
        return "/addCircuit.xhtml";
    }
}