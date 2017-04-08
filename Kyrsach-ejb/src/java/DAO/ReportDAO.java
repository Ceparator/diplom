/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Myuser;
import Model.Report;
import Model.Route;
import Model.Routetransport;
import Model.Shift;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ceparator
 */
@Stateless
public class ReportDAO implements ReportDAOInterface {

    @PersistenceContext(unitName = "Kyrsach-ejbPU2")
    private EntityManager em2;

    @PersistenceContext(unitName = "Kyrsach-ejbPU")
    private EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteReport(List<Report> selectedReportList) throws Exception {
        Iterator<Report> iter = selectedReportList.iterator();
        try {
            while (iter.hasNext()) {
                Report item = iter.next();
                Report someReport = em2.getReference(Report.class, item.getIdReport());
                Query query = em.createQuery("SELECT r FROM Route r WHERE r.number = ?1", Report.class);
                query.setParameter(1, someReport.getRouteNumber());
                Route route = (Route) query.getSingleResult();
                route.setRating(route.getRating() - someReport.getSumma() * 0.01);
                em.merge(route);
                em2.remove(someReport);

                query = em.createQuery("SELECT r FROM Routetransport r WHERE r.idRoute.number = ?1 AND r.idShift.idShift = ?2", Routetransport.class);
                query.setParameter(1, someReport.getRouteNumber());
                query.setParameter(2, someReport.getIdShift());
                query.setMaxResults(1);
                List<Routetransport> rtl = query.getResultList();
                for (Routetransport routetransport : rtl) {
                    query = em2.createQuery("SELECT r FROM Report r WHERE r.routeNumber = ?1 AND r.idShift = ?2", Report.class);
                    query.setParameter(1, someReport.getRouteNumber());
                    query.setParameter(2, someReport.getIdShift());
                    List<Report> reportList = query.getResultList();
                    int occSum = 0;
                    for (Report report1 : reportList) {
                        occSum += report1.getOccupancy();
                    }
                    routetransport.setOptimalNumber((int) Math.ceil(routetransport.getTransportNumber() * ((occSum - someReport.getOccupancy()) / (reportList.size() - 1) / 100)));
                    em.merge(routetransport);
                }

            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @Override
    public List<Report> getAllReports() {
        Query query2 = em.createQuery("SELECT s FROM Shift s", Shift.class);
        List<Shift> shifts = query2.getResultList();
        Query query = em2.createQuery("SELECT r FROM Report r", Report.class);
        List<Report> rlist = query.getResultList();
        DateFormat df = new SimpleDateFormat("HH:mm");
        for (Report report : rlist) {
            for (Shift sht : shifts) {
                if (sht.getIdShift() == report.getIdShift()) {
                    report.setShiftTime(df.format(sht.getStartTime()) + " - " + df.format(sht.getEndTime()));
                }
            }
        }
        return rlist;
    }

    @Override
    public List<Integer> getUserRoute(String username) {
        Query query = em.createQuery("SELECT DISTINCT r.idRoute.number FROM Routelist r WHERE r.conductor = ?1 OR r.driver = ?1", Integer.class);
        query.setParameter(1, username);
        List<Integer> rl = query.getResultList();
        return rl;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addReport(int routeNumber, int idShift, int tickets, Date newDate, Myuser conductor, int occupancy) {
        Query query = em.createQuery("SELECT r FROM Route r WHERE r.number = ?1", Report.class);
        query.setParameter(1, routeNumber);
        Route route = (Route) query.getSingleResult();
        Report report = new Report();
        report.setRouteNumber(routeNumber);
        report.setTickets(tickets);
        report.setVremya(newDate);
        report.setSumma(route.getPrice() * tickets);
        report.setUsername(conductor);
        report.setOccupancy(occupancy);
        report.setIdShift(idShift);
        em2.persist(report);
        route.setRating(route.getRating() + report.getSumma() * 0.01);
        em.merge(route);

        query = em.createQuery("SELECT r FROM Routetransport r WHERE r.idRoute.number = ?1 AND r.idShift.idShift = ?2", Routetransport.class);
        query.setParameter(1, routeNumber);
        query.setParameter(2, idShift);
        query.setMaxResults(1);
        List<Routetransport> rtl = query.getResultList();
        for (Routetransport routetransport : rtl) {
            query = em2.createQuery("SELECT r FROM Report r WHERE r.routeNumber = ?1 AND r.idShift = ?2", Report.class);
            query.setParameter(1, routeNumber);
            query.setParameter(2, idShift);
            List<Report> reportList = query.getResultList();
            int occSum = 0;
            for (Report report1 : reportList) {
                occSum += report1.getOccupancy();
            }
            routetransport.setOptimalNumber((int) Math.ceil(routetransport.getTransportNumber() * ((occSum + occupancy) / (reportList.size() + 1) / 100)));
            em.merge(routetransport);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editReport(Report report) {
        int oldOccupancy = report.getOccupancy();
        Query query = em.createQuery("SELECT r FROM Route r WHERE r.number = ?1", Report.class);
        query.setParameter(1, report.getRouteNumber());
        Report oldReport = em2.find(Report.class, report.getIdReport());
        int oldSum = oldReport.getSumma();
        Route route = (Route) query.getSingleResult();
        report.setSumma(route.getPrice() * report.getTickets());
        em2.merge(report);
        route.setRating(route.getRating() - oldSum * 0.01 + report.getSumma() * 0.01);
        em.merge(route);

        query = em.createQuery("SELECT r FROM Routetransport r WHERE r.idRoute.number = ?1 AND r.idShift.idShift = ?2", Routetransport.class);
        query.setParameter(1, report.getRouteNumber());
        query.setParameter(2, report.getIdShift());
        query.setMaxResults(1);
        List<Routetransport> rtl = query.getResultList();
        for (Routetransport routetransport : rtl) {
            query = em2.createQuery("SELECT r FROM Report r WHERE r.routeNumber = ?1 AND r.idShift = ?2", Report.class);
            query.setParameter(1, report.getRouteNumber());
            query.setParameter(2, report.getIdShift());
            List<Report> reportList = query.getResultList();
            int occSum = 0;
            for (Report report1 : reportList) {
                occSum += report1.getOccupancy();
            }
            routetransport.setOptimalNumber((int) Math.ceil(routetransport.getTransportNumber() * ((occSum - oldOccupancy + report.getOccupancy()) / reportList.size() / 100)));
            em.merge(routetransport);
        }
    }

    @Override
    public List<Report> getMyReports(String username) {

        Query query2 = em.createQuery("SELECT s FROM Shift s", Shift.class);
        List<Shift> shifts = query2.getResultList();
        Query query = em2.createQuery("SELECT r FROM Report r WHERE r.username = ?1", Report.class);
        query.setParameter(1, em2.find(Myuser.class, username));
        List<Report> rlist = query.getResultList();
        DateFormat df = new SimpleDateFormat("HH:mm");
        for (Report report : rlist) {
            for (Shift sht : shifts) {
                if (sht.getIdShift() == report.getIdShift()) {
                    report.setShiftTime(df.format(sht.getStartTime()) + " - " + df.format(sht.getEndTime()));
                }
            }
        }
        return rlist;

    }
}
