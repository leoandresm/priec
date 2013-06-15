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
@Table(name = "equipo_has_tipo_disco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquipoHasTipoDisco.findAll", query = "SELECT e FROM EquipoHasTipoDisco e"),
    @NamedQuery(name = "EquipoHasTipoDisco.findByEquipoIdEquipo", query = "SELECT e FROM EquipoHasTipoDisco e WHERE e.equipoHasTipoDiscoPK.equipoIdEquipo = :equipoIdEquipo"),
    @NamedQuery(name = "EquipoHasTipoDisco.findByTipoDiscoIdDisco", query = "SELECT e FROM EquipoHasTipoDisco e WHERE e.equipoHasTipoDiscoPK.tipoDiscoIdDisco = :tipoDiscoIdDisco"),
    @NamedQuery(name = "EquipoHasTipoDisco.findByCantidad", query = "SELECT e FROM EquipoHasTipoDisco e WHERE e.cantidad = :cantidad")})
public class EquipoHasTipoDisco implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EquipoHasTipoDiscoPK equipoHasTipoDiscoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    
    @JoinColumn(name = "tipo_disco_id_disco", referencedColumnName = "id_disco", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoDisco tipoDisco;
    @JoinColumn(name = "equipo_id_equipo", referencedColumnName = "id_equipo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Equipo equipo;

    public EquipoHasTipoDisco() {
    }

    public EquipoHasTipoDisco(EquipoHasTipoDiscoPK equipoHasTipoDiscoPK) {
        this.equipoHasTipoDiscoPK = equipoHasTipoDiscoPK;
    }

    public EquipoHasTipoDisco(EquipoHasTipoDiscoPK equipoHasTipoDiscoPK, int cantidad) {
        this.equipoHasTipoDiscoPK = equipoHasTipoDiscoPK;
        this.cantidad = cantidad;
    }

    public EquipoHasTipoDisco(int equipoIdEquipo, int tipoDiscoIdDisco) {
        this.equipoHasTipoDiscoPK = new EquipoHasTipoDiscoPK(equipoIdEquipo, tipoDiscoIdDisco);
    }

    public EquipoHasTipoDiscoPK getEquipoHasTipoDiscoPK() {
        return equipoHasTipoDiscoPK;
    }

    public void setEquipoHasTipoDiscoPK(EquipoHasTipoDiscoPK equipoHasTipoDiscoPK) {
        this.equipoHasTipoDiscoPK = equipoHasTipoDiscoPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;        
    }

    public TipoDisco getTipoDisco() {
        return tipoDisco;
    }

    public void setTipoDisco(TipoDisco tipoDisco) {
        this.tipoDisco = tipoDisco;
        equipoHasTipoDiscoPK.setTipoDiscoIdDisco(tipoDisco.getIdDisco());
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
        equipoHasTipoDiscoPK.setEquipoIdEquipo(equipo.getIdEquipo());
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equipoHasTipoDiscoPK != null ? equipoHasTipoDiscoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipoHasTipoDisco)) {
            return false;
        }
        EquipoHasTipoDisco other = (EquipoHasTipoDisco) object;
        if ((this.equipoHasTipoDiscoPK == null && other.equipoHasTipoDiscoPK != null) || (this.equipoHasTipoDiscoPK != null && !this.equipoHasTipoDiscoPK.equals(other.equipoHasTipoDiscoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.EquipoHasTipoDisco[ equipoHasTipoDiscoPK=" + equipoHasTipoDiscoPK + " ]";
    }
    
}
