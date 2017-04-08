/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Fuel;
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
public class FuelDAO {

    @PersistenceContext(unitName = "Kyrsach-ejbPU")
    private EntityManager em;

    public void deleteFuel(Fuel selectedFuel) throws Exception {
        Fuel someFuel = em.getReference(Fuel.class, selectedFuel.getIdFuel());
        em.remove(someFuel);
    }

    public List<Fuel> getAllFuels() {
        Query query = em.createQuery("SELECT f FROM Fuel f", Fuel.class);
        return query.getResultList();
    }

    public void addFuel(Fuel fuel) {
        em.persist(fuel);
    }

    public void editFuel(Fuel fuel) {
        em.merge(fuel);
    }

    public Fuel findFuelById(int idFuel) {
        return em.find(Fuel.class, idFuel);
    }
}
