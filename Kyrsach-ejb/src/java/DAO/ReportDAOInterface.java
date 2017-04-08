/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Myuser;
import Model.Report;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author Ceparator
 */
@Local
public interface ReportDAOInterface {

    void addReport(int routeNumber, int idShift, int tickets, Date newDate, Myuser conductor, int occupancy);

    void deleteReport(List<Report> selectedReportList) throws Exception;

    void editReport(Report report);

    List<Report> getAllReports();
    
    List<Integer> getUserRoute(String username);

    public List<Report> getMyReports(String username);
    
}
