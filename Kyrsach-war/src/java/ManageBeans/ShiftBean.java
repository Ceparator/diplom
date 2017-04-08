/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import DAO.ShiftDAO;
import Model.Shift;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ceparator
 */
public class ShiftBean {

    @EJB
    private ShiftDAO shiftDAO;

    private Shift shift;

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    @PostConstruct
    private void initializeBean() {
        shift = new Shift();
    }

    public String addNewShift() throws SQLException, Exception {
        shiftDAO.addShift(shift);
        return "/allShifts.xhtml";
    }

    public void deleteShift(Shift selectedShift) throws Exception {
        shiftDAO.deleteShift(selectedShift);
    }

    public List<Shift> getAllShifts() throws Exception {
        return shiftDAO.getAllShifts();
    }

    public String ToEditShift(Shift shift) {
        this.shift = shift;
        return "/editShift.xhtml";
    }

    public String editShift() throws SQLException, Exception {
        shiftDAO.editShift(shift);
        this.shift = new Shift();
        return "/allShifts.xhtml";
    }
}
