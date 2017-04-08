/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Circuit;
import Model.Route;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ceparator
 */
@Local
public interface CircuitDAOInterface {

    void addCircuit(List<Circuit> circuitList, int idRoute) throws Exception;

    void addOneCircuit(int idRoute, int newStopId, int number, boolean tyda) throws Exception;

    void deleteCircuit(int idCircuit) throws Exception;

    void updateRouteStops(Route route) throws Exception;

    public void editCircuit(int idCircuit, int number, int newStopId) throws Exception;
    
}
