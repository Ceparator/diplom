/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import DAO.FuelDAO;
import Model.Fuel;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ceparator
 */
public class FuelBean {

    @EJB
    private FuelDAO fuelDAO;

    private Fuel fuel;

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    @PostConstruct
    private void initializeBean() {
        fuel = new Fuel();
    }

    public String addNewFuel() throws SQLException, Exception {
        fuelDAO.addFuel(fuel);
        return "/allFuels.xhtml";
    }

    public void deleteFuel(Fuel selectedFuel) throws Exception {
        fuelDAO.deleteFuel(selectedFuel);
    }

    public List<Fuel> getAllFuels() throws Exception {
        return fuelDAO.getAllFuels();
    }

    public String ToEditFuel(Fuel fuel) {
        this.fuel = fuel;
        return "/editFuel.xhtml";
    }

    public String editFuel() throws SQLException, Exception {
        fuelDAO.editFuel(fuel);
        this.fuel = new Fuel();
        return "/allFuels.xhtml";
    } 
}
