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
@Table(name = "estado_incidente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoIncidente.findAll", query = "SELECT e FROM EstadoIncidente e"),
    @NamedQuery(name = "EstadoIncidente.findByIdEstadoIncidente", query = "SELECT e FROM EstadoIncidente e WHERE e.idEstadoIncidente = :idEstadoIncidente"),
    @NamedQuery(name = "EstadoIncidente.findByDescripcion", query = "SELECT e FROM EstadoIncidente e WHERE e.descripcion = :descripcion")})
public class EstadoIncidente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_incidente")
    private Integer idEstadoIncidente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoIncidente")
    private List<Incidente> incidenteList;

    public EstadoIncidente() {
    }

    public EstadoIncidente(Integer idEstadoIncidente) {
        this.idEstadoIncidente = idEstadoIncidente;
    }

    public EstadoIncidente(Integer idEstadoIncidente, String descripcion) {
        this.idEstadoIncidente = idEstadoIncidente;
        this.descripcion = descripcion;
    }

    public Integer getIdEstadoIncidente() {
        return idEstadoIncidente;
    }

    public void setIdEstadoIncidente(Integer idEstadoIncidente) {
        this.idEstadoIncidente = idEstadoIncidente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Incidente> getIncidenteList() {
        return incidenteList;
    }

    public void setIncidenteList(List<Incidente> incidenteList) {
        this.incidenteList = incidenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoIncidente != null ? idEstadoIncidente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoIncidente)) {
            return false;
        }
        EstadoIncidente other = (EstadoIncidente) object;
        if ((this.idEstadoIncidente == null && other.idEstadoIncidente != null) || (this.idEstadoIncidente != null && !this.idEstadoIncidente.equals(other.idEstadoIncidente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}
