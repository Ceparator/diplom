/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Health;
import Model.Routelist;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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
public class HealthDAO {

    @PersistenceContext(unitName = "Kyrsach-ejbPU")
    private EntityManager em;

    public void deleteHealth(List<Health> selectedHealth) throws Exception {
        for (Health hl : selectedHealth) {
            em.remove(em.getReference(Health.class, hl.getIdHealth()));
        }
    }

    public List<Health> getAllHealths() {
        Query query = em.createQuery("SELECT h FROM Health h", Health.class);
        return query.getResultList();
    }

    public List<String> getDoctorHealths() {
        
        Query query = em.createQuery("SELECT DISTINCT(r.driver) FROM Routelist r", String.class);
        List<String> allDrivers = query.getResultList();

        query = em.createQuery("SELECT DISTINCT(h.driver) FROM Health h WHERE h.vremya = ?1", String.class);
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        Date tdate = now.getTime();
        query.setParameter(1, tdate);
        List<String> hl = query.getResultList();
        allDrivers.removeAll(hl);
        return allDrivers;
    }

    public void addHealth(Health health) {
        em.persist(health);
        if (health.getQualified() == false) {
            Query query = em.createQuery("SELECT r FROM Routelist r WHERE r.driver = ?1", Routelist.class);
            query.setParameter(1, health.getDriver());
            List<Routelist> rl = query.getResultList();
            if (!rl.isEmpty()) {
                for (Routelist routelist : rl) {
                    Routelist invr = em.getReference(Routelist.class, routelist.getIdRouteList());
                    invr.setIsValid(false);
                    em.merge(invr);
                }
            }
        } else {
            Query query = em.createQuery("SELECT r FROM Routelist r WHERE r.driver = ?1", Routelist.class);
            query.setParameter(1, health.getDriver());
            List<Routelist> rl = query.getResultList();
            if (!rl.isEmpty()) {
                for (Routelist routelist : rl) {
                    Routelist vr = em.getReference(Routelist.class, routelist.getIdRouteList());
                    if (vr.getIdTransport().getTransportValid()) {
                        vr.setIsValid(true);
                        em.merge(vr);
                    }
                }
            }
        }
    }

    public void editHealth(Health health) {
        em.merge(health);
        if (health.getQualified() == false) {
            Query query = em.createQuery("SELECT r FROM Routelist r WHERE r.driver = ?1", Routelist.class);
            query.setParameter(1, health.getDriver());
            List<Routelist> rl = query.getResultList();
            if (!rl.isEmpty()) {
                for (Routelist routelist : rl) {
                    Routelist invr = em.getReference(Routelist.class, routelist.getIdRouteList());
                    invr.setIsValid(false);
                    em.merge(invr);
                }
            }
        } else {
            Query query = em.createQuery("SELECT r FROM Routelist r WHERE r.driver = ?1", Routelist.class);
            query.setParameter(1, health.getDriver());
            List<Routelist> rl = query.getResultList();
            if (!rl.isEmpty()) {
                for (Routelist routelist : rl) {
                    Routelist vr = em.getReference(Routelist.class, routelist.getIdRouteList());
                    if (vr.getIdTransport().getTransportValid()) {
                        vr.setIsValid(true);
                        em.merge(vr);
                    }
                }
            }
        }
    }

    public Health findHealthById(int idHealth) {
        return em.find(Health.class, idHealth);
    }
    
    public boolean isChecked(String name){
        Query query = em.createQuery("SELECT h FROM Health h WHERE h.driver = ?1", String.class);
        query.setParameter(1, name);
        return query.getResultList().isEmpty();
    }
}
