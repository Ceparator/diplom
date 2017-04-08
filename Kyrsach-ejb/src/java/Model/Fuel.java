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
@Table(name = "fuel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fuel.findAll", query = "SELECT f FROM Fuel f"),
    @NamedQuery(name = "Fuel.findByIdFuel", query = "SELECT f FROM Fuel f WHERE f.idFuel = :idFuel"),
    @NamedQuery(name = "Fuel.findByFuelNumber", query = "SELECT f FROM Fuel f WHERE f.fuelNumber = :fuelNumber"),
    @NamedQuery(name = "Fuel.findByFuelPrice", query = "SELECT f FROM Fuel f WHERE f.fuelPrice = :fuelPrice")})
public class Fuel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFuel")
    private Integer idFuel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "fuelNumber")
    private String fuelNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fuelPrice")
    private double fuelPrice;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFuel")
    private List<Refill> refillList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFuel")
    private List<Transport> transportList;

    public Fuel() {
    }

    public Fuel(Integer idFuel) {
        this.idFuel = idFuel;
    }

    public Fuel(Integer idFuel, String fuelNumber, double fuelPrice) {
        this.idFuel = idFuel;
        this.fuelNumber = fuelNumber;
        this.fuelPrice = fuelPrice;
    }

    public Integer getIdFuel() {
        return idFuel;
    }

    public void setIdFuel(Integer idFuel) {
        this.idFuel = idFuel;
    }

    public String getFuelNumber() {
        return fuelNumber;
    }

    public void setFuelNumber(String fuelNumber) {
        this.fuelNumber = fuelNumber;
    }

    public double getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(double fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

    @XmlTransient
    public List<Refill> getRefillList() {
        return refillList;
    }

    public void setRefillList(List<Refill> refillList) {
        this.refillList = refillList;
    }

    @XmlTransient
    public List<Transport> getTransportList() {
        return transportList;
    }

    public void setTransportList(List<Transport> transportList) {
        this.transportList = transportList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFuel != null ? idFuel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fuel)) {
            return false;
        }
        Fuel other = (Fuel) object;
        if ((this.idFuel == null && other.idFuel != null) || (this.idFuel != null && !this.idFuel.equals(other.idFuel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Fuel[ idFuel=" + idFuel + " ]";
    }
    
}
