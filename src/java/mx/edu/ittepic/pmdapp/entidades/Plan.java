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
@Table(name = "plan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plan.findAll", query = "SELECT p FROM Plan p")
    , @NamedQuery(name = "Plan.findByIdplan", query = "SELECT p FROM Plan p WHERE p.idplan = :idplan")
    , @NamedQuery(name = "Plan.findByClaveplan", query = "SELECT p FROM Plan p WHERE p.claveplan = :claveplan")
    , @NamedQuery(name = "Plan.findByNombreplan", query = "SELECT p FROM Plan p WHERE p.nombreplan = :nombreplan")})
public class Plan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idplan")
    private Integer idplan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "claveplan")
    private String claveplan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombreplan")
    private String nombreplan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idplan")
    private List<Categoriaplan> categoriaplanList;
    @JoinColumn(name = "idadmon", referencedColumnName = "idadmon")
    @ManyToOne(optional = false)
    private Administracion idadmon;

    public Plan() {
    }

    public Plan(Integer idplan) {
        this.idplan = idplan;
    }

    public Plan(Integer idplan, String claveplan, String nombreplan) {
        this.idplan = idplan;
        this.claveplan = claveplan;
        this.nombreplan = nombreplan;
    }

    public Integer getIdplan() {
        return idplan;
    }

    public void setIdplan(Integer idplan) {
        this.idplan = idplan;
    }

    public String getClaveplan() {
        return claveplan;
    }

    public void setClaveplan(String claveplan) {
        this.claveplan = claveplan;
    }

    public String getNombreplan() {
        return nombreplan;
    }

    public void setNombreplan(String nombreplan) {
        this.nombreplan = nombreplan;
    }

    @XmlTransient
    public List<Categoriaplan> getCategoriaplanList() {
        return categoriaplanList;
    }

    public void setCategoriaplanList(List<Categoriaplan> categoriaplanList) {
        this.categoriaplanList = categoriaplanList;
    }

    public Administracion getIdadmon() {
        return idadmon;
    }

    public void setIdadmon(Administracion idadmon) {
        this.idadmon = idadmon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplan != null ? idplan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plan)) {
            return false;
        }
        Plan other = (Plan) object;
        if ((this.idplan == null && other.idplan != null) || (this.idplan != null && !this.idplan.equals(other.idplan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.ittepic.pmdapp.entidades.Plan[ idplan=" + idplan + " ]";
    }
    
}
