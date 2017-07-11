/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.pmdapp.entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Esteban
 */
@Entity
@Table(name = "administracion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administracion.findAll", query = "SELECT a FROM Administracion a")
    , @NamedQuery(name = "Administracion.findByIdadmon", query = "SELECT a FROM Administracion a WHERE a.idadmon = :idadmon")
    , @NamedQuery(name = "Administracion.findByIdmun", query = "SELECT a FROM Administracion a WHERE a.idmun = :idmun")
    , @NamedQuery(name = "Administracion.findByClaveadmon", query = "SELECT a FROM Administracion a WHERE a.claveadmon = :claveadmon")
    , @NamedQuery(name = "Administracion.findByNombreadmon", query = "SELECT a FROM Administracion a WHERE a.nombreadmon = :nombreadmon")
    , @NamedQuery(name = "Administracion.findByFechaini", query = "SELECT a FROM Administracion a WHERE a.fechaini = :fechaini")
    , @NamedQuery(name = "Administracion.findByFechafin", query = "SELECT a FROM Administracion a WHERE a.fechafin = :fechafin")})
public class Administracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idadmon")
    private Integer idadmon;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "claveadmon")
    private String claveadmon;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombreadmon")
    private String nombreadmon;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaini")
    @Temporal(TemporalType.DATE)
    private Date fechaini;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idadmon")
    private List<Admondepe> admondepeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idadmon")
    private List<Plan> planList;
    @JoinColumn(name = "idmun", referencedColumnName = "idmun")
    @ManyToOne(optional = false)
    private Municipio idmun;

    public Administracion() {
    }

    public Administracion(Integer idadmon) {
        this.idadmon = idadmon;
    }

    public Administracion(Integer idadmon, String claveadmon, String nombreadmon, Date fechaini, Date fechafin) {
        this.idadmon = idadmon;
        this.claveadmon = claveadmon;
        this.nombreadmon = nombreadmon;
        this.fechaini = fechaini;
        this.fechafin = fechafin;
    }

    public Integer getIdadmon() {
        return idadmon;
    }

    public void setIdadmon(Integer idadmon) {
        this.idadmon = idadmon;
    }

    public String getClaveadmon() {
        return claveadmon;
    }

    public void setClaveadmon(String claveadmon) {
        this.claveadmon = claveadmon;
    }

    public String getNombreadmon() {
        return nombreadmon;
    }

    public void setNombreadmon(String nombreadmon) {
        this.nombreadmon = nombreadmon;
    }

    public Date getFechaini() {
        return fechaini;
    }

    public void setFechaini(Date fechaini) {
        this.fechaini = fechaini;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    @XmlTransient
    public List<Admondepe> getAdmondepeList() {
        return admondepeList;
    }

    public void setAdmondepeList(List<Admondepe> admondepeList) {
        this.admondepeList = admondepeList;
    }

    @XmlTransient
    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }

    public Municipio getIdmun() {
        return idmun;
    }

    public void setIdmun(Municipio idmun) {
        this.idmun = idmun;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idadmon != null ? idadmon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administracion)) {
            return false;
        }
        Administracion other = (Administracion) object;
        if ((this.idadmon == null && other.idadmon != null) || (this.idadmon != null && !this.idadmon.equals(other.idadmon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.ittepic.pmdapp.entidades.Administracion[ idadmon=" + idadmon + " ]";
    }
    
}
