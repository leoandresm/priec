/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leoandresm
 */
@Entity
@Table(name = "equipo_has_tipo_unidad_optica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquipoHasTipoUnidadOptica.findAll", query = "SELECT e FROM EquipoHasTipoUnidadOptica e"),
    @NamedQuery(name = "EquipoHasTipoUnidadOptica.findByEquipoIdEquipo", query = "SELECT e FROM EquipoHasTipoUnidadOptica e WHERE e.equipoHasTipoUnidadOpticaPK.equipoIdEquipo = :equipoIdEquipo"),
    @NamedQuery(name = "EquipoHasTipoUnidadOptica.findByTipoUnidadOpticaIdUnidadOptica", query = "SELECT e FROM EquipoHasTipoUnidadOptica e WHERE e.equipoHasTipoUnidadOpticaPK.tipoUnidadOpticaIdUnidadOptica = :tipoUnidadOpticaIdUnidadOptica"),
    @NamedQuery(name = "EquipoHasTipoUnidadOptica.findByCantidad", query = "SELECT e FROM EquipoHasTipoUnidadOptica e WHERE e.cantidad = :cantidad")})
public class EquipoHasTipoUnidadOptica implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EquipoHasTipoUnidadOpticaPK equipoHasTipoUnidadOpticaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @JoinColumn(name = "tipo_unidad_optica_id_unidad_optica", referencedColumnName = "id_unidad_optica", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoUnidadOptica tipoUnidadOptica;
    @JoinColumn(name = "equipo_id_equipo", referencedColumnName = "id_equipo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Equipo equipo;

    public EquipoHasTipoUnidadOptica() {
    }

    public EquipoHasTipoUnidadOptica(EquipoHasTipoUnidadOpticaPK equipoHasTipoUnidadOpticaPK) {
        this.equipoHasTipoUnidadOpticaPK = equipoHasTipoUnidadOpticaPK;
    }

    public EquipoHasTipoUnidadOptica(EquipoHasTipoUnidadOpticaPK equipoHasTipoUnidadOpticaPK, int cantidad) {
        this.equipoHasTipoUnidadOpticaPK = equipoHasTipoUnidadOpticaPK;
        this.cantidad = cantidad;
    }

    public EquipoHasTipoUnidadOptica(int equipoIdEquipo, int tipoUnidadOpticaIdUnidadOptica) {
        this.equipoHasTipoUnidadOpticaPK = new EquipoHasTipoUnidadOpticaPK(equipoIdEquipo, tipoUnidadOpticaIdUnidadOptica);
    }

    public EquipoHasTipoUnidadOpticaPK getEquipoHasTipoUnidadOpticaPK() {
        return equipoHasTipoUnidadOpticaPK;
    }

    public void setEquipoHasTipoUnidadOpticaPK(EquipoHasTipoUnidadOpticaPK equipoHasTipoUnidadOpticaPK) {
        this.equipoHasTipoUnidadOpticaPK = equipoHasTipoUnidadOpticaPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public TipoUnidadOptica getTipoUnidadOptica() {
        return tipoUnidadOptica;
    }

    public void setTipoUnidadOptica(TipoUnidadOptica tipoUnidadOptica) {
        this.tipoUnidadOptica = tipoUnidadOptica;
        equipoHasTipoUnidadOpticaPK.setTipoUnidadOpticaIdUnidadOptica(tipoUnidadOptica.getIdUnidadOptica());
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
        equipoHasTipoUnidadOpticaPK.setEquipoIdEquipo(equipo.getIdEquipo());
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equipoHasTipoUnidadOpticaPK != null ? equipoHasTipoUnidadOpticaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipoHasTipoUnidadOptica)) {
            return false;
        }
        EquipoHasTipoUnidadOptica other = (EquipoHasTipoUnidadOptica) object;
        if ((this.equipoHasTipoUnidadOpticaPK == null && other.equipoHasTipoUnidadOpticaPK != null) || (this.equipoHasTipoUnidadOpticaPK != null && !this.equipoHasTipoUnidadOpticaPK.equals(other.equipoHasTipoUnidadOpticaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.EquipoHasTipoUnidadOptica[ equipoHasTipoUnidadOpticaPK=" + equipoHasTipoUnidadOpticaPK + " ]";
    }
    
}
