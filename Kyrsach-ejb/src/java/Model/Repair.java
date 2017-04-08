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
@Table(name = "repair")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Repair.findAll", query = "SELECT r FROM Repair r"),
    @NamedQuery(name = "Repair.findByIdRepair", query = "SELECT r FROM Repair r WHERE r.idRepair = :idRepair"),
    @NamedQuery(name = "Repair.findByMechanic", query = "SELECT r FROM Repair r WHERE r.mechanic = :mechanic"),
    @NamedQuery(name = "Repair.findByDriver", query = "SELECT r FROM Repair r WHERE r.driver = :driver"),
    @NamedQuery(name = "Repair.findByPrice", query = "SELECT r FROM Repair r WHERE r.price = :price"),
    @NamedQuery(name = "Repair.findByExpectedFixDate", query = "SELECT r FROM Repair r WHERE r.expectedFixDate = :expectedFixDate"),
    @NamedQuery(name = "Repair.findByActualFixDate", query = "SELECT r FROM Repair r WHERE r.actualFixDate = :actualFixDate")})
public class Repair implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRepair")
    private Integer idRepair;
    @Size(max = 20)
    @Column(name = "mechanic")
    private String mechanic;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "driver")
    private String driver;
    @Column(name = "price")
    private Integer price;
    @Column(name = "expectedFixDate")
    @Temporal(TemporalType.DATE)
    private Date expectedFixDate;
    @Column(name = "actualFixDate")
    @Temporal(TemporalType.DATE)
    private Date actualFixDate;
    @JoinColumn(name = "idTransport", referencedColumnName = "idTransport")
    @ManyToOne(optional = false)
    private Transport idTransport;

    public Repair() {
    }

    public Repair(Integer idRepair) {
        this.idRepair = idRepair;
    }

    public Repair(Integer idRepair, String driver) {
        this.idRepair = idRepair;
        this.driver = driver;
    }

    public Integer getIdRepair() {
        return idRepair;
    }

    public void setIdRepair(Integer idRepair) {
        this.idRepair = idRepair;
    }

    public String getMechanic() {
        return mechanic;
    }

    public void setMechanic(String mechanic) {
        this.mechanic = mechanic;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getExpectedFixDate() {
        return expectedFixDate;
    }

    public void setExpectedFixDate(Date expectedFixDate) {
        this.expectedFixDate = expectedFixDate;
    }

    public Date getActualFixDate() {
        return actualFixDate;
    }

    public void setActualFixDate(Date actualFixDate) {
        this.actualFixDate = actualFixDate;
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
        hash += (idRepair != null ? idRepair.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Repair)) {
            return false;
        }
        Repair other = (Repair) object;
        if ((this.idRepair == null && other.idRepair != null) || (this.idRepair != null && !this.idRepair.equals(other.idRepair))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Repair[ idRepair=" + idRepair + " ]";
    }
    
}
