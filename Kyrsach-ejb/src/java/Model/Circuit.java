/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ceparator
 */
@Entity
@Table(name = "circuit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Circuit.findAll", query = "SELECT c FROM Circuit c"),
    @NamedQuery(name = "Circuit.findByIdCircuit", query = "SELECT c FROM Circuit c WHERE c.idCircuit = :idCircuit"),
    @NamedQuery(name = "Circuit.findByStopNumber", query = "SELECT c FROM Circuit c WHERE c.stopNumber = :stopNumber"),
    @NamedQuery(name = "Circuit.findByTyda", query = "SELECT c FROM Circuit c WHERE c.tyda = :tyda")})
public class Circuit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCircuit")
    private Integer idCircuit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stopNumber")
    private int stopNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tyda")
    private boolean tyda;
    @JoinColumn(name = "idRoute", referencedColumnName = "idRoute")
    @ManyToOne(optional = false)
    private Route idRoute;
    @JoinColumn(name = "idStop", referencedColumnName = "idStop")
    @ManyToOne(optional = false)
    private Stop idStop;

    public Circuit() {
    }

    public Circuit(Integer idCircuit) {
        this.idCircuit = idCircuit;
    }

    public Circuit(Integer idCircuit, int stopNumber, boolean tyda) {
        this.idCircuit = idCircuit;
        this.stopNumber = stopNumber;
        this.tyda = tyda;
    }

    public Integer getIdCircuit() {
        return idCircuit;
    }

    public void setIdCircuit(Integer idCircuit) {
        this.idCircuit = idCircuit;
    }

    public int getStopNumber() {
        return stopNumber;
    }

    public void setStopNumber(int stopNumber) {
        this.stopNumber = stopNumber;
    }

    public boolean getTyda() {
        return tyda;
    }

    public void setTyda(boolean tyda) {
        this.tyda = tyda;
    }

    public Route getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(Route idRoute) {
        this.idRoute = idRoute;
    }

    public Stop getIdStop() {
        return idStop;
    }

    public void setIdStop(Stop idStop) {
        this.idStop = idStop;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCircuit != null ? idCircuit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Circuit)) {
            return false;
        }
        Circuit other = (Circuit) object;
        if ((this.idCircuit == null && other.idCircuit != null) || (this.idCircuit != null && !this.idCircuit.equals(other.idCircuit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Circuit[ idCircuit=" + idCircuit + " ]";
    }
    
}
