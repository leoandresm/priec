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
@Table(name = "tipo_unidad_optica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoUnidadOptica.findAll", query = "SELECT t FROM TipoUnidadOptica t"),
    @NamedQuery(name = "TipoUnidadOptica.findByIdUnidadOptica", query = "SELECT t FROM TipoUnidadOptica t WHERE t.idUnidadOptica = :idUnidadOptica"),
    @NamedQuery(name = "TipoUnidadOptica.findByMarca", query = "SELECT t FROM TipoUnidadOptica t WHERE t.marca = :marca"),
    @NamedQuery(name = "TipoUnidadOptica.findByTipo", query = "SELECT t FROM TipoUnidadOptica t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "TipoUnidadOptica.findByTecnologia", query = "SELECT t FROM TipoUnidadOptica t WHERE t.tecnologia = :tecnologia")})
public class TipoUnidadOptica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_unidad_optica")
    private Integer idUnidadOptica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "tecnologia")
    private String tecnologia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoUnidadOptica")
    private List<EquipoHasTipoUnidadOptica> equipoHasTipoUnidadOpticaList;

    public TipoUnidadOptica() {
    }

    public TipoUnidadOptica(Integer idUnidadOptica) {
        this.idUnidadOptica = idUnidadOptica;
    }

    public TipoUnidadOptica(Integer idUnidadOptica, String marca, String tipo, String tecnologia) {
        this.idUnidadOptica = idUnidadOptica;
        this.marca = marca;
        this.tipo = tipo;
        this.tecnologia = tecnologia;
    }

    public Integer getIdUnidadOptica() {
        return idUnidadOptica;
    }

    public void setIdUnidadOptica(Integer idUnidadOptica) {
        this.idUnidadOptica = idUnidadOptica;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    @XmlTransient
    public List<EquipoHasTipoUnidadOptica> getEquipoHasTipoUnidadOpticaList() {
        return equipoHasTipoUnidadOpticaList;
    }

    public void setEquipoHasTipoUnidadOpticaList(List<EquipoHasTipoUnidadOptica> equipoHasTipoUnidadOpticaList) {
        this.equipoHasTipoUnidadOpticaList = equipoHasTipoUnidadOpticaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidadOptica != null ? idUnidadOptica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoUnidadOptica)) {
            return false;
        }
        TipoUnidadOptica other = (TipoUnidadOptica) object;
        if ((this.idUnidadOptica == null && other.idUnidadOptica != null) || (this.idUnidadOptica != null && !this.idUnidadOptica.equals(other.idUnidadOptica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return marca + " " + tipo + " " + tecnologia ;
    }
    
}
