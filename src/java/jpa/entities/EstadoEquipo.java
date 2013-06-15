/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

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
 * @author leoandresm
 */
@Entity
@Table(name = "estado_equipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoEquipo.findAll", query = "SELECT e FROM EstadoEquipo e"),
    @NamedQuery(name = "EstadoEquipo.findByIdEstadoEquipo", query = "SELECT e FROM EstadoEquipo e WHERE e.idEstadoEquipo = :idEstadoEquipo"),
    @NamedQuery(name = "EstadoEquipo.findByDescripcion", query = "SELECT e FROM EstadoEquipo e WHERE e.descripcion = :descripcion")})
public class EstadoEquipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_equipo")
    private Integer idEstadoEquipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoEquipo")
    private List<Equipo> equipoList;

    public EstadoEquipo() {
    }

    public EstadoEquipo(Integer idEstadoEquipo) {
        this.idEstadoEquipo = idEstadoEquipo;
    }

    public EstadoEquipo(Integer idEstadoEquipo, String descripcion) {
        this.idEstadoEquipo = idEstadoEquipo;
        this.descripcion = descripcion;
    }

    public Integer getIdEstadoEquipo() {
        return idEstadoEquipo;
    }

    public void setIdEstadoEquipo(Integer idEstadoEquipo) {
        this.idEstadoEquipo = idEstadoEquipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoEquipo != null ? idEstadoEquipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoEquipo)) {
            return false;
        }
        EstadoEquipo other = (EstadoEquipo) object;
        if ((this.idEstadoEquipo == null && other.idEstadoEquipo != null) || (this.idEstadoEquipo != null && !this.idEstadoEquipo.equals(other.idEstadoEquipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}
