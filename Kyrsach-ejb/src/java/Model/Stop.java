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
@Table(name = "stop")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stop.findAll", query = "SELECT s FROM Stop s"),
    @NamedQuery(name = "Stop.findByIdStop", query = "SELECT s FROM Stop s WHERE s.idStop = :idStop"),
    @NamedQuery(name = "Stop.findByName", query = "SELECT s FROM Stop s WHERE s.name = :name"),
    @NamedQuery(name = "Stop.findByCoordX", query = "SELECT s FROM Stop s WHERE s.coordX = :coordX"),
    @NamedQuery(name = "Stop.findByCoordY", query = "SELECT s FROM Stop s WHERE s.coordY = :coordY")})
public class Stop implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idStop")
    private Integer idStop;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "coordX")
    private double coordX;
    @Basic(optional = false)
    @NotNull
    @Column(name = "coordY")
    private double coordY;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStop")
    private List<Circuit> circuitList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "firstStop")
    private List<Route> routeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastStop")
    private List<Route> routeList1;

    public Stop() {
    }

    public Stop(Integer idStop) {
        this.idStop = idStop;
    }

    public Stop(Integer idStop, String name, double coordX, double coordY) {
        this.idStop = idStop;
        this.name = name;
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public Integer getIdStop() {
        return idStop;
    }

    public void setIdStop(Integer idStop) {
        this.idStop = idStop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCoordX() {
        return coordX;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    @XmlTransient
    public List<Circuit> getCircuitList() {
        return circuitList;
    }

    public void setCircuitList(List<Circuit> circuitList) {
        this.circuitList = circuitList;
    }

    @XmlTransient
    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }

    @XmlTransient
    public List<Route> getRouteList1() {
        return routeList1;
    }

    public void setRouteList1(List<Route> routeList1) {
        this.routeList1 = routeList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStop != null ? idStop.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stop)) {
            return false;
        }
        Stop other = (Stop) object;
        if ((this.idStop == null && other.idStop != null) || (this.idStop != null && !this.idStop.equals(other.idStop))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Stop[ idStop=" + idStop + " ]";
    }
    
}
