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
@Table(name = "marca_equipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MarcaEquipo.findAll", query = "SELECT m FROM MarcaEquipo m"),
    @NamedQuery(name = "MarcaEquipo.findByIdMarcaEquipo", query = "SELECT m FROM MarcaEquipo m WHERE m.idMarcaEquipo = :idMarcaEquipo"),
    @NamedQuery(name = "MarcaEquipo.findByDescripcion", query = "SELECT m FROM MarcaEquipo m WHERE m.descripcion = :descripcion")})
public class MarcaEquipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_marca_equipo")
    private Integer idMarcaEquipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMarcaEquipo")
    private List<Equipo> equipoList;

    public MarcaEquipo() {
    }

    public MarcaEquipo(Integer idMarcaEquipo) {
        this.idMarcaEquipo = idMarcaEquipo;
    }

    public MarcaEquipo(Integer idMarcaEquipo, String descripcion) {
        this.idMarcaEquipo = idMarcaEquipo;
        this.descripcion = descripcion;
    }

    public Integer getIdMarcaEquipo() {
        return idMarcaEquipo;
    }

    public void setIdMarcaEquipo(Integer idMarcaEquipo) {
        this.idMarcaEquipo = idMarcaEquipo;
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
        hash += (idMarcaEquipo != null ? idMarcaEquipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MarcaEquipo)) {
            return false;
        }
        MarcaEquipo other = (MarcaEquipo) object;
        if ((this.idMarcaEquipo == null && other.idMarcaEquipo != null) || (this.idMarcaEquipo != null && !this.idMarcaEquipo.equals(other.idMarcaEquipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion ;
    }
    
}
