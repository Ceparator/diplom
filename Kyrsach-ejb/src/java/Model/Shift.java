/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ceparator
 */
@Entity
@Table(name = "shift")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shift.findAll", query = "SELECT s FROM Shift s"),
    @NamedQuery(name = "Shift.findByIdShift", query = "SELECT s FROM Shift s WHERE s.idShift = :idShift"),
    @NamedQuery(name = "Shift.findByStartTime", query = "SELECT s FROM Shift s WHERE s.startTime = :startTime"),
    @NamedQuery(name = "Shift.findByEndTime", query = "SELECT s FROM Shift s WHERE s.endTime = :endTime")})
public class Shift implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idShift")
    private Integer idShift;
    @Basic(optional = false)
    @NotNull
    @Column(name = "startTime")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "endTime")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idShift")
    private List<Routetransport> routetransportList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idShift")
    private List<Routelist> routelistList;

    public Shift() {
    }

    public Shift(Integer idShift) {
        this.idShift = idShift;
    }

    public Shift(Integer idShift, Date startTime, Date endTime) {
        this.idShift = idShift;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getIdShift() {
        return idShift;
    }

    public void setIdShift(Integer idShift) {
        this.idShift = idShift;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @XmlTransient
    public List<Routetransport> getRoutetransportList() {
        return routetransportList;
    }

    public void setRoutetransportList(List<Routetransport> routetransportList) {
        this.routetransportList = routetransportList;
    }

    @XmlTransient
    public List<Routelist> getRoutelistList() {
        return routelistList;
    }

    public void setRoutelistList(List<Routelist> routelistList) {
        this.routelistList = routelistList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idShift != null ? idShift.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shift)) {
            return false;
        }
        Shift other = (Shift) object;
        if ((this.idShift == null && other.idShift != null) || (this.idShift != null && !this.idShift.equals(other.idShift))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Shift[ idShift=" + idShift + " ]";
    }
    
}
