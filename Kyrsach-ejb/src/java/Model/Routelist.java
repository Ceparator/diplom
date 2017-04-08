/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ceparator
 */
@Entity
@Table(name = "routelist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Routelist.findAll", query = "SELECT r FROM Routelist r"),
    @NamedQuery(name = "Routelist.findByIdRouteList", query = "SELECT r FROM Routelist r WHERE r.idRouteList = :idRouteList"),
    @NamedQuery(name = "Routelist.findByDriver", query = "SELECT r FROM Routelist r WHERE r.driver = :driver"),
    @NamedQuery(name = "Routelist.findByConductor", query = "SELECT r FROM Routelist r WHERE r.conductor = :conductor"),
    @NamedQuery(name = "Routelist.findByIsValid", query = "SELECT r FROM Routelist r WHERE r.isValid = :isValid"),
    @NamedQuery(name = "Routelist.findByFromFirst", query = "SELECT r FROM Routelist r WHERE r.fromFirst = :fromFirst")})
public class Routelist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRouteList")
    private Integer idRouteList;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "driver")
    private String driver;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "conductor")
    private String conductor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isValid")
    private boolean isValid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fromFirst")
    private boolean fromFirst;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRouteList")
    private List<Schedule> scheduleList;
    @JoinColumn(name = "idTransport", referencedColumnName = "idTransport")
    @ManyToOne(optional = false)
    private Transport idTransport;
    @JoinColumn(name = "idRoute", referencedColumnName = "idRoute")
    @ManyToOne(optional = false)
    private Route idRoute;
    @JoinColumn(name = "idShift", referencedColumnName = "idShift")
    @ManyToOne(optional = false)
    private Shift idShift;

    public Routelist() {
    }

    public Routelist(Integer idRouteList) {
        this.idRouteList = idRouteList;
    }

    public Routelist(Integer idRouteList, String driver, String conductor, boolean isValid, boolean fromFirst) {
        this.idRouteList = idRouteList;
        this.driver = driver;
        this.conductor = conductor;
        this.isValid = isValid;
        this.fromFirst = fromFirst;
    }

    public Integer getIdRouteList() {
        return idRouteList;
    }

    public void setIdRouteList(Integer idRouteList) {
        this.idRouteList = idRouteList;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public boolean getFromFirst() {
        return fromFirst;
    }

    public void setFromFirst(boolean fromFirst) {
        this.fromFirst = fromFirst;
    }

    @XmlTransient
    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public Transport getIdTransport() {
        return idTransport;
    }

    public void setIdTransport(Transport idTransport) {
        this.idTransport = idTransport;
    }

    public Route getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(Route idRoute) {
        this.idRoute = idRoute;
    }

    public Shift getIdShift() {
        return idShift;
    }

    public void setIdShift(Shift idShift) {
        this.idShift = idShift;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRouteList != null ? idRouteList.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Routelist)) {
            return false;
        }
        Routelist other = (Routelist) object;
        if ((this.idRouteList == null && other.idRouteList != null) || (this.idRouteList != null && !this.idRouteList.equals(other.idRouteList))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Routelist[ idRouteList=" + idRouteList + " ]";
    }
    
}
