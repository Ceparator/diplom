/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import DAO.RoutelistDAO;
import DAO.ScheduleDAO;
import DAO.TransportDAO;
import Model.Route;
import Model.Routelist;
import Model.Schedule;
import Model.Transport;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ceparator
 */
public class RoutelistBean {

    @EJB
    private RoutelistDAO routelistDAO;

    @EJB
    private TransportDAO transportDAO;

    @EJB
    private ScheduleDAO scheduleDAO;

    private Routelist routelist;
    private Route route;
    private String driver, conductor;
    private String transport;

    public Routelist getRoutelist() {
        return routelist;
    }

    public void setRoutelist(Routelist routelist) {
        this.routelist = routelist;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    @PostConstruct
    private void initializeBean() {
        routelist = new Routelist();
    }

    public void addNewRoutelist() throws SQLException, Exception {
        routelist.setIdTransport(transportDAO.findTransportBySerialNumber(transport));
        routelist.setIdRoute(route);
        routelistDAO.addRoutelist(routelist);
        transport = "";
        routelist.setConductor("");
        routelist.setDriver("");
        //return "/routeList.xhtml";
    }

    public void deleteRoutelist(Routelist selectedRoutelist) throws Exception {
        routelistDAO.deleteRoutelist(selectedRoutelist);
    }

    public List<Routelist> getAllRoutelists() throws Exception {
        return routelistDAO.getAllRoutelists();
    }

    public String ToEditRoutelist(Routelist routelist) {
        this.routelist = routelist;
        return "/editRoutelist.xhtml";
    }

    public String editRoutelist() throws SQLException, Exception {
        routelist.setIdTransport(transportDAO.findTransportBySerialNumber(transport));
        routelistDAO.editRoutelist(routelist);
        this.routelist = new Routelist();
        return "/routeList.xhtml";
    }

    public List<Routelist> getRouteList(Route route) {
        return routelistDAO.getRouteList(route);
    }

    public List<Routelist> getMyRouteList(String username) {
        return routelistDAO.getMyRouteList(username);
    }

    public String toAddrouteList(Route route) throws SQLException, Exception {
        this.route = route;
        //transport = " ";
        //routelist.setConductor(" ");
        //routelist.setDriver(" ");
        return "/addRouteList.xhtml";
    }

   /* public void selectConductor(String cond) throws SQLException, Exception {
        routelist.setConductor(cond);
    }

    public void selectDriver(String driv) throws SQLException, Exception {
        routelist.setDriver(driv);
    }

    public void selectTransport(String transp) throws SQLException, Exception {
        routelist.setIdTransport(transportDAO.findTransportBySerialNumber(transp));
    }*/
}
