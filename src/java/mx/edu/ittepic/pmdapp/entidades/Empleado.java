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
@Table(name = "empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findByIdemp", query = "SELECT e FROM Empleado e WHERE e.idemp = :idemp")
    , @NamedQuery(name = "Empleado.findByIddepto", query = "SELECT e FROM Empleado e WHERE e.iddepto = :iddepto order by e.paternoemp")
    , @NamedQuery(name = "Empleado.findByPaternoemp", query = "SELECT e FROM Empleado e WHERE e.paternoemp = :paternoemp")
    , @NamedQuery(name = "Empleado.findByMaternoemp", query = "SELECT e FROM Empleado e WHERE e.maternoemp = :maternoemp")
    , @NamedQuery(name = "Empleado.findByNombreemp", query = "SELECT e FROM Empleado e WHERE e.nombreemp = :nombreemp")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idemp")
    private Integer idemp;
    @Size(max = 50)
    @Column(name = "paternoemp")
    private String paternoemp;
    @Size(max = 50)
    @Column(name = "maternoemp")
    private String maternoemp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombreemp")
    private String nombreemp;
    @JoinColumn(name = "iddepto", referencedColumnName = "iddepto")
    @ManyToOne(optional = false)
    private Departamento iddepto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idemp")
    private List<Usuario> usuarioList;

    public Empleado() {
    }

    public Empleado(Integer idemp) {
        this.idemp = idemp;
    }

    public Empleado(Integer idemp, String nombreemp) {
        this.idemp = idemp;
        this.nombreemp = nombreemp;
    }

    public Integer getIdemp() {
        return idemp;
    }

    public void setIdemp(Integer idemp) {
        this.idemp = idemp;
    }

    public String getPaternoemp() {
        return paternoemp;
    }

    public void setPaternoemp(String paternoemp) {
        this.paternoemp = paternoemp;
    }

    public String getMaternoemp() {
        return maternoemp;
    }

    public void setMaternoemp(String maternoemp) {
        this.maternoemp = maternoemp;
    }

    public String getNombreemp() {
        return nombreemp;
    }

    public void setNombreemp(String nombreemp) {
        this.nombreemp = nombreemp;
    }

    public Departamento getIddepto() {
        return iddepto;
    }

    public void setIddepto(Departamento iddepto) {
        this.iddepto = iddepto;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idemp != null ? idemp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.idemp == null && other.idemp != null) || (this.idemp != null && !this.idemp.equals(other.idemp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.ittepic.pmdapp.entidades.Empleado[ idemp=" + idemp + " ]";
    }
    
}
