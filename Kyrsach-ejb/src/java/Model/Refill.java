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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ceparator
 */
@Entity
@Table(name = "refill")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Refill.findAll", query = "SELECT r FROM Refill r"),
    @NamedQuery(name = "Refill.findByIdRefill", query = "SELECT r FROM Refill r WHERE r.idRefill = :idRefill"),
    @NamedQuery(name = "Refill.findByMechanic", query = "SELECT r FROM Refill r WHERE r.mechanic = :mechanic"),
    @NamedQuery(name = "Refill.findByVolume", query = "SELECT r FROM Refill r WHERE r.volume = :volume"),
    @NamedQuery(name = "Refill.findByCost", query = "SELECT r FROM Refill r WHERE r.cost = :cost"),
    @NamedQuery(name = "Refill.findByRefillDate", query = "SELECT r FROM Refill r WHERE r.refillDate = :refillDate")})
public class Refill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRefill")
    private Integer idRefill;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "mechanic")
    private String mechanic;
    @Basic(optional = false)
    @NotNull
    @Column(name = "volume")
    private int volume;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cost")
    private double cost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "refillDate")
    @Temporal(TemporalType.DATE)
    private Date refillDate;
    @JoinColumn(name = "idFuel", referencedColumnName = "idFuel")
    @ManyToOne(optional = false)
    private Fuel idFuel;
    @JoinColumn(name = "idTransport", referencedColumnName = "idTransport")
    @ManyToOne(optional = false)
    private Transport idTransport;

    public Refill() {
    }

    public Refill(Integer idRefill) {
        this.idRefill = idRefill;
    }

    public Refill(Integer idRefill, String mechanic, int volume, double cost, Date refillDate) {
        this.idRefill = idRefill;
        this.mechanic = mechanic;
        this.volume = volume;
        this.cost = cost;
        this.refillDate = refillDate;
    }

    public Integer getIdRefill() {
        return idRefill;
    }

    public void setIdRefill(Integer idRefill) {
        this.idRefill = idRefill;
    }

    public String getMechanic() {
        return mechanic;
    }

    public void setMechanic(String mechanic) {
        this.mechanic = mechanic;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getRefillDate() {
        return refillDate;
    }

    public void setRefillDate(Date refillDate) {
        this.refillDate = refillDate;
    }

    public Fuel getIdFuel() {
        return idFuel;
    }

    public void setIdFuel(Fuel idFuel) {
        this.idFuel = idFuel;
    }

    public Transport getIdTransport() {
        return idTransport;
    }

    public void setIdTransport(Transport idTransport) {
        this.idTransport = idTransport;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRefill != null ? idRefill.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Refill)) {
            return false;
        }
        Refill other = (Refill) object;
        if ((this.idRefill == null && other.idRefill != null) || (this.idRefill != null && !this.idRefill.equals(other.idRefill))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Refill[ idRefill=" + idRefill + " ]";
    }
    
}
