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
public class EquipoHasTipoMemoriaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "equipo_id_equipo")
    private int equipoIdEquipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_memoria_id_memoria")
    private int tipoMemoriaIdMemoria;

    public EquipoHasTipoMemoriaPK() {
    }

    public EquipoHasTipoMemoriaPK(int equipoIdEquipo, int tipoMemoriaIdMemoria) {
        this.equipoIdEquipo = equipoIdEquipo;
        this.tipoMemoriaIdMemoria = tipoMemoriaIdMemoria;
    }

    public int getEquipoIdEquipo() {
        return equipoIdEquipo;
    }

    public void setEquipoIdEquipo(int equipoIdEquipo) {
        this.equipoIdEquipo = equipoIdEquipo;
    }

    public int getTipoMemoriaIdMemoria() {
        return tipoMemoriaIdMemoria;
    }

    public void setTipoMemoriaIdMemoria(int tipoMemoriaIdMemoria) {
        this.tipoMemoriaIdMemoria = tipoMemoriaIdMemoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) equipoIdEquipo;
        hash += (int) tipoMemoriaIdMemoria;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipoHasTipoMemoriaPK)) {
            return false;
        }
        EquipoHasTipoMemoriaPK other = (EquipoHasTipoMemoriaPK) object;
        if (this.equipoIdEquipo != other.equipoIdEquipo) {
            return false;
        }
        if (this.tipoMemoriaIdMemoria != other.tipoMemoriaIdMemoria) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.EquipoHasTipoMemoriaPK[ equipoIdEquipo=" + equipoIdEquipo + ", tipoMemoriaIdMemoria=" + tipoMemoriaIdMemoria + " ]";
    }
    
}
