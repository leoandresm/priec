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
@Table(name = "equipo_has_tipo_memoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquipoHasTipoMemoria.findAll", query = "SELECT e FROM EquipoHasTipoMemoria e"),
    @NamedQuery(name = "EquipoHasTipoMemoria.findByEquipoIdEquipo", query = "SELECT e FROM EquipoHasTipoMemoria e WHERE e.equipoHasTipoMemoriaPK.equipoIdEquipo = :equipoIdEquipo"),
    @NamedQuery(name = "EquipoHasTipoMemoria.findByTipoMemoriaIdMemoria", query = "SELECT e FROM EquipoHasTipoMemoria e WHERE e.equipoHasTipoMemoriaPK.tipoMemoriaIdMemoria = :tipoMemoriaIdMemoria"),
    @NamedQuery(name = "EquipoHasTipoMemoria.findByCantidad", query = "SELECT e FROM EquipoHasTipoMemoria e WHERE e.cantidad = :cantidad")})
public class EquipoHasTipoMemoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EquipoHasTipoMemoriaPK equipoHasTipoMemoriaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @JoinColumn(name = "tipo_memoria_id_memoria", referencedColumnName = "id_memoria", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoMemoria tipoMemoria;
    @JoinColumn(name = "equipo_id_equipo", referencedColumnName = "id_equipo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Equipo equipo;

    public EquipoHasTipoMemoria() {
    }

    public EquipoHasTipoMemoria(EquipoHasTipoMemoriaPK equipoHasTipoMemoriaPK) {
        this.equipoHasTipoMemoriaPK = equipoHasTipoMemoriaPK;
    }

    public EquipoHasTipoMemoria(EquipoHasTipoMemoriaPK equipoHasTipoMemoriaPK, int cantidad) {
        this.equipoHasTipoMemoriaPK = equipoHasTipoMemoriaPK;
        this.cantidad = cantidad;
    }

    public EquipoHasTipoMemoria(int equipoIdEquipo, int tipoMemoriaIdMemoria) {
        this.equipoHasTipoMemoriaPK = new EquipoHasTipoMemoriaPK(equipoIdEquipo, tipoMemoriaIdMemoria);
    }

    public EquipoHasTipoMemoriaPK getEquipoHasTipoMemoriaPK() {
        return equipoHasTipoMemoriaPK;
    }

    public void setEquipoHasTipoMemoriaPK(EquipoHasTipoMemoriaPK equipoHasTipoMemoriaPK) {
        this.equipoHasTipoMemoriaPK = equipoHasTipoMemoriaPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public TipoMemoria getTipoMemoria() {
        return tipoMemoria;
    }

    public void setTipoMemoria(TipoMemoria tipoMemoria) {
        this.tipoMemoria = tipoMemoria;
        equipoHasTipoMemoriaPK.setTipoMemoriaIdMemoria(tipoMemoria.getIdMemoria());
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
        equipoHasTipoMemoriaPK.setEquipoIdEquipo(equipo.getIdEquipo());
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equipoHasTipoMemoriaPK != null ? equipoHasTipoMemoriaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipoHasTipoMemoria)) {
            return false;
        }
        EquipoHasTipoMemoria other = (EquipoHasTipoMemoria) object;
        if ((this.equipoHasTipoMemoriaPK == null && other.equipoHasTipoMemoriaPK != null) || (this.equipoHasTipoMemoriaPK != null && !this.equipoHasTipoMemoriaPK.equals(other.equipoHasTipoMemoriaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.EquipoHasTipoMemoria[ equipoHasTipoMemoriaPK=" + equipoHasTipoMemoriaPK + " ]";
    }
    
}
