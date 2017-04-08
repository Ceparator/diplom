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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ceparator
 */
@Entity
@Table(name = "report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r"),
    @NamedQuery(name = "Report.findByIdReport", query = "SELECT r FROM Report r WHERE r.idReport = :idReport"),
    @NamedQuery(name = "Report.findByRouteNumber", query = "SELECT r FROM Report r WHERE r.routeNumber = :routeNumber"),
    @NamedQuery(name = "Report.findByIdShift", query = "SELECT r FROM Report r WHERE r.idShift = :idShift"),
    @NamedQuery(name = "Report.findByTickets", query = "SELECT r FROM Report r WHERE r.tickets = :tickets"),
    @NamedQuery(name = "Report.findByVremya", query = "SELECT r FROM Report r WHERE r.vremya = :vremya"),
    @NamedQuery(name = "Report.findByOccupancy", query = "SELECT r FROM Report r WHERE r.occupancy = :occupancy"),
    @NamedQuery(name = "Report.findBySumma", query = "SELECT r FROM Report r WHERE r.summa = :summa")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idReport")
    private Integer idReport;
    @Basic(optional = false)
    @NotNull
    @Column(name = "routeNumber")
    private int routeNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idShift")
    private int idShift;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tickets")
    private int tickets;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vremya")
    @Temporal(TemporalType.DATE)
    private Date vremya;
    @Column(name = "occupancy")
    private Integer occupancy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "summa")
    private int summa;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private Myuser username;

    @Transient
    private String shiftTime;

    public String getShiftTime() {
        return shiftTime;
    }

    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }

    public Report(Integer idReport, int routeNumber, int idShift, int tickets, Date vremya, Integer occupancy, int summa, Myuser username, String shiftTime) {
        this.idReport = idReport;
        this.routeNumber = routeNumber;
        this.idShift = idShift;
        this.tickets = tickets;
        this.vremya = vremya;
        this.occupancy = occupancy;
        this.summa = summa;
        this.username = username;
        this.shiftTime = shiftTime;
    }

    public Report() {
    }

    public Report(Integer idReport) {
        this.idReport = idReport;
    }

    public Report(Integer idReport, int routeNumber, int idShift, int tickets, Date vremya, int summa) {
        this.idReport = idReport;
        this.routeNumber = routeNumber;
        this.idShift = idShift;
        this.tickets = tickets;
        this.vremya = vremya;
        this.summa = summa;
    }

    public Integer getIdReport() {
        return idReport;
    }

    public void setIdReport(Integer idReport) {
        this.idReport = idReport;
    }

    public int getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(int routeNumber) {
        this.routeNumber = routeNumber;
    }

    public int getIdShift() {
        return idShift;
    }

    public void setIdShift(int idShift) {
        this.idShift = idShift;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public Date getVremya() {
        return vremya;
    }

    public void setVremya(Date vremya) {
        this.vremya = vremya;
    }

    public Integer getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(Integer occupancy) {
        this.occupancy = occupancy;
    }

    public int getSumma() {
        return summa;
    }

    public void setSumma(int summa) {
        this.summa = summa;
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
        hash += (idReport != null ? idReport.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.idReport == null && other.idReport != null) || (this.idReport != null && !this.idReport.equals(other.idReport))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Report[ idReport=" + idReport + " ]";
    }

}
