/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Circuit;
import Model.Report;
import Model.Route;
import Model.Stop;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ceparator
 */
@Stateless
public class RouteDAO implements RouteDAOInterface {

    @PersistenceContext(unitName = "Kyrsach-ejbPU")
    private EntityManager em;

    @PersistenceContext(unitName = "Kyrsach-ejbPU2")
    private EntityManager em2;

    @Override
    public void deleteRoute(List<Route> selectedRouteList) {
        for (Route route : selectedRouteList) {
            Route smth = em.getReference(Route.class, route.getIdRoute());
            Query query = em2.createQuery("SELECT r FROM Report r WHERE r.idReport = ?1", Report.class);
            query.setParameter(1, smth.getNumber());
            List<Report> rlist = query.getResultList();
            for (Report report : rlist) {
                em2.remove(em2.getReference(Report.class, report.getIdReport()));
            }
            em.remove(smth);
        }
    }

    @Override
    public List<Route> getAllRoutes() {
        Query query = em.createQuery("SELECT r FROM Route r", Route.class);
        return query.getResultList();
    }

    @Override
    public List<Circuit> getRouteFirstCircuit(int idRoute) {
        Query query = em.createQuery("SELECT c FROM Circuit c WHERE c.idRoute = ?1 AND c.tyda = ?2 ORDER BY c.stopNumber ASC", Circuit.class);
        query.setParameter(1, em.getReference(Route.class, idRoute));
        query.setParameter(2, true);
        return query.getResultList();
    }

    @Override
    public List<Circuit> getRouteSecondCircuit(int idRoute) {
        Query query = em.createQuery("SELECT c FROM Circuit c WHERE c.idRoute = ?1 AND c.tyda = ?2 ORDER BY c.stopNumber ASC", Circuit.class);
        query.setParameter(1, em.getReference(Route.class, idRoute));
        query.setParameter(2, false);
        return query.getResultList();
    }

    @Override
    public int addRoute(Route route, Stop stop1, Stop stop2) {
        route.setFirstStop(stop1);
        route.setLastStop(stop2);
        em.persist(route);
        em.flush();
        return route.getIdRoute();
    }
}
