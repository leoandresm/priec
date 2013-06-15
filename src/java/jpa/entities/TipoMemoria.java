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
@Table(name = "tipo_memoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMemoria.findAll", query = "SELECT t FROM TipoMemoria t"),
    @NamedQuery(name = "TipoMemoria.findByIdMemoria", query = "SELECT t FROM TipoMemoria t WHERE t.idMemoria = :idMemoria"),
    @NamedQuery(name = "TipoMemoria.findByMarca", query = "SELECT t FROM TipoMemoria t WHERE t.marca = :marca"),
    @NamedQuery(name = "TipoMemoria.findByVelocidadFsb", query = "SELECT t FROM TipoMemoria t WHERE t.velocidadFsb = :velocidadFsb"),
    @NamedQuery(name = "TipoMemoria.findByCapacidad", query = "SELECT t FROM TipoMemoria t WHERE t.capacidad = :capacidad"),
    @NamedQuery(name = "TipoMemoria.findByTecnologia", query = "SELECT t FROM TipoMemoria t WHERE t.tecnologia = :tecnologia")})
public class TipoMemoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_memoria")
    private Integer idMemoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "velocidad_fsb")
    private int velocidadFsb;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacidad")
    private int capacidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "tecnologia")
    private String tecnologia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoMemoria")
    private List<EquipoHasTipoMemoria> equipoHasTipoMemoriaList;

    public TipoMemoria() {
    }

    public TipoMemoria(Integer idMemoria) {
        this.idMemoria = idMemoria;
    }

    public TipoMemoria(Integer idMemoria, String marca, int velocidadFsb, int capacidad, String tecnologia) {
        this.idMemoria = idMemoria;
        this.marca = marca;
        this.velocidadFsb = velocidadFsb;
        this.capacidad = capacidad;
        this.tecnologia = tecnologia;
    }

    public Integer getIdMemoria() {
        return idMemoria;
    }

    public void setIdMemoria(Integer idMemoria) {
        this.idMemoria = idMemoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getVelocidadFsb() {
        return velocidadFsb;
    }

    public void setVelocidadFsb(int velocidadFsb) {
        this.velocidadFsb = velocidadFsb;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    @XmlTransient
    public List<EquipoHasTipoMemoria> getEquipoHasTipoMemoriaList() {
        return equipoHasTipoMemoriaList;
    }

    public void setEquipoHasTipoMemoriaList(List<EquipoHasTipoMemoria> equipoHasTipoMemoriaList) {
        this.equipoHasTipoMemoriaList = equipoHasTipoMemoriaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMemoria != null ? idMemoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMemoria)) {
            return false;
        }
        TipoMemoria other = (TipoMemoria) object;
        if ((this.idMemoria == null && other.idMemoria != null) || (this.idMemoria != null && !this.idMemoria.equals(other.idMemoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return marca + " " + velocidadFsb + " Mhz " + capacidad + " GB " + tecnologia ;
    }
    
}
