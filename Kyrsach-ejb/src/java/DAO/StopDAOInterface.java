/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Stop;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author Ceparator
 */
@Remote
public interface StopDAOInterface {

    void addStop(Stop stop);

    void deleteStop(List<Stop> selectedStopList) throws Exception;

    void editStop(Stop stop);

    List<Stop> getAllStops();
}
