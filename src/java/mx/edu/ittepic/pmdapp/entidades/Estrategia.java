/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.pmdapp.entidades;

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
 * @author Esteban
 */
@Entity
@Table(name = "estrategia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estrategia.findAll", query = "SELECT e FROM Estrategia e")
    , @NamedQuery(name = "Estrategia.findByIdestrategia", query = "SELECT e FROM Estrategia e WHERE e.idestrategia = :idestrategia")
    , @NamedQuery(name = "Estrategia.findByClaveestrategia", query = "SELECT e FROM Estrategia e WHERE e.claveestrategia = :claveestrategia")
    , @NamedQuery(name = "Estrategia.findByEstrategia", query = "SELECT e FROM Estrategia e WHERE e.estrategia = :estrategia")})
public class Estrategia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestrategia")
    private Integer idestrategia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "claveestrategia")
    private String claveestrategia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "estrategia")
    private String estrategia;
    @JoinColumn(name = "idobjetivo", referencedColumnName = "idobjetivo")
    @ManyToOne(optional = false)
    private Objetivo idobjetivo;

    public Estrategia() {
    }

    public Estrategia(Integer idestrategia) {
        this.idestrategia = idestrategia;
    }

    public Estrategia(Integer idestrategia, String claveestrategia, String estrategia) {
        this.idestrategia = idestrategia;
        this.claveestrategia = claveestrategia;
        this.estrategia = estrategia;
    }

    public Integer getIdestrategia() {
        return idestrategia;
    }

    public void setIdestrategia(Integer idestrategia) {
        this.idestrategia = idestrategia;
    }

    public String getClaveestrategia() {
        return claveestrategia;
    }

    public void setClaveestrategia(String claveestrategia) {
        this.claveestrategia = claveestrategia;
    }

    public String getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(String estrategia) {
        this.estrategia = estrategia;
    }

    public Objetivo getIdobjetivo() {
        return idobjetivo;
    }

    public void setIdobjetivo(Objetivo idobjetivo) {
        this.idobjetivo = idobjetivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestrategia != null ? idestrategia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estrategia)) {
            return false;
        }
        Estrategia other = (Estrategia) object;
        if ((this.idestrategia == null && other.idestrategia != null) || (this.idestrategia != null && !this.idestrategia.equals(other.idestrategia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.ittepic.pmdapp.entidades.Estrategia[ idestrategia=" + idestrategia + " ]";
    }
    
}
