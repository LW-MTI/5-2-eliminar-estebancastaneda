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
@Table(name = "objetivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Objetivo.findAll", query = "SELECT o FROM Objetivo o")
    , @NamedQuery(name = "Objetivo.findByIdobjetivo", query = "SELECT o FROM Objetivo o WHERE o.idobjetivo = :idobjetivo")
    , @NamedQuery(name = "Objetivo.findByClaveobjetivo", query = "SELECT o FROM Objetivo o WHERE o.claveobjetivo = :claveobjetivo")
    , @NamedQuery(name = "Objetivo.findByObjetivo", query = "SELECT o FROM Objetivo o WHERE o.objetivo = :objetivo")})
public class Objetivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idobjetivo")
    private Integer idobjetivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "claveobjetivo")
    private String claveobjetivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "objetivo")
    private String objetivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idobjetivo")
    private List<Estrategia> estrategiaList;
    @JoinColumn(name = "idcat", referencedColumnName = "idcat")
    @ManyToOne(optional = false)
    private Categoriaplan idcat;

    public Objetivo() {
    }

    public Objetivo(Integer idobjetivo) {
        this.idobjetivo = idobjetivo;
    }

    public Objetivo(Integer idobjetivo, String claveobjetivo, String objetivo) {
        this.idobjetivo = idobjetivo;
        this.claveobjetivo = claveobjetivo;
        this.objetivo = objetivo;
    }

    public Integer getIdobjetivo() {
        return idobjetivo;
    }

    public void setIdobjetivo(Integer idobjetivo) {
        this.idobjetivo = idobjetivo;
    }

    public String getClaveobjetivo() {
        return claveobjetivo;
    }

    public void setClaveobjetivo(String claveobjetivo) {
        this.claveobjetivo = claveobjetivo;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    @XmlTransient
    public List<Estrategia> getEstrategiaList() {
        return estrategiaList;
    }

    public void setEstrategiaList(List<Estrategia> estrategiaList) {
        this.estrategiaList = estrategiaList;
    }

    public Categoriaplan getIdcat() {
        return idcat;
    }

    public void setIdcat(Categoriaplan idcat) {
        this.idcat = idcat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idobjetivo != null ? idobjetivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Objetivo)) {
            return false;
        }
        Objetivo other = (Objetivo) object;
        if ((this.idobjetivo == null && other.idobjetivo != null) || (this.idobjetivo != null && !this.idobjetivo.equals(other.idobjetivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.ittepic.pmdapp.entidades.Objetivo[ idobjetivo=" + idobjetivo + " ]";
    }
    
}
