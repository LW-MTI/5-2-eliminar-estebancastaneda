/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.pmdapp.entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Esteban
 */
@Entity
@Table(name = "actividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividad.findAll", query = "SELECT a FROM Actividad a")
    , @NamedQuery(name = "Actividad.findByIdactividad", query = "SELECT a FROM Actividad a WHERE a.idactividad = :idactividad")
    , @NamedQuery(name = "Actividad.findByActividad", query = "SELECT a FROM Actividad a WHERE a.actividad = :actividad")
    , @NamedQuery(name = "Actividad.findByFechaini", query = "SELECT a FROM Actividad a WHERE a.fechaini = :fechaini")
    , @NamedQuery(name = "Actividad.findByFechafin", query = "SELECT a FROM Actividad a WHERE a.fechafin = :fechafin")
    , @NamedQuery(name = "Actividad.findByLatitud", query = "SELECT a FROM Actividad a WHERE a.latitud = :latitud")
    , @NamedQuery(name = "Actividad.findByLongitud", query = "SELECT a FROM Actividad a WHERE a.longitud = :longitud")})
public class Actividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idactividad")
    private Integer idactividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "actividad")
    private String actividad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaini")
    @Temporal(TemporalType.DATE)
    private Date fechaini;
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitud")
    private Float latitud;
    @Column(name = "longitud")
    private Float longitud;
    @JoinColumn(name = "idcat", referencedColumnName = "idcat")
    @ManyToOne(optional = false)
    private Categoriaplan idcat;
    @JoinColumn(name = "iddepe", referencedColumnName = "iddepe")
    @ManyToOne(optional = false)
    private Dependencia iddepe;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public Actividad() {
    }

    public Actividad(Integer idactividad) {
        this.idactividad = idactividad;
    }

    public Actividad(Integer idactividad, String actividad, Date fechaini) {
        this.idactividad = idactividad;
        this.actividad = actividad;
        this.fechaini = fechaini;
    }

    public Integer getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(Integer idactividad) {
        this.idactividad = idactividad;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public Date getFechaini() {
        return fechaini;
    }

    public void setFechaini(Date fechaini) {
        this.fechaini = fechaini;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Float getLatitud() {
        return latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
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

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactividad != null ? idactividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividad)) {
            return false;
        }
        Actividad other = (Actividad) object;
        if ((this.idactividad == null && other.idactividad != null) || (this.idactividad != null && !this.idactividad.equals(other.idactividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.ittepic.pmdapp.entidades.Actividad[ idactividad=" + idactividad + " ]";
    }
    
}
