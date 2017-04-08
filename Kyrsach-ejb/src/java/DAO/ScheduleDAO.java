/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Route;
import Model.Routelist;
import Model.Schedule;
import Model.Shift;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
public class ScheduleDAO {

    @PersistenceContext(unitName = "Kyrsach-ejbPU")
    private EntityManager em;

    public List<Schedule> generateRouteSchedule(Route route, boolean fromFirst, int relaxAct, int foodBreakAct) throws SQLException, Exception {

        int relax = relaxAct;
        int foodBreak = foodBreakAct;

        List<Schedule> schedules = new ArrayList<>();

        List<Shift> shiftList = sortShifts();
        List<Shift> doneShifts = new ArrayList<>();

        Calendar firstTime = Calendar.getInstance();
        Calendar lastTime = Calendar.getInstance();
        Calendar midTime = Calendar.getInstance();
        Date midDate = new Date();
        Calendar scTime;

        for (Shift sh : shiftList) {

            Query query = em.createQuery("SELECT r FROM Routelist r WHERE r.idShift = ?1 AND r.isValid = ?2 AND r.idRoute = ?3 AND r.fromFirst = ?4", Routelist.class);
            query.setParameter(1, sh);
            query.setParameter(2, true);
            query.setParameter(3, route);
            query.setParameter(4, fromFirst);
            List<Routelist> currentShift = query.getResultList();

            if (!currentShift.isEmpty()) {
                if (isClearShift(sh, doneShifts) == null) {

                    int deltaTime = (currentShift.get(0).getIdRoute().getVremya() * 2 + 2 * relax) / currentShift.size();
                    int timePlus = 0 - deltaTime;

                    long t1;
                    long t2;

                    for (Routelist rl : currentShift) {
                        boolean tyda = true;

                        firstTime.setTime(rl.getIdShift().getStartTime());
                        lastTime.setTime(rl.getIdShift().getEndTime());

                        timePlus += deltaTime;

                        firstTime.add(Calendar.MINUTE, timePlus);
                        lastTime.add(Calendar.MINUTE, timePlus);

                        t1 = firstTime.getTimeInMillis();
                        t2 = lastTime.getTimeInMillis();
                        midDate.setTime(t1 + (t2 - t1) / 2);
                        midTime.setTime(midDate);

                        scTime = firstTime;
                        scTime.add(Calendar.MINUTE, -relax);

                        while (firstTime.before(lastTime)) {
                            if (scTime.after(midTime) && isLongShift(rl.getIdShift())) {
                                relax = foodBreak;
                            }
                            if (tyda) {
                                Schedule tSchedule = new Schedule();
                                scTime.add(Calendar.MINUTE, relax);
                                tSchedule.setIdRouteList(rl);
                                tSchedule.setFirstTime(scTime.getTime());
                                scTime.add(Calendar.MINUTE, rl.getIdRoute().getVremya());
                                tSchedule.setLastTime(scTime.getTime());
                                tSchedule.setTyda(tyda);
                                schedules.add(tSchedule);
                                relax = relaxAct;
                                tyda = false;

                            } else {
                                Schedule tSchedule = new Schedule();
                                tSchedule.setIdRouteList(rl);
                                scTime.add(Calendar.MINUTE, relax);
                                tSchedule.setLastTime(scTime.getTime());
                                scTime.add(Calendar.MINUTE, rl.getIdRoute().getVremya());
                                tSchedule.setFirstTime(scTime.getTime());
                                tSchedule.setTyda(tyda);
                                schedules.add(tSchedule);
                                relax = relaxAct;
                                tyda = true;
                            }
                        }
                    }
                } else {

                    int deltaTime = (currentShift.get(0).getIdRoute().getVremya() * 2 + 2 * relax) / currentShift.size();
                    int timePlus = 0 - deltaTime;
                    boolean difFound = false;
                    long difT = 0;

                    long t1;
                    long t2;

                    /*for (Routelist rl : currentShift) {
                        boolean tyda = true;

                        for (Schedule shc : schedules) {
                            if (shc.getFirstTime().after(rl.getIdShift().getStartTime()) && !difFound && shc.getTyda()) {
                                difT = rl.getIdShift().getStartTime().getTime() + rl.getIdShift().getStartTime().getTime() - shc.getFirstTime().getTime() / 2;
                                difT -= relax * 60 * 1000;
                                difFound = true;
                                break;
                            }
                        }*/
                    for (Routelist rl : currentShift) {
                        boolean tyda = true;

                        long maxRange = 0;
                        int shcId = 0;

                        if (!difFound) {
                            for (int i = 1; i < schedules.size(); i++) {
                                Schedule shc = schedules.get(i);
                                maxRange = shc.getFirstTime().getTime() - rl.getIdShift().getStartTime().getTime() - (deltaTime * 2 + relax * 2);
                                if (shc.getFirstTime().after(rl.getIdShift().getStartTime()) && shc.getTyda()) {
                                    if (maxRange < 0) {
                                        if (shc.getFirstTime().getTime() - schedules.get(i - 1).getFirstTime().getTime() > difT) {
                                            difT = shc.getFirstTime().getTime() - schedules.get(i - 1).getFirstTime().getTime();
                                            shcId = i;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                            }
                            difT = schedules.get(shcId).getFirstTime().getTime() - (difT / 2);
                            difT -= relax * 60 * 1000;
                            difFound = true;
                        }

                        firstTime.setTime(new Date(difT));
                        lastTime.setTime(rl.getIdShift().getEndTime());

                        timePlus += deltaTime;

                        firstTime.add(Calendar.MINUTE, timePlus);
                        lastTime.add(Calendar.MINUTE, timePlus);

                        t1 = firstTime.getTimeInMillis();
                        t2 = lastTime.getTimeInMillis();
                        midDate.setTime(t1 + (t2 - t1) / 2);
                        midTime.setTime(midDate);

                        scTime = firstTime;
                        scTime.add(Calendar.MINUTE, -relax);

                        while (firstTime.before(lastTime)) {
                            if (scTime.after(midTime) && isLongShift(rl.getIdShift())) {
                                relax = foodBreak;
                            }
                            if (tyda) {
                                Schedule tSchedule = new Schedule();
                                scTime.add(Calendar.MINUTE, relax);
                                tSchedule.setIdRouteList(rl);
                                tSchedule.setFirstTime(scTime.getTime());
                                scTime.add(Calendar.MINUTE, rl.getIdRoute().getVremya());
                                tSchedule.setLastTime(scTime.getTime());
                                tSchedule.setTyda(tyda);
                                schedules.add(tSchedule);
                                relax = relaxAct;
                                tyda = false;

                            } else {
                                Schedule tSchedule = new Schedule();
                                tSchedule.setIdRouteList(rl);
                                scTime.add(Calendar.MINUTE, relax);
                                tSchedule.setLastTime(scTime.getTime());
                                scTime.add(Calendar.MINUTE, rl.getIdRoute().getVremya());
                                tSchedule.setFirstTime(scTime.getTime());
                                tSchedule.setTyda(tyda);
                                schedules.add(tSchedule);
                                relax = relaxAct;
                                tyda = true;
                            }
                        }
                    }
                }
                doneShifts.add(sh);
            }
        }
        return schedules;
    }

    public boolean isLongShift(Shift shift) {
        Calendar time1 = Calendar.getInstance();
        Calendar time2 = Calendar.getInstance();
        time1.setTime(shift.getStartTime());
        time2.setTime(shift.getEndTime());
        long milliseconds = time2.getTimeInMillis() - time1.getTimeInMillis();
        int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);
        return hours > 7;
    }

    public Shift isClearShift(Shift shift, List<Shift> shiftList) {
        for (Shift sh : shiftList) {
            if (shift.getStartTime().before(sh.getEndTime()) && shift.getEndTime().after(sh.getStartTime())) {
                return sh;
            }
        }
        return null;
    }

    public List<Shift> sortShifts() {
        Query query = em.createQuery("SELECT s FROM Shift s", Shift.class);
        List<Shift> shifts = query.getResultList();
        if (shifts.size() > 0) {
            Collections.sort(shifts, new Comparator<Shift>() {
                @Override
                public int compare(Shift object1, Shift object2) {
                    return object1.getStartTime().compareTo(object2.getStartTime());
                }
            });
        }
        if (shifts.size() > 0) {
            Collections.sort(shifts, new Comparator<Shift>() {
                @Override
                public int compare(Shift object1, Shift object2) {
                    Calendar firstTime = Calendar.getInstance();
                    Calendar secondTime = Calendar.getInstance();
                    firstTime.setTime(object1.getStartTime());
                    secondTime.setTime(object1.getEndTime());
                    Long t1 = firstTime.getTimeInMillis();
                    Long t2 = secondTime.getTimeInMillis();
                    Long o1Time = t2 - t1;

                    firstTime.setTime(object2.getStartTime());
                    secondTime.setTime(object2.getEndTime());
                    t1 = firstTime.getTimeInMillis();
                    t2 = secondTime.getTimeInMillis();

                    Long o2Time = t2 - t1;
                    return o2Time.compareTo(o1Time);
                }
            });
        }
        return shifts;
    }

    public void deleteSchedule(Schedule selectedSchedule) {
        Schedule someSchedule = em.getReference(Schedule.class, selectedSchedule.getIdSchedule());
        em.remove(someSchedule);
    }

    public void acceptSchedules(List<Schedule> schedules) {
        for (Schedule schedule : schedules) {
            em.persist(schedule);
        }
    }

    public boolean isEmptySchedule(Route rt, boolean isFirst) {
        Query query = em.createQuery("SELECT s FROM Schedule s WHERE S.idRouteList.idRoute = ?1 AND s.idRouteList.fromFirst = ?2", Schedule.class);
        query.setParameter(1, rt);
        query.setParameter(2, isFirst);
        List<Schedule> scheduleList = query.getResultList();
        return scheduleList.isEmpty();
    }

    public List<Schedule> getAllRouteSchedules(Route rt, boolean isFirst) {
        Query query = em.createQuery("SELECT s FROM Schedule s WHERE S.idRouteList.idRoute = ?1 AND s.idRouteList.fromFirst = ?2", Schedule.class);
        query.setParameter(1, rt);
        query.setParameter(2, isFirst);
        return query.getResultList();
    }

    public boolean exist(Schedule sc) {
        Query query = em.createQuery("SELECT s FROM Schedule s WHERE s.idSchedule = ?1", Schedule.class);
        query.setParameter(1, sc.getIdSchedule());
        List<Schedule> myuserList = query.getResultList();
        if (myuserList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void deleteAllSchedules(Route rt, boolean isFirst) {
        Query query = em.createQuery("SELECT s FROM Schedule s WHERE S.idRouteList.idRoute = ?1 AND s.idRouteList.fromFirst = ?2", Schedule.class);
        query.setParameter(1, rt);
        query.setParameter(2, isFirst);
        List<Schedule> scheduleList = query.getResultList();
        for (Schedule schedule : scheduleList) {
            Schedule someSchedule = em.getReference(Schedule.class, schedule.getIdSchedule());
            em.remove(someSchedule);
        }
    }

    public List<Schedule> getSchedules(Route rt, boolean isFirst, int relax, int foodBreak) throws Exception {
        if (isFirst) {
            if (isEmptySchedule(rt, true)) {
                return generateRouteSchedule(rt, true, relax, foodBreak);
            } else {
                return getAllRouteSchedules(rt, true);
            }
        } else if (isEmptySchedule(rt, false)) {
            return generateRouteSchedule(rt, false, relax, foodBreak);
        } else {
            return getAllRouteSchedules(rt, false);
        }
    }
}
