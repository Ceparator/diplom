/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ceparator
 */
@Entity
@Table(name = "schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s"),
    @NamedQuery(name = "Schedule.findByIdSchedule", query = "SELECT s FROM Schedule s WHERE s.idSchedule = :idSchedule"),
    @NamedQuery(name = "Schedule.findByFirstTime", query = "SELECT s FROM Schedule s WHERE s.firstTime = :firstTime"),
    @NamedQuery(name = "Schedule.findByLastTime", query = "SELECT s FROM Schedule s WHERE s.lastTime = :lastTime"),
    @NamedQuery(name = "Schedule.findByTyda", query = "SELECT s FROM Schedule s WHERE s.tyda = :tyda")})
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSchedule")
    private Integer idSchedule;
    @Basic(optional = false)
    @NotNull
    @Column(name = "firstTime")
    @Temporal(TemporalType.TIME)
    private Date firstTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lastTime")
    @Temporal(TemporalType.TIME)
    private Date lastTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tyda")
    private boolean tyda;
    @JoinColumn(name = "idRouteList", referencedColumnName = "idRouteList")
    @ManyToOne(optional = false)
    private Routelist idRouteList;

    public Schedule() {
    }

    public Schedule(Integer idSchedule) {
        this.idSchedule = idSchedule;
    }

    public Schedule(Integer idSchedule, Date firstTime, Date lastTime, boolean tyda) {
        this.idSchedule = idSchedule;
        this.firstTime = firstTime;
        this.lastTime = lastTime;
        this.tyda = tyda;
    }

    public Integer getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(Integer idSchedule) {
        this.idSchedule = idSchedule;
    }

    public Date getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public boolean getTyda() {
        return tyda;
    }

    public void setTyda(boolean tyda) {
        this.tyda = tyda;
    }

    public Routelist getIdRouteList() {
        return idRouteList;
    }

    public void setIdRouteList(Routelist idRouteList) {
        this.idRouteList = idRouteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSchedule != null ? idSchedule.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schedule)) {
            return false;
        }
        Schedule other = (Schedule) object;
        if ((this.idSchedule == null && other.idSchedule != null) || (this.idSchedule != null && !this.idSchedule.equals(other.idSchedule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Schedule[ idSchedule=" + idSchedule + " ]";
    }
    
}
