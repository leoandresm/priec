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
public class EquipoHasTipoDiscoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "equipo_id_equipo")
    private int equipoIdEquipo = 0;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_disco_id_disco")
    private int tipoDiscoIdDisco;

    public EquipoHasTipoDiscoPK() {
    }

    public EquipoHasTipoDiscoPK(int equipoIdEquipo, int tipoDiscoIdDisco) {
        this.equipoIdEquipo = equipoIdEquipo;
        this.tipoDiscoIdDisco = tipoDiscoIdDisco;
    }

    public int getEquipoIdEquipo() {
        return equipoIdEquipo;
    }

    public void setEquipoIdEquipo(int equipoIdEquipo) {
        this.equipoIdEquipo = equipoIdEquipo;
    }

    public int getTipoDiscoIdDisco() {
        return tipoDiscoIdDisco;
    }

    public void setTipoDiscoIdDisco(int tipoDiscoIdDisco) {
        this.tipoDiscoIdDisco = tipoDiscoIdDisco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) equipoIdEquipo;
        hash += (int) tipoDiscoIdDisco;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipoHasTipoDiscoPK)) {
            return false;
        }
        EquipoHasTipoDiscoPK other = (EquipoHasTipoDiscoPK) object;
        if (this.equipoIdEquipo != other.equipoIdEquipo) {
            return false;
        }
        if (this.tipoDiscoIdDisco != other.tipoDiscoIdDisco) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.EquipoHasTipoDiscoPK[ equipoIdEquipo=" + equipoIdEquipo + ", tipoDiscoIdDisco=" + tipoDiscoIdDisco + " ]";
    }
    
}
