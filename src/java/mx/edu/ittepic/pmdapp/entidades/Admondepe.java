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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Esteban
 */
@Entity
@Table(name = "admondepe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Admondepe.findAll", query = "SELECT a FROM Admondepe a")
    , @NamedQuery(name = "Admondepe.findByIdadmondepe", query = "SELECT a FROM Admondepe a WHERE a.idadmondepe = :idadmondepe")})
public class Admondepe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idadmondepe")
    private Integer idadmondepe;
    @JoinColumn(name = "idadmon", referencedColumnName = "idadmon")
    @ManyToOne(optional = false)
    private Administracion idadmon;
    @JoinColumn(name = "iddepe", referencedColumnName = "iddepe")
    @ManyToOne(optional = false)
    private Dependencia iddepe;

    public Admondepe() {
    }

    public Admondepe(Integer idadmondepe) {
        this.idadmondepe = idadmondepe;
    }

    public Integer getIdadmondepe() {
        return idadmondepe;
    }

    public void setIdadmondepe(Integer idadmondepe) {
        this.idadmondepe = idadmondepe;
    }

    public Administracion getIdadmon() {
        return idadmon;
    }

    public void setIdadmon(Administracion idadmon) {
        this.idadmon = idadmon;
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
        hash += (idadmondepe != null ? idadmondepe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admondepe)) {
            return false;
        }
        Admondepe other = (Admondepe) object;
        if ((this.idadmondepe == null && other.idadmondepe != null) || (this.idadmondepe != null && !this.idadmondepe.equals(other.idadmondepe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.ittepic.pmdapp.entidades.Admondepe[ idadmondepe=" + idadmondepe + " ]";
    }
    
}
