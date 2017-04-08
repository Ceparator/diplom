/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import DAO.StopDAOInterface;
import Model.Stop;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import org.icefaces.ace.model.table.RowStateMap;

/**
 *
 * @author Ceparator
 */
@Named(value = "stopBean")
@SessionScoped
public class StopBean implements Serializable {

    @EJB
    private StopDAOInterface stopDAO;

    private Stop stop;

    @PostConstruct
    private void initializeBean() {
        stop = new Stop();
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    private RowStateMap stateMap;

    public RowStateMap getStateMap() {
        return stateMap;
    }

    public void setStateMap(RowStateMap stateMap) {
        this.stateMap = stateMap;
    }

    public void actionListener(ActionEvent event) throws Exception {
        List<Stop> selectedStopList = (List<Stop>) stateMap.getSelected();
        stopDAO.deleteStop(selectedStopList);
    }

    public List<Stop> getAllStops() throws Exception {
        return stopDAO.getAllStops();
    }

    public String ToEditStop(Stop stop) {
        this.stop = stop;
        return "/editStop.xhtml";
    }

    public String editStop() throws SQLException, Exception {
        stopDAO.editStop(stop);
        this.stop = new Stop();
        return "/allStops.xhtml";
    }
}
