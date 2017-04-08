/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Route;
import Model.Routelist;
import Model.Routetransport;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ceparator
 */
@Stateless
@LocalBean
public class RoutelistDAO {

    @PersistenceContext(unitName = "Kyrsach-ejbPU")
    private EntityManager em;

    public void deleteRoutelist(Routelist selectedRoutelist) throws Exception {
        Routelist someRoutelist = em.getReference(Routelist.class, selectedRoutelist.getIdRouteList());
        em.remove(someRoutelist);
        Query query = em.createQuery("SELECT r FROM Routetransport r WHERE r.idRoute = ?1 AND r.idShift = ?2", Routetransport.class);
        query.setParameter(1, selectedRoutelist.getIdRoute());
        query.setParameter(2, selectedRoutelist.getIdShift());
        query.setMaxResults(1);
        if (!query.getResultList().isEmpty()) {
            Routetransport routetransport = (Routetransport) query.getResultList().get(0);
            routetransport.setTransportNumber(routetransport.getTransportNumber() - 1);
            em.merge(routetransport);
        }
    }

    public List<Routelist> getAllRoutelists() {
        Query query = em.createQuery("SELECT r FROM Routelist r", Routelist.class);
        return query.getResultList();
    }

    public List<Routelist> getRouteList(Route route) {
        Query query = em.createQuery("SELECT r FROM Routelist r WHERE r.idRoute = ?1", Routelist.class);
        query.setParameter(1, route);
        return query.getResultList();
    }

    public List<Routelist> getMyRouteList(String username) {
        Query query = em.createQuery("SELECT r FROM Routelist r WHERE r.conductor = ?1 OR r.driver = ?1", Routelist.class);
        query.setParameter(1, username);
        return query.getResultList();
    }

    public void addRoutelist(Routelist routelist) {
        em.persist(routelist);
        Query query = em.createQuery("SELECT r FROM Routetransport r WHERE r.idRoute = ?1 AND r.idShift = ?2", Routetransport.class);
        query.setParameter(1, routelist.getIdRoute());
        query.setParameter(2, routelist.getIdShift());
        query.setMaxResults(1);
        if (query.getResultList().isEmpty()) {
            Routetransport routetransport = new Routetransport();
            routetransport.setIdRoute(routelist.getIdRoute());
            routetransport.setIdShift(routelist.getIdShift());
            routetransport.setTransportNumber(1);
            em.persist(routetransport);
        } else {
            Routetransport routetransport = (Routetransport) query.getResultList().get(0);
            routetransport.setTransportNumber(routetransport.getTransportNumber() + 1);
            em.merge(routetransport);
        }
    }

    public void editRoutelist(Routelist routelist) {
        em.merge(routelist);
    }

    public Routelist findRoutelistById(int idRoutelist) {
        return em.find(Routelist.class, idRoutelist);
    }
}
