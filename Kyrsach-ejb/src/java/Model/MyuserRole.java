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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ceparator
 */
@Entity
@Table(name = "myuser_role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MyuserRole.findAll", query = "SELECT m FROM MyuserRole m"),
    @NamedQuery(name = "MyuserRole.findByIdRole", query = "SELECT m FROM MyuserRole m WHERE m.idRole = :idRole"),
    @NamedQuery(name = "MyuserRole.findByRole", query = "SELECT m FROM MyuserRole m WHERE m.role = :role")})
public class MyuserRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRole")
    private Integer idRole;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "role")
    private String role;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private Myuser username;

    public MyuserRole() {
    }

    public MyuserRole(Integer idRole) {
        this.idRole = idRole;
    }

    public MyuserRole(Integer idRole, String role) {
        this.idRole = idRole;
        this.role = role;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Myuser getUsername() {
        return username;
    }

    public void setUsername(Myuser username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRole != null ? idRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MyuserRole)) {
            return false;
        }
        MyuserRole other = (MyuserRole) object;
        if ((this.idRole == null && other.idRole != null) || (this.idRole != null && !this.idRole.equals(other.idRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.MyuserRole[ idRole=" + idRole + " ]";
    }
    
}
