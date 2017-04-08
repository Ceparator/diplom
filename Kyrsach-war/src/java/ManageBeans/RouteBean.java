/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import DAO.RouteDAOInterface;
import Model.Route;
import Model.Circuit;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.icefaces.ace.model.table.RowStateMap;

/**
 *
 * @author Ceparator
 */
@SessionScoped
@ManagedBean(name = "routeBean")
public class RouteBean implements Serializable {

    @EJB
    private RouteDAOInterface routeDAO;

    private int numberRoute;
    private Route route;

    @PostConstruct
    private void initializeBean() {
        numberRoute = 0;
    }

    public int getNumberRoute() {
        return numberRoute;
    }

    public void setNumberRoute(int numberRoute) {
        this.numberRoute = numberRoute;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    private RowStateMap stateMap;

    public RowStateMap getStateMap() {
        return stateMap;
    }

    public void setStateMap(RowStateMap stateMap) {
        this.stateMap = stateMap;
    }

    public void actionListener(ActionEvent event) throws Exception {
        List<Route> selectedRouteList = (List<Route>) stateMap.getSelected();
        routeDAO.deleteRoute(selectedRouteList);
    }

    public List<Route> getAllRoutes() throws Exception {
        return routeDAO.getAllRoutes();
    }

    public List<Circuit> getFirstCircuit(int idRoute) throws Exception {
        return routeDAO.getRouteFirstCircuit(idRoute);
    }

    public List<Circuit> getSecondCircuit(int idRoute) throws Exception {
        return routeDAO.getRouteSecondCircuit(idRoute);
    }

    public String redirectRouteInfo(int number) {
        this.numberRoute = number;
        return "/routeCircuit.xhtml";
    }

    public String redirectRouteList(Route rt) {
        route = rt;
        return "/routeList.xhtml";
    }

    public String redirectRouteSchedule(Route rt) {
        route = rt;
        return "/routeSchedule.xhtml";
    }

}
