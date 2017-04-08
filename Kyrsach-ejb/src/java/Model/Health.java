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
@Table(name = "health")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Health.findAll", query = "SELECT h FROM Health h"),
    @NamedQuery(name = "Health.findByIdHealth", query = "SELECT h FROM Health h WHERE h.idHealth = :idHealth"),
    @NamedQuery(name = "Health.findByDoctor", query = "SELECT h FROM Health h WHERE h.doctor = :doctor"),
    @NamedQuery(name = "Health.findByDriver", query = "SELECT h FROM Health h WHERE h.driver = :driver"),
    @NamedQuery(name = "Health.findByQualified", query = "SELECT h FROM Health h WHERE h.qualified = :qualified"),
    @NamedQuery(name = "Health.findByVremya", query = "SELECT h FROM Health h WHERE h.vremya = :vremya")})
public class Health implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHealth")
    private Integer idHealth;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "doctor")
    private String doctor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "driver")
    private String driver;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qualified")
    private boolean qualified;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vremya")
    @Temporal(TemporalType.DATE)
    private Date vremya;

    public Health() {
    }

    public Health(Integer idHealth) {
        this.idHealth = idHealth;
    }

    public Health(Integer idHealth, String doctor, String driver, boolean qualified, Date vremya) {
        this.idHealth = idHealth;
        this.doctor = doctor;
        this.driver = driver;
        this.qualified = qualified;
        this.vremya = vremya;
    }

    public Integer getIdHealth() {
        return idHealth;
    }

    public void setIdHealth(Integer idHealth) {
        this.idHealth = idHealth;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public boolean getQualified() {
        return qualified;
    }

    public void setQualified(boolean qualified) {
        this.qualified = qualified;
    }

    public Date getVremya() {
        return vremya;
    }

    public void setVremya(Date vremya) {
        this.vremya = vremya;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHealth != null ? idHealth.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Health)) {
            return false;
        }
        Health other = (Health) object;
        if ((this.idHealth == null && other.idHealth != null) || (this.idHealth != null && !this.idHealth.equals(other.idHealth))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Health[ idHealth=" + idHealth + " ]";
    }
    
}
