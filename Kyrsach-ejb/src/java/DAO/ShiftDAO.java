/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Routelist;
import Model.Shift;
import java.util.ArrayList;
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
public class ShiftDAO {

    @PersistenceContext(unitName = "Kyrsach-ejbPU")
    private EntityManager em;

    public void deleteShift(Shift selectedShift) throws Exception {
        Shift someShift = em.getReference(Shift.class, selectedShift.getIdShift());
        em.remove(someShift);
    }

    public List<Shift> getAllShifts() {
        Query query = em.createQuery("SELECT s FROM Shift s", Shift.class);
        return query.getResultList();
    }

    public List<Shift> getUserRouteShifts(String username, int idRoute) {
        Query query = em.createQuery("SELECT DISTINCT r.idShift FROM Routelist r WHERE (r.conductor = ?1 OR r.driver = ?1) AND r.idRoute.number = ?2", Routelist.class);
        query.setParameter(1, username);
        query.setParameter(2, idRoute);
        //List<Routelist> rol = query.getResultList();
        //List<Shift> shl = new ArrayList<>();
        //for (Routelist routelist : rol) {
        //    shl.add(routelist.getIdShift());
        //}
        return query.getResultList();
    }

    public void addShift(Shift shift) {
        em.persist(shift);
    }

    public void editShift(Shift shift) {
        em.merge(shift);
    }

    public Shift findShiftById(int idShift) {
        return em.find(Shift.class, idShift);
    }

}
