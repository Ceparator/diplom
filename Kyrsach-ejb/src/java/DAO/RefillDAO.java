/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Refill;
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
public class RefillDAO {

    @PersistenceContext(unitName = "Kyrsach-ejbPU")
    private EntityManager em;

    public void deleteRefill(Refill selectedRefill) throws Exception {
        Refill someRefill = em.getReference(Refill.class, selectedRefill.getIdRefill());
        em.remove(someRefill);
    }

    public List<Refill> getAllRefills() {
        Query query = em.createQuery("SELECT r FROM Refill r", Refill.class);
        return query.getResultList();
    }

    public void addRefill(Refill refill) {
        em.persist(refill);
    }

    public void editRefill(Refill refill) {
        em.merge(refill);
    }

    public Refill findRefillById(int idRefill) {
        return em.find(Refill.class, idRefill);
    }

}
