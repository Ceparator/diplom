/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Circuit;
import Model.Route;
import Model.Stop;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ceparator
 */
@Local
public interface RouteDAOInterface {

    int addRoute(Route route, Stop stop1, Stop stop2);

    void deleteRoute(List<Route> selectedRouteList);

    List<Route> getAllRoutes();

    List<Circuit> getRouteFirstCircuit(int idRoute);

    List<Circuit> getRouteSecondCircuit(int idRoute);
    
}
