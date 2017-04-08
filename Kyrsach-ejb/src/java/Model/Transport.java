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
@Table(name = "transport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transport.findAll", query = "SELECT t FROM Transport t"),
    @NamedQuery(name = "Transport.findByIdTransport", query = "SELECT t FROM Transport t WHERE t.idTransport = :idTransport"),
    @NamedQuery(name = "Transport.findBySerialNumber", query = "SELECT t FROM Transport t WHERE t.serialNumber = :serialNumber"),
    @NamedQuery(name = "Transport.findByFuelCapacity", query = "SELECT t FROM Transport t WHERE t.fuelCapacity = :fuelCapacity"),
    @NamedQuery(name = "Transport.findByTransportValid", query = "SELECT t FROM Transport t WHERE t.transportValid = :transportValid")})
public class Transport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTransport")
    private Integer idTransport;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "serialNumber")
    private String serialNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fuelCapacity")
    private int fuelCapacity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "transportValid")
    private boolean transportValid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransport")
    private List<Refill> refillList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransport")
    private List<Repair> repairList;
    @JoinColumn(name = "idFuel", referencedColumnName = "idFuel")
    @ManyToOne(optional = false)
    private Fuel idFuel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransport")
    private List<Routelist> routelistList;

    public Transport() {
    }

    public Transport(Integer idTransport) {
        this.idTransport = idTransport;
    }

    public Transport(Integer idTransport, String serialNumber, int fuelCapacity, boolean transportValid) {
        this.idTransport = idTransport;
        this.serialNumber = serialNumber;
        this.fuelCapacity = fuelCapacity;
        this.transportValid = transportValid;
    }

    public Integer getIdTransport() {
        return idTransport;
    }

    public void setIdTransport(Integer idTransport) {
        this.idTransport = idTransport;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public boolean getTransportValid() {
        return transportValid;
    }

    public void setTransportValid(boolean transportValid) {
        this.transportValid = transportValid;
    }

    @XmlTransient
    public List<Refill> getRefillList() {
        return refillList;
    }

    public void setRefillList(List<Refill> refillList) {
        this.refillList = refillList;
    }

    @XmlTransient
    public List<Repair> getRepairList() {
        return repairList;
    }

    public void setRepairList(List<Repair> repairList) {
        this.repairList = repairList;
    }

    public Fuel getIdFuel() {
        return idFuel;
    }

    public void setIdFuel(Fuel idFuel) {
        this.idFuel = idFuel;
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
        hash += (idTransport != null ? idTransport.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transport)) {
            return false;
        }
        Transport other = (Transport) object;
        if ((this.idTransport == null && other.idTransport != null) || (this.idTransport != null && !this.idTransport.equals(other.idTransport))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Transport[ idTransport=" + idTransport + " ]";
    }
    
}
