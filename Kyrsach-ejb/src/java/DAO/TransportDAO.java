/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Refill;
import Model.Repair;
import Model.Transport;
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
public class TransportDAO {

    @PersistenceContext(unitName = "Kyrsach-ejbPU")
    private EntityManager em;

    public void deleteTransport(Transport selectedTransport) throws Exception {
        Transport someTransport = em.getReference(Transport.class, selectedTransport.getIdTransport());
        em.remove(someTransport);
    }

    public List<Transport> getAllTransports() {
        Query query = em.createQuery("SELECT t FROM Transport t", Transport.class);
        return query.getResultList();
    }

    public void addTransport(Transport transport) {
        em.persist(transport);
    }

    public void editTransport(Transport transport) {
        em.merge(transport);
    }

    public Transport findTransportById(int idTransport) {
        return em.find(Transport.class, idTransport);
    }

    public Transport findTransportBySerialNumber(String serialNumber) {
        Query query = em.createQuery("SELECT t FROM Transport t WHERE t.serialNumber = ?1", Transport.class);
        query.setParameter(1, serialNumber);
        List<Transport> transpList = query.getResultList();
        for (Transport transport : transpList) {
            return transport;
        }
        return null;
    }

    public List<Repair> getTransportRepairs(Transport transport) {
        Query query = em.createQuery("SELECT r FROM Repair r WHERE r.idTransport = ?1", Repair.class);
        query.setParameter(1, transport);
        return query.getResultList();
    }

    public List<Refill> getTransportRefills(Transport transport) {
        Query query = em.createQuery("SELECT r FROM Refill r WHERE r.idTransport = ?1", Refill.class);
        query.setParameter(1, transport);
        return query.getResultList();
    }
}
