/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Health;
import Model.Repair;
import Model.Routelist;
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
public class RepairDAO {

    @PersistenceContext(unitName = "Kyrsach-ejbPU")
    private EntityManager em;

    public void deleteRepair(Repair selectedRepair) throws Exception {
        Repair someRepair = em.getReference(Repair.class, selectedRepair.getIdRepair());
        em.remove(someRepair);
    }

    public List<Repair> getAllRepairs() {
        Query query = em.createQuery("SELECT r FROM Repair r", Repair.class);
        return query.getResultList();
    }

    public List<Repair> getPendingRepairs() {
        Query query = em.createQuery("SELECT r FROM Repair r WHERE r.mechanic IS NULL", Repair.class);
        return query.getResultList();
    }

    public void addRepair(Repair repair) {
        em.persist(repair);
        Transport tr = repair.getIdTransport();
        tr.setTransportValid(false);

        Query query = em.createQuery("SELECT r FROM Routelist r WHERE r.idTransport = ?1", Routelist.class);
        query.setParameter(1, tr);
        List<Routelist> rl = query.getResultList();
        if (!rl.isEmpty()) {
            for (Routelist routelist : rl) {
                Routelist invr = em.getReference(Routelist.class, routelist.getIdRouteList());
                invr.setIsValid(false);
                em.merge(invr);
            }
        }
        em.merge(tr);
    }

    public void editRepair(Repair repair) {
        em.merge(repair);
        if (repair.getActualFixDate() == null) {
            Transport tr = repair.getIdTransport();
            tr.setTransportValid(false);

            Query query = em.createQuery("SELECT r FROM Routelist r WHERE r.idTransport = ?1", Routelist.class);
            query.setParameter(1, tr);
            List<Routelist> rl = query.getResultList();
            if (!rl.isEmpty()) {
                for (Routelist routelist : rl) {
                    Routelist invr = em.getReference(Routelist.class, routelist.getIdRouteList());
                    invr.setIsValid(false);
                    em.merge(invr);
                }
            }
            em.merge(tr);
        } else {
            Transport tr = repair.getIdTransport();
            tr.setTransportValid(true);

            Query query = em.createQuery("SELECT r FROM Routelist r WHERE r.idTransport = ?1", Routelist.class);
            query.setParameter(1, tr);
            List<Routelist> rl = query.getResultList();
            if (!rl.isEmpty()) {
                for (Routelist routelist : rl) {
                    Routelist vr = em.getReference(Routelist.class, routelist.getIdRouteList());

                    Query query2 = em.createQuery("SELECT h FROM Health h WHERE h.driver = ?1 ORDER BY h.vremya DESC", Health.class);
                    query2.setParameter(1, vr.getDriver());
                    query2.setMaxResults(1);
                    List<Health> hl = query2.getResultList();
                    if (hl.isEmpty()) {
                        vr.setIsValid(true);
                        em.merge(vr);
                    }
                    for (Health health : hl) {
                        if (health.getQualified()) {
                            vr.setIsValid(true);
                            em.merge(vr);
                        }
                    }
                }
            }
            em.merge(tr);
        }
    }

    public Repair findRepairById(int idRepair) {
        return em.find(Repair.class, idRepair);
    }

    public List<Repair> getMyRepairs(String username) {
        Query query = em.createQuery("SELECT r FROM Repair r WHERE r.mechanic = ?1", Repair.class);
        query.setParameter(1, username);
        return query.getResultList();
    }
}
