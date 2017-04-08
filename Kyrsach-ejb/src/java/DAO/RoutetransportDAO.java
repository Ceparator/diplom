/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Route;
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
public class RoutetransportDAO {

    @PersistenceContext(unitName = "Kyrsach-ejbPU")
    private EntityManager em;

    public void deleteRoutetransport(Routetransport selectedRoutetransport) throws Exception {
        Routetransport someRoutetransport = em.getReference(Routetransport.class, selectedRoutetransport.getIdRouteTransport());
        em.remove(someRoutetransport);
    }

    public List<Routetransport> getAllRoutetransports() {
        Query query = em.createQuery("SELECT r FROM Routetransport r", Routetransport.class);
        return query.getResultList();
    }

    public List<Routetransport> getRoutetransports(Route route) {
        Query query = em.createQuery("SELECT r FROM Routetransport r WHERE r.idRoute = ?1", Routetransport.class);
        query.setParameter(1, route);
        return query.getResultList();
    }

    public void addRoutetransport(Routetransport routetransport) {
        em.persist(routetransport);
    }

    public void editRoutetransport(Routetransport routetransport) {
        em.merge(routetransport);
    }

    public Routetransport findRoutetransportById(int idRoutetransport) {
        return em.find(Routetransport.class, idRoutetransport);
    }

}
