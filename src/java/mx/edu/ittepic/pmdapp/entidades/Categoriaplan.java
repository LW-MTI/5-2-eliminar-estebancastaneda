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
@Table(name = "categoriaplan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoriaplan.findAll", query = "SELECT c FROM Categoriaplan c")
    , @NamedQuery(name = "Categoriaplan.findByIdcat", query = "SELECT c FROM Categoriaplan c WHERE c.idcat = :idcat")
    , @NamedQuery(name = "Categoriaplan.findByClavecat", query = "SELECT c FROM Categoriaplan c WHERE c.clavecat = :clavecat")
    , @NamedQuery(name = "Categoriaplan.findByNombrecat", query = "SELECT c FROM Categoriaplan c WHERE c.nombrecat = :nombrecat")
    , @NamedQuery(name = "Categoriaplan.findByNivelcat", query = "SELECT c FROM Categoriaplan c WHERE c.nivelcat = :nivelcat")
    , @NamedQuery(name = "Categoriaplan.findByRutacat", query = "SELECT c FROM Categoriaplan c WHERE c.rutacat = :rutacat")
    , @NamedQuery(name = "Categoriaplan.findByOrdencat", query = "SELECT c FROM Categoriaplan c WHERE c.ordencat = :ordencat")
    , @NamedQuery(name = "Categoriaplan.findByIdcatpadre", query = "SELECT c FROM Categoriaplan c WHERE c.idcatpadre = :idcatpadre")
    , @NamedQuery(name = "Categoriaplan.findByDescripcat", query = "SELECT c FROM Categoriaplan c WHERE c.descripcat = :descripcat")})
public class Categoriaplan implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcat")
    private List<Actividad> actividadList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcat")
    private Integer idcat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "clavecat")
    private String clavecat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombrecat")
    private String nombrecat;
    @Column(name = "nivelcat")
    private Integer nivelcat;
    @Size(max = 100)
    @Column(name = "rutacat")
    private String rutacat;
    @Column(name = "ordencat")
    private Integer ordencat;
    @Column(name = "idcatpadre")
    private Integer idcatpadre;
    @Basic(optional = false)
    @NotNull
    @Size(max = 500)
    @Column(name = "descripcat")
    private String descripcat;
    @JoinColumn(name = "idplan", referencedColumnName = "idplan")
    @ManyToOne(optional = false)
    private Plan idplan;

    public Categoriaplan() {
    }

    public Categoriaplan(Integer idcat) {
        this.idcat = idcat;
    }

    public Categoriaplan(Integer idcat, String clavecat, String nombrecat) {
        this.idcat = idcat;
        this.clavecat = clavecat;
        this.nombrecat = nombrecat;
    }

    public Integer getIdcat() {
        return idcat;
    }

    public void setIdcat(Integer idcat) {
        this.idcat = idcat;
    }

    public String getClavecat() {
        return clavecat;
    }

    public void setClavecat(String clavecat) {
        this.clavecat = clavecat;
    }

    public String getNombrecat() {
        return nombrecat;
    }

    public void setNombrecat(String nombrecat) {
        this.nombrecat = nombrecat;
    }

    public Integer getNivelcat() {
        return nivelcat;
    }

    public void setNivelcat(Integer nivelcat) {
        this.nivelcat = nivelcat;
    }

    public String getRutacat() {
        return rutacat;
    }

    public void setRutacat(String rutacat) {
        this.rutacat = rutacat;
    }

    public Integer getOrdencat() {
        return ordencat;
    }

    public void setOrdencat(Integer ordencat) {
        this.ordencat = ordencat;
    }

    public Integer getIdcatpadre() {
        return idcatpadre;
    }

    public void setIdcatpadre(Integer idcatpadre) {
        this.idcatpadre = idcatpadre;
    }

    public String getDescripcat() {
        return descripcat;
    }

    public void setDescripcat(String descripcat) {
        this.descripcat = descripcat;
    }

    public Plan getIdplan() {
        return idplan;
    }

    public void setIdplan(Plan idplan) {
        this.idplan = idplan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcat != null ? idcat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoriaplan)) {
            return false;
        }
        Categoriaplan other = (Categoriaplan) object;
        if ((this.idcat == null && other.idcat != null) || (this.idcat != null && !this.idcat.equals(other.idcat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.ittepic.pmdapp.entidades.Categoriaplan[ idcat=" + idcat + " ]";
    }

    @XmlTransient
    public List<Actividad> getActividadList() {
        return actividadList;
    }

    public void setActividadList(List<Actividad> actividadList) {
        this.actividadList = actividadList;
    }
    
}
