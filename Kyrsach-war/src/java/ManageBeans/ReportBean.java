/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import DAO.MyuserDAOInterface;
import DAO.ReportDAOInterface;
import DAO.ShiftDAO;
import Model.Myuser;
import Model.Report;
import Model.Shift;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.icefaces.ace.model.table.RowStateMap;

/**
 *
 * @author Ceparator
 */
@Named(value = "reportBean")
@SessionScoped
public class ReportBean implements Serializable {

    @EJB
    private ReportDAOInterface reportDAO;

    @EJB
    private MyuserDAOInterface myuserDAO;

    @EJB
    private ShiftDAO shiftDAO;


    private Report report;
    private int routeNumber;
    private Date vremya;

    @PostConstruct
    private void initializeBean() {
        report = new Report();
    }

    public int getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(int routeNumber) {
        this.routeNumber = routeNumber;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Date getVremya() {
        return vremya;
    }

    public void setVremya(Date vremya) {
        this.vremya = vremya;
    }

    private RowStateMap stateMap;

    public RowStateMap getStateMap() {
        return stateMap;
    }

    public void setStateMap(RowStateMap stateMap) {
        this.stateMap = stateMap;
    }

    public void actionListener(ActionEvent event) throws Exception {
        List<Report> selectedReportList = (List<Report>) stateMap.getSelected();
        reportDAO.deleteReport(selectedReportList);
    }

    public List<Report> getAllReports() throws Exception {
        return reportDAO.getAllReports();
    }

    public String toEditReport(Report report) {
        this.report = report;
        return "/editReport.xhtml";
    }

    public String toAddReport() throws SQLException, Exception {
        String somename = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        List<Integer> somelist = reportDAO.getUserRoute(somename);
        report.setRouteNumber(somelist.get(0));
        report.setOccupancy(100);
        vremya = Calendar.getInstance().getTime();
        return "/addReport.xhtml";
    }

    public List<Integer> getUserRoute() throws SQLException, Exception {
        String somename = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        return reportDAO.getUserRoute(somename);
    }

    public String editReport() throws SQLException, Exception {
        reportDAO.editReport(report);
        return "/allReports.xhtml";
    }

    public List<Report> getUserReports() throws Exception {
        String somename = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        return reportDAO.getMyReports(somename);
    }
    
        public String addNewReport(Date vremya) throws SQLException, Exception {
        Myuser conductorname = myuserDAO.findUserById(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
        reportDAO.addReport(report.getRouteNumber(), report.getIdShift(), report.getTickets(), vremya, conductorname, report.getOccupancy());
        report = new Report();
        return "/allReports.xhtml";
    }

    public List<Shift> getUserRouteShifts() throws Exception {
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        return shiftDAO.getUserRouteShifts(username, report.getRouteNumber());
    }
}
