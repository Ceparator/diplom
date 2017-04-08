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
@Table(name = "myuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Myuser.findAll", query = "SELECT m FROM Myuser m"),
    @NamedQuery(name = "Myuser.findByUsername", query = "SELECT m FROM Myuser m WHERE m.username = :username"),
    @NamedQuery(name = "Myuser.findByPassword", query = "SELECT m FROM Myuser m WHERE m.password = :password")})
public class Myuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private List<Report> reportList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private List<MyuserRole> myuserRoleList;

    public Myuser() {
    }

    public Myuser(String username) {
        this.username = username;
    }

    public Myuser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    @XmlTransient
    public List<MyuserRole> getMyuserRoleList() {
        return myuserRoleList;
    }

    public void setMyuserRoleList(List<MyuserRole> myuserRoleList) {
        this.myuserRoleList = myuserRoleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Myuser)) {
            return false;
        }
        Myuser other = (Myuser) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Myuser[ username=" + username + " ]";
    }
    
}
