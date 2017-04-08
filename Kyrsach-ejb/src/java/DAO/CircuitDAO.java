/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Circuit;
import Model.Route;
import Model.Stop;
import java.util.Iterator;
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
public class CircuitDAO implements CircuitDAOInterface {

    @PersistenceContext(unitName = "Kyrsach-ejbPU")
    private EntityManager em;

    @Override
    public void addCircuit(List<Circuit> circuitList, int idRoute) throws Exception {        //актуальное добавление схемы в БД
        Iterator<Circuit> iter = circuitList.iterator();
        try {
            while (iter.hasNext()) {
                Circuit item = iter.next();
                item.setIdRoute(em.getReference(Route.class, idRoute));
                em.persist(item);
            }
        } catch (Exception ex) {
            throw new Exception("Don't panic! just reload the page. It's all my fault...");
        }
    }

    @Override
    public void addOneCircuit(int idRoute, int newStopId, int number, boolean tyda) throws Exception {        //актуальное добавление схемы в БД
        try {
            Route route = em.getReference(Route.class, idRoute);
            Query query = em.createQuery("SELECT c FROM Circuit c WHERE c.idRoute = ?1 AND c.tyda = ?2", Circuit.class);
            query.setParameter(1, route);
            query.setParameter(2, tyda);
            List<Circuit> circuitList = query.getResultList();
            Iterator<Circuit> iter = circuitList.iterator();
            while (iter.hasNext()) {
                Circuit item = iter.next();
                if (item.getStopNumber() >= number) {
                    item.setStopNumber(item.getStopNumber() + 1);
                    em.merge(item);
                }
            }
            Circuit circuit = new Circuit();
            circuit.setIdRoute(route);
            Stop stop = em.getReference(Stop.class, newStopId);
            circuit.setIdStop(stop);
            circuit.setStopNumber(number);
            circuit.setTyda(tyda);
            em.persist(circuit);
            updateRouteStops(route);
        } catch (Exception ex) {
            throw new Exception("Don't panic! just reload the page. It's all my fault...");
        }
    }

    @Override
    public void deleteCircuit(int idCircuit) throws Exception {
        Circuit circuit = em.getReference(Circuit.class, idCircuit);
        try {
            Query query = em.createQuery("SELECT c FROM Circuit c WHERE c.idRoute = ?1 AND c.tyda = ?2", Circuit.class);
            query.setParameter(1, circuit.getIdRoute());
            query.setParameter(2, circuit.getTyda());
            List<Circuit> circuitList = query.getResultList();
            Iterator<Circuit> iter = circuitList.iterator();
            while (iter.hasNext()) {
                Circuit item = iter.next();
                if (item.getStopNumber() > circuit.getStopNumber()) {
                    item.setStopNumber(item.getStopNumber() - 1);
                    em.merge(item);
                }
            }
            em.remove(circuit);
            updateRouteStops(circuit.getIdRoute());
        } catch (Exception ex) {
            throw new Exception("Don't panic! just reload the page. It's all my fault...");
        }
    }

    @Override
    public void updateRouteStops(Route route) throws Exception {
        try {
            Query query = em.createQuery("SELECT c FROM Circuit c where c.idRoute = ?1 AND c.tyda = ?2 ORDER BY c.stopNumber ASC", Circuit.class);
            query.setFirstResult(0);
            query.setMaxResults(1);
            query.setParameter(1, route);
            query.setParameter(2, true);
            Circuit circuit = (Circuit) query.getSingleResult();
            route.setFirstStop(circuit.getIdStop());
            em.merge(route);
            query = em.createQuery("SELECT c FROM Circuit c where c.idRoute = ?1 AND c.tyda = ?2 ORDER BY c.stopNumber ASC", Circuit.class);
            query.setFirstResult(0);
            query.setMaxResults(1);
            query.setParameter(1, route);
            query.setParameter(2, false);
            circuit = (Circuit) query.getSingleResult();
            route.setLastStop(circuit.getIdStop());
            em.merge(route);
        } catch (Exception ex) {
            throw new Exception("Don't panic! just reload the page. It's all my fault...");
        }
    }

    @Override
    public void editCircuit(int idCircuit, int number, int newStopId) throws Exception {
        try {
            Circuit circuit = em.getReference(Circuit.class, idCircuit);
            Query query = em.createQuery("SELECT c FROM Circuit c WHERE c.idRoute = ?1 AND c.tyda = ?2", Circuit.class);
            query.setParameter(1, circuit.getIdRoute());
            query.setParameter(2, circuit.getTyda());
            List<Circuit> circuitList = query.getResultList();
            circuitList.remove(circuit);
            Iterator<Circuit> iter = circuitList.iterator();
            if (circuit.getStopNumber() > number) {
                while (iter.hasNext()) {
                    Circuit item = iter.next();
                    if (item.getStopNumber() >= number && (item.getStopNumber() < circuit.getStopNumber())) {
                        item.setStopNumber(item.getStopNumber() + 1);
                        em.merge(item);
                    }
                }
            }
            if (circuit.getStopNumber() < number) {
                while (iter.hasNext()) {
                    Circuit item = iter.next();
                    if (item.getStopNumber() >= item.getStopNumber() && (item.getStopNumber() <= number)) {
                        item.setStopNumber(item.getStopNumber() - 1);
                        em.merge(item);
                    }
                }
            }
            circuit.setStopNumber(number);
            circuit.setIdStop(em.getReference(Stop.class, newStopId));
            em.merge(circuit);
            updateRouteStops(circuit.getIdRoute());
        } catch (Exception ex) {
            throw new Exception("Don't panic! just reload the page. It's all my fault...");
        }
    }
}
