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
@Table(name = "rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")
    , @NamedQuery(name = "Rol.findByIdrol", query = "SELECT r FROM Rol r WHERE r.idrol = :idrol")
    , @NamedQuery(name = "Rol.findByClaverol", query = "SELECT r FROM Rol r WHERE r.claverol = :claverol")
    , @NamedQuery(name = "Rol.findByNombrerol", query = "SELECT r FROM Rol r WHERE r.nombrerol = :nombrerol")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrol")
    private Integer idrol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "claverol")
    private String claverol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombrerol")
    private String nombrerol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idrol")
    private List<Usuariorol> usuariorolList;

    public Rol() {
    }

    public Rol(Integer idrol) {
        this.idrol = idrol;
    }

    public Rol(Integer idrol, String claverol, String nombrerol) {
        this.idrol = idrol;
        this.claverol = claverol;
        this.nombrerol = nombrerol;
    }

    public Integer getIdrol() {
        return idrol;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }

    public String getClaverol() {
        return claverol;
    }

    public void setClaverol(String claverol) {
        this.claverol = claverol;
    }

    public String getNombrerol() {
        return nombrerol;
    }

    public void setNombrerol(String nombrerol) {
        this.nombrerol = nombrerol;
    }

    @XmlTransient
    public List<Usuariorol> getUsuariorolList() {
        return usuariorolList;
    }

    public void setUsuariorolList(List<Usuariorol> usuariorolList) {
        this.usuariorolList = usuariorolList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrol != null ? idrol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.idrol == null && other.idrol != null) || (this.idrol != null && !this.idrol.equals(other.idrol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.ittepic.pmdapp.entidades.Rol[ idrol=" + idrol + " ]";
    }
    
}
