/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author leoandresm
 */
@Embeddable
public class EquipoHasTipoUnidadOpticaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "equipo_id_equipo")
    private int equipoIdEquipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_unidad_optica_id_unidad_optica")
    private int tipoUnidadOpticaIdUnidadOptica;

    public EquipoHasTipoUnidadOpticaPK() {
    }

    public EquipoHasTipoUnidadOpticaPK(int equipoIdEquipo, int tipoUnidadOpticaIdUnidadOptica) {
        this.equipoIdEquipo = equipoIdEquipo;
        this.tipoUnidadOpticaIdUnidadOptica = tipoUnidadOpticaIdUnidadOptica;
    }

    public int getEquipoIdEquipo() {
        return equipoIdEquipo;
    }

    public void setEquipoIdEquipo(int equipoIdEquipo) {
        this.equipoIdEquipo = equipoIdEquipo;
    }

    public int getTipoUnidadOpticaIdUnidadOptica() {
        return tipoUnidadOpticaIdUnidadOptica;
    }

    public void setTipoUnidadOpticaIdUnidadOptica(int tipoUnidadOpticaIdUnidadOptica) {
        this.tipoUnidadOpticaIdUnidadOptica = tipoUnidadOpticaIdUnidadOptica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) equipoIdEquipo;
        hash += (int) tipoUnidadOpticaIdUnidadOptica;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipoHasTipoUnidadOpticaPK)) {
            return false;
        }
        EquipoHasTipoUnidadOpticaPK other = (EquipoHasTipoUnidadOpticaPK) object;
        if (this.equipoIdEquipo != other.equipoIdEquipo) {
            return false;
        }
        if (this.tipoUnidadOpticaIdUnidadOptica != other.tipoUnidadOpticaIdUnidadOptica) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.EquipoHasTipoUnidadOpticaPK[ equipoIdEquipo=" + equipoIdEquipo + ", tipoUnidadOpticaIdUnidadOptica=" + tipoUnidadOpticaIdUnidadOptica + " ]";
    }
    
}
