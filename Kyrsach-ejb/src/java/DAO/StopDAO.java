/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Stop;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ceparator
 */
@Stateless
public class StopDAO implements StopDAOInterface {

    @PersistenceContext(unitName = "Kyrsach-ejbPU")
    private EntityManager em;

    @Override
    public void deleteStop(List<Stop> selectedStopList) throws Exception {
        Iterator<Stop> iter = selectedStopList.iterator();
        try {
            while (iter.hasNext()) {
                Stop item = iter.next();
                Stop someStop = em.getReference(Stop.class, item.getIdStop());
                em.remove(someStop);
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @Override
    public List<Stop> getAllStops() {
        Query query = em.createQuery("SELECT s FROM Stop s", Stop.class);
        return query.getResultList();
    }

    @Override
    public void addStop(Stop stop) {
        em.persist(stop);
    }

    @Override
    public void editStop(Stop stop) {
        em.merge(stop);
    }
}
