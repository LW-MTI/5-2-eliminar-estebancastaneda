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
@Table(name = "entidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entidad.findAll", query = "SELECT e FROM Entidad e order by e.nombreent")
    , @NamedQuery(name = "Entidad.findByIdent", query = "SELECT e FROM Entidad e WHERE e.ident = :ident")
    , @NamedQuery(name = "Entidad.findByClaveent", query = "SELECT e FROM Entidad e WHERE e.claveent = :claveent")
    , @NamedQuery(name = "Entidad.findByNombreent", query = "SELECT e FROM Entidad e WHERE e.nombreent = :nombreent")
    , @NamedQuery(name = "Entidad.findByAbrevent", query = "SELECT e FROM Entidad e WHERE e.abrevent = :abrevent")})
public class Entidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ident")
    private Integer ident;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "claveent")
    private String claveent;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombreent")
    private String nombreent;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "abrevent")
    private String abrevent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ident")
    private List<Municipio> municipioList;

    public Entidad() {
    }

    public Entidad(Integer ident) {
        this.ident = ident;
    }

    public Entidad(Integer ident, String claveent, String nombreent, String abrevent) {
        this.ident = ident;
        this.claveent = claveent;
        this.nombreent = nombreent;
        this.abrevent = abrevent;
    }

    public Integer getIdent() {
        return ident;
    }

    public void setIdent(Integer ident) {
        this.ident = ident;
    }

    public String getClaveent() {
        return claveent;
    }

    public void setClaveent(String claveent) {
        this.claveent = claveent;
    }

    public String getNombreent() {
        return nombreent;
    }

    public void setNombreent(String nombreent) {
        this.nombreent = nombreent;
    }

    public String getAbrevent() {
        return abrevent;
    }

    public void setAbrevent(String abrevent) {
        this.abrevent = abrevent;
    }

    @XmlTransient
    public List<Municipio> getMunicipioList() {
        return municipioList;
    }

    public void setMunicipioList(List<Municipio> municipioList) {
        this.municipioList = municipioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ident != null ? ident.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entidad)) {
            return false;
        }
        Entidad other = (Entidad) object;
        if ((this.ident == null && other.ident != null) || (this.ident != null && !this.ident.equals(other.ident))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.ittepic.pmdapp.entidades.Entidad[ ident=" + ident + " ]";
    }
    
}
