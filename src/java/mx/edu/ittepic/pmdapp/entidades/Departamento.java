/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.pmdapp.entidades;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Esteban
 */
@Entity
@Table(name = "departamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d")
    , @NamedQuery(name = "Departamento.findByIddepto", query = "SELECT d FROM Departamento d WHERE d.iddepto = :iddepto")
    , @NamedQuery(name = "Departamento.findByClavedepto", query = "SELECT d FROM Departamento d WHERE d.clavedepto = :clavedepto")
    , @NamedQuery(name = "Departamento.findByNombredepto", query = "SELECT d FROM Departamento d WHERE d.nombredepto = :nombredepto")})
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddepto")
    private Integer iddepto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "clavedepto")
    private String clavedepto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombredepto")
    private String nombredepto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddepto")
    private List<Empleado> empleadoList;
    @JoinColumn(name = "iddepe", referencedColumnName = "iddepe")
    @ManyToOne(optional = false)
    private Dependencia iddepe;

    public Departamento() {
    }

    public Departamento(Integer iddepto) {
        this.iddepto = iddepto;
    }

    public Departamento(Integer iddepto, String clavedepto, String nombredepto) {
        this.iddepto = iddepto;
        this.clavedepto = clavedepto;
        this.nombredepto = nombredepto;
    }

    public Integer getIddepto() {
        return iddepto;
    }

    public void setIddepto(Integer iddepto) {
        this.iddepto = iddepto;
    }

    public String getClavedepto() {
        return clavedepto;
    }

    public void setClavedepto(String clavedepto) {
        this.clavedepto = clavedepto;
    }

    public String getNombredepto() {
        return nombredepto;
    }

    public void setNombredepto(String nombredepto) {
        this.nombredepto = nombredepto;
    }

    @XmlTransient
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    public Dependencia getIddepe() {
        return iddepe;
    }

    public void setIddepe(Dependencia iddepe) {
        this.iddepe = iddepe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddepto != null ? iddepto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.iddepto == null && other.iddepto != null) || (this.iddepto != null && !this.iddepto.equals(other.iddepto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.ittepic.pmdapp.entidades.Departamento[ iddepto=" + iddepto + " ]";
    }
    
}
