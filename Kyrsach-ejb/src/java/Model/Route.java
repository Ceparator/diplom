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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ceparator
 */
@Entity
@Table(name = "route")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Route.findAll", query = "SELECT r FROM Route r"),
    @NamedQuery(name = "Route.findByIdRoute", query = "SELECT r FROM Route r WHERE r.idRoute = :idRoute"),
    @NamedQuery(name = "Route.findByNumber", query = "SELECT r FROM Route r WHERE r.number = :number"),
    @NamedQuery(name = "Route.findByRating", query = "SELECT r FROM Route r WHERE r.rating = :rating"),
    @NamedQuery(name = "Route.findByPrice", query = "SELECT r FROM Route r WHERE r.price = :price"),
    @NamedQuery(name = "Route.findByVremya", query = "SELECT r FROM Route r WHERE r.vremya = :vremya"),
    @NamedQuery(name = "Route.findByTransportNumber", query = "SELECT r FROM Route r WHERE r.transportNumber = :transportNumber")})
public class Route implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRoute")
    private Integer idRoute;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number")
    private int number;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rating")
    private double rating;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @Column(name = "vremya")
    private Integer vremya;
    @Column(name = "transportNumber")
    private Integer transportNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRoute")
    private List<Routetransport> routetransportList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRoute")
    private List<Circuit> circuitList;
    @JoinColumn(name = "firstStop", referencedColumnName = "idStop")
    @ManyToOne(optional = false)
    private Stop firstStop;
    @JoinColumn(name = "lastStop", referencedColumnName = "idStop")
    @ManyToOne(optional = false)
    private Stop lastStop;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRoute")
    private List<Routelist> routelistList;

    public Route() {
    }

    public Route(Integer idRoute) {
        this.idRoute = idRoute;
    }

    public Route(Integer idRoute, int number, double rating, int price) {
        this.idRoute = idRoute;
        this.number = number;
        this.rating = rating;
        this.price = price;
    }

    public Integer getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(Integer idRoute) {
        this.idRoute = idRoute;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getVremya() {
        return vremya;
    }

    public void setVremya(Integer vremya) {
        this.vremya = vremya;
    }

    public Integer getTransportNumber() {
        return transportNumber;
    }

    public void setTransportNumber(Integer transportNumber) {
        this.transportNumber = transportNumber;
    }

    @XmlTransient
    public List<Routetransport> getRoutetransportList() {
        return routetransportList;
    }

    public void setRoutetransportList(List<Routetransport> routetransportList) {
        this.routetransportList = routetransportList;
    }

    @XmlTransient
    public List<Circuit> getCircuitList() {
        return circuitList;
    }

    public void setCircuitList(List<Circuit> circuitList) {
        this.circuitList = circuitList;
    }

    public Stop getFirstStop() {
        return firstStop;
    }

    public void setFirstStop(Stop firstStop) {
        this.firstStop = firstStop;
    }

    public Stop getLastStop() {
        return lastStop;
    }

    public void setLastStop(Stop lastStop) {
        this.lastStop = lastStop;
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
        hash += (idRoute != null ? idRoute.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Route)) {
            return false;
        }
        Route other = (Route) object;
        if ((this.idRoute == null && other.idRoute != null) || (this.idRoute != null && !this.idRoute.equals(other.idRoute))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Route[ idRoute=" + idRoute + " ]";
    }
    
}
