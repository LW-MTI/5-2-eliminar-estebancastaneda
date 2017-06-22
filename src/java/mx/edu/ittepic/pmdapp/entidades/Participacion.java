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
@Table(name = "participacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Participacion.findAll", query = "SELECT p FROM Participacion p")
    , @NamedQuery(name = "Participacion.findByIdparticipacion", query = "SELECT p FROM Participacion p WHERE p.idparticipacion = :idparticipacion")})
public class Participacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idparticipacion")
    private Integer idparticipacion;
    @JoinColumn(name = "idcat", referencedColumnName = "idcat")
    @ManyToOne(optional = false)
    private Categoriaplan idcat;
    @JoinColumn(name = "iddepe", referencedColumnName = "iddepe")
    @ManyToOne(optional = false)
    private Dependencia iddepe;

    public Participacion() {
    }

    public Participacion(Integer idparticipacion) {
        this.idparticipacion = idparticipacion;
    }

    public Integer getIdparticipacion() {
        return idparticipacion;
    }

    public void setIdparticipacion(Integer idparticipacion) {
        this.idparticipacion = idparticipacion;
    }

    public Categoriaplan getIdcat() {
        return idcat;
    }

    public void setIdcat(Categoriaplan idcat) {
        this.idcat = idcat;
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
        hash += (idparticipacion != null ? idparticipacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participacion)) {
            return false;
        }
        Participacion other = (Participacion) object;
        if ((this.idparticipacion == null && other.idparticipacion != null) || (this.idparticipacion != null && !this.idparticipacion.equals(other.idparticipacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.ittepic.pmdapp.entidades.Participacion[ idparticipacion=" + idparticipacion + " ]";
    }
    
}
