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
@Table(name = "tipo_falla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoFalla.findAll", query = "SELECT t FROM TipoFalla t"),
    @NamedQuery(name = "TipoFalla.findByIdTipoFalla", query = "SELECT t FROM TipoFalla t WHERE t.idTipoFalla = :idTipoFalla"),
    @NamedQuery(name = "TipoFalla.findByDescripcion", query = "SELECT t FROM TipoFalla t WHERE t.descripcion = :descripcion")})
public class TipoFalla implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_falla")
    private Integer idTipoFalla;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoFalla")
    private List<Incidente> incidenteList;

    public TipoFalla() {
    }

    public TipoFalla(Integer idTipoFalla) {
        this.idTipoFalla = idTipoFalla;
    }

    public TipoFalla(Integer idTipoFalla, String descripcion) {
        this.idTipoFalla = idTipoFalla;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoFalla() {
        return idTipoFalla;
    }

    public void setIdTipoFalla(Integer idTipoFalla) {
        this.idTipoFalla = idTipoFalla;
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
        hash += (idTipoFalla != null ? idTipoFalla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoFalla)) {
            return false;
        }
        TipoFalla other = (TipoFalla) object;
        if ((this.idTipoFalla == null && other.idTipoFalla != null) || (this.idTipoFalla != null && !this.idTipoFalla.equals(other.idTipoFalla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}
