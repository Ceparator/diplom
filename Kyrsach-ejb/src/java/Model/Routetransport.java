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
@Table(name = "routetransport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Routetransport.findAll", query = "SELECT r FROM Routetransport r"),
    @NamedQuery(name = "Routetransport.findByIdRouteTransport", query = "SELECT r FROM Routetransport r WHERE r.idRouteTransport = :idRouteTransport"),
    @NamedQuery(name = "Routetransport.findByTransportNumber", query = "SELECT r FROM Routetransport r WHERE r.transportNumber = :transportNumber"),
    @NamedQuery(name = "Routetransport.findByOptimalNumber", query = "SELECT r FROM Routetransport r WHERE r.optimalNumber = :optimalNumber")})
public class Routetransport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRouteTransport")
    private Integer idRouteTransport;
    @Basic(optional = false)
    @NotNull
    @Column(name = "transportNumber")
    private int transportNumber;
    @Column(name = "optimalNumber")
    private Integer optimalNumber;
    @JoinColumn(name = "idRoute", referencedColumnName = "idRoute")
    @ManyToOne(optional = false)
    private Route idRoute;
    @JoinColumn(name = "idShift", referencedColumnName = "idShift")
    @ManyToOne(optional = false)
    private Shift idShift;

    public Routetransport() {
    }

    public Routetransport(Integer idRouteTransport) {
        this.idRouteTransport = idRouteTransport;
    }

    public Routetransport(Integer idRouteTransport, int transportNumber) {
        this.idRouteTransport = idRouteTransport;
        this.transportNumber = transportNumber;
    }

    public Integer getIdRouteTransport() {
        return idRouteTransport;
    }

    public void setIdRouteTransport(Integer idRouteTransport) {
        this.idRouteTransport = idRouteTransport;
    }

    public int getTransportNumber() {
        return transportNumber;
    }

    public void setTransportNumber(int transportNumber) {
        this.transportNumber = transportNumber;
    }

    public Integer getOptimalNumber() {
        return optimalNumber;
    }

    public void setOptimalNumber(Integer optimalNumber) {
        this.optimalNumber = optimalNumber;
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
        hash += (idRouteTransport != null ? idRouteTransport.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Routetransport)) {
            return false;
        }
        Routetransport other = (Routetransport) object;
        if ((this.idRouteTransport == null && other.idRouteTransport != null) || (this.idRouteTransport != null && !this.idRouteTransport.equals(other.idRouteTransport))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Routetransport[ idRouteTransport=" + idRouteTransport + " ]";
    }
    
}
