/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import DAO.ScheduleDAO;
import Model.Route;
import Model.Schedule;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ceparator
 */
@ViewScoped
@ManagedBean(name = "scheduleBean")
public class ScheduleBean {

    @EJB
    private ScheduleDAO scheduleDAO;

    private Schedule schedule;
    private List<Schedule> firstSchedules;
    private List<Schedule> secondSchedules;
    private int relax;
    private int foodBreak;

    @ManagedProperty(value = "#{routeBean}")
    private RouteBean routeBean;

    public void setRouteBean(RouteBean routeBean) {
        this.routeBean = routeBean;
    }

    public int getRelax() {
        return relax;
    }

    public void setRelax(int relax) {
        this.relax = relax;
    }

    public int getFoodBreak() {
        return foodBreak;
    }

    public void setFoodBreak(int foodBreak) {
        this.foodBreak = foodBreak;
    }

    public List<Schedule> getFirstSchedules() {

        return firstSchedules;
    }

    public void setFirstSchedules(List<Schedule> firstSchedules) {
        this.firstSchedules = firstSchedules;
    }

    public List<Schedule> getSecondSchedules() {
        return secondSchedules;
    }

    public void setSecondSchedules(List<Schedule> secondSchedules) {
        this.secondSchedules = secondSchedules;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @PostConstruct
    private void initializeBean() {
        schedule = new Schedule();
        foodBreak = 30;
        relax = 10;
        System.out.println("ManageBeans.ScheduleBean.initializeBean()-------------------");
        try {
            firstSchedules = scheduleDAO.getSchedules(routeBean.getRoute(), true, relax, foodBreak);
                    System.out.println("ManageBeans.ScheduleBean.initializeBean()-------------------2222");

        } catch (Exception ex) {
            Logger.getLogger(ScheduleBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            secondSchedules = scheduleDAO.getSchedules(routeBean.getRoute(), false, relax, foodBreak);
            
                    System.out.println("ManageBeans.ScheduleBean.initializeBean()-------------------3333  " + routeBean.getRoute().getNumber());
                    System.out.println("ManageBeans.ScheduleBean.initializeBean()-------------------3333  " + secondSchedules.size());

        } catch (Exception ex) {
            Logger.getLogger(ScheduleBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteSchedule(Schedule selectedSchedule) throws Exception {
        if (isCointains(firstSchedules, selectedSchedule)) {
            firstSchedules.remove(selectedSchedule);
        } else {
            secondSchedules.remove(selectedSchedule);
        }
        if (scheduleDAO.exist(selectedSchedule)) {
            scheduleDAO.deleteSchedule(selectedSchedule);
        }
    }

    public void acceptSchedules(boolean isFirst) throws Exception {
        if (isFirst) {
            scheduleDAO.acceptSchedules(firstSchedules);
        } else {
            scheduleDAO.acceptSchedules(secondSchedules);
        }
    }

    public void regenerateSchedules(Route rt, boolean isFirst) throws Exception {
        if (isFirst) {
            scheduleDAO.deleteAllSchedules(rt, true);
            firstSchedules = scheduleDAO.getSchedules(routeBean.getRoute(), true, relax, foodBreak);
        } else {
            scheduleDAO.deleteAllSchedules(rt, false);
            secondSchedules = scheduleDAO.getSchedules(routeBean.getRoute(), false, relax, foodBreak);
        }
    }

    public boolean isCointains(List<Schedule> shcList, Schedule shc) throws Exception {
        for (Schedule sched : shcList) {
            if (sched.getIdRouteList().equals(shc.getIdRouteList()) && (sched.getTyda() == shc.getTyda()) && sched.getFirstTime().equals(shc.getFirstTime()) && sched.getLastTime().equals(shc.getLastTime())) {
                return true;
            }
        }
        return false;
    }
}
