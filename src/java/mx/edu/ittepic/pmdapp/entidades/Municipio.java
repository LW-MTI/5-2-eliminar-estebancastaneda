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
@Table(name = "municipio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Municipio.findAll", query = "SELECT m FROM Municipio m")
    , @NamedQuery(name = "Municipio.findByIdmun", query = "SELECT m FROM Municipio m WHERE m.idmun = :idmun")
    , @NamedQuery(name = "Municipio.findByClavemun", query = "SELECT m FROM Municipio m WHERE m.clavemun = :clavemun")
    , @NamedQuery(name = "Municipio.findByNombremun", query = "SELECT m FROM Municipio m WHERE m.nombremun = :nombremun")})
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmun")
    private Integer idmun;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "clavemun")
    private String clavemun;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombremun")
    private String nombremun;
    @JoinColumn(name = "ident", referencedColumnName = "ident")
    @ManyToOne(optional = false)
    private Entidad ident;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmun")
    private List<Administracion> administracionList;

    public Municipio() {
    }

    public Municipio(Integer idmun) {
        this.idmun = idmun;
    }

    public Municipio(Integer idmun, String clavemun, String nombremun) {
        this.idmun = idmun;
        this.clavemun = clavemun;
        this.nombremun = nombremun;
    }

    public Integer getIdmun() {
        return idmun;
    }

    public void setIdmun(Integer idmun) {
        this.idmun = idmun;
    }

    public String getClavemun() {
        return clavemun;
    }

    public void setClavemun(String clavemun) {
        this.clavemun = clavemun;
    }

    public String getNombremun() {
        return nombremun;
    }

    public void setNombremun(String nombremun) {
        this.nombremun = nombremun;
    }

    public Entidad getIdent() {
        return ident;
    }

    public void setIdent(Entidad ident) {
        this.ident = ident;
    }

    @XmlTransient
    public List<Administracion> getAdministracionList() {
        return administracionList;
    }

    public void setAdministracionList(List<Administracion> administracionList) {
        this.administracionList = administracionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmun != null ? idmun.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipio)) {
            return false;
        }
        Municipio other = (Municipio) object;
        if ((this.idmun == null && other.idmun != null) || (this.idmun != null && !this.idmun.equals(other.idmun))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.ittepic.pmdapp.entidades.Municipio[ idmun=" + idmun + " ]";
    }
    
}
